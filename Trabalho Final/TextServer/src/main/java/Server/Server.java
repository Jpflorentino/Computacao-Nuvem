package Server;

import Observers.StreamObserverImage;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.core.ApiFuture;
import com.google.api.services.compute.Compute;
import com.google.api.services.compute.ComputeScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import textservice.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class Server extends TextServiceGrpc.TextServiceImplBase {

    private static int sessionId = 1;
    private static int svcPort = 80;
    private static final String PROJECT_ID = "g05-leirt61d";
    private static final String freeTopicName = "free-ocr";
    //Lab5, criamos um tópico que ira ser subscrito
    TopicName topicNameFree = TopicName.ofProjectTopicName(PROJECT_ID, freeTopicName);
    //Ira subscrever/publicar no topico criado
    Publisher pubFree = Publisher.newBuilder(topicNameFree).build();
    private static final String premiumTopicName = "premium-ocr";
    TopicName topicNamePremium = TopicName.ofProjectTopicName(PROJECT_ID, premiumTopicName);
    Publisher pubPremium = Publisher.newBuilder(topicNamePremium).build();

    private static int premiumUsers = 0;
    private static int initialUsers = 0;
    //Criar um mapa para os utilizadores que ira ter um id de utilizador e o nome do utilizador conectado
    private static Map<Integer, String> userLogInMap = new HashMap<>();
    private static Compute computeService;

    public Server() throws IOException {
    }

    public static void main(String[] args) {
        try {
            if (args.length > 0) svcPort = Integer.parseInt(args[0]);
            io.grpc.Server svc = ServerBuilder
                    .forPort(svcPort)
                    .addService(new Server())
                    .build();
            svc.start();
            System.out.println("Server started, listening on " + svcPort);
            //Lab6 ComputeEngineApi para redimensionamento de vms
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
            List<String> scopes = new ArrayList<>();
            scopes.add(ComputeScopes.COMPUTE);
            credentials = credentials.createScoped(scopes);
            HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
            JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
            HttpRequestInitializer requestInit = new HttpCredentialsAdapter(credentials);
            computeService = new Compute
                    .Builder(transport, jacksonFactory, requestInit)
                    .setApplicationName("Server").build();

            Scanner scan = new Scanner(System.in);
            scan.nextLine();
            svc.shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginReply> responseObserver) {
        System.out.println("Login");
        LoginReply reply;
        try {
            //Lab5 ocupacaoTemporariaStorage
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("D:\\Documentos\\Universidade\\6 Semestre\\Computacao na Nuvem\\Trabalhos_CN\\Lab3\\g05-leirt61d-8a5f23de8d8d.json"));
            //GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\asus\\Desktop\\CN\\Trabalhos_CN\\Lab3\\g05-leirt61d-8270b4aba338.json"));
            FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder().setCredentials(credentials).build();
            Firestore firestore = firestoreOptions.getService();
            //Aceder à coleçao firestore dos utilizadores
            CollectionReference collectionReference = firestore.collection("Users");
            Iterable<DocumentReference> documentReferences = collectionReference.listDocuments();
            boolean finish = false;
            //Lab4 showDocumentById
            for (DocumentReference documentReference : documentReferences) {
                ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = documentReference.get();
                DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();
                if (documentSnapshot.get("username").equals(request.getUserName())) {
                    finish = true;
                    //Quando um user faz log in incrementamos o numero de utilizadores no mapa
                    reply = LoginReply.newBuilder().setSessionId(sessionId).build();
                    //Adicionar tambem o tipo de servico utilizado pelo cliente para distincao de premium e free user
                    userLogInMap.put(sessionId, request.getUserName() + ":" + documentSnapshot.get("servico"));
                    sessionId++;
                    if (documentSnapshot.get("servico").equals("premium")) {
                        //Quando incrementamos o numero de utilizadores premium incrementamos também o número de vms dependendo
                        //do numero de utilizadores que temos
                        premiumUsers++;
                        if (initialUsers != premiumUsers) {
                            //Lab6 funcao resizeNumberOfInstances
                            Compute.InstanceGroupManagers.Resize ORCInstanceGroup = computeService.instanceGroupManagers().resize(PROJECT_ID, "europe-west1-b", "premium-ocr-instance-group", premiumUsers);
                            ORCInstanceGroup.execute();
                            Compute.InstanceGroupManagers.Resize TranslateInstanceGroup = computeService.instanceGroupManagers().resize(PROJECT_ID, "europe-west1-b", "premium-translate-instance-group", premiumUsers);
                            TranslateInstanceGroup.execute();
                            initialUsers = premiumUsers;
                        }
                    }
                    responseObserver.onNext(reply);
                    responseObserver.onCompleted();
                    //Printar o numero de utilizadores que estao "logados" para simples debug
                    UserMapPrint(userLogInMap);
                }
            }
            if (!finish) {
                reply = LoginReply.newBuilder().setSessionId(0).build();
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void logout(LogOutRequest request, StreamObserver<LogOutReply> responseObserver) {
        LogOutReply reply = LogOutReply.newBuilder().setMessageLogOut("Sessao terminada: " + request.getSessionId()).build();
        try {
            //Igual à funcao de login mas para decrescimo de utilizadores
            String userName = userLogInMap.get(request.getSessionId());
            if (userName.split(":")[1].equals("premium")) {
                premiumUsers--;
                if (initialUsers != premiumUsers) {
                    Compute.InstanceGroupManagers.Resize resize = computeService.instanceGroupManagers().resize(PROJECT_ID, "europe-west1-b", "premium-ocr-instance-group", premiumUsers);
                    resize.execute();
                    Compute.InstanceGroupManagers.Resize TranslateInstanceGroup = computeService.instanceGroupManagers().resize(PROJECT_ID, "europe-west1-b", "premium-translate-instance-group", premiumUsers);
                    TranslateInstanceGroup.execute();
                    initialUsers = premiumUsers;
                }
            }
            //Remover o utilizador do mapa
            userLogInMap.remove(request.getSessionId());
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            UserMapPrint(userLogInMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    //Necessário criar streamObserver por ser um caso 3
    public StreamObserver<ImageRequest> sendImage(StreamObserver<ImageReply> responseObserver) {
        System.out.println("Enviar Imagem");
        try {
            StreamObserverImage streamObserverImage = new StreamObserverImage(responseObserver);
            return streamObserverImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void detectText(ORCRequest request, StreamObserver<ORCReply> replyStreamObserver) {
        try {
            String imgName = request.getImgName();
            //System.out.println(imgName);
            String[] line = imgName.split("_");
            for (String s : line) {
                System.out.println(s);
            }
            ByteString imgNameMsg = ByteString.copyFromUtf8(imgName);

            //Criar o pubsub no qual ira ser registado a imagem
            PubsubMessage pubsubMessage =
                    PubsubMessage.newBuilder()
                            .setData(imgNameMsg)
                            .putAttributes("TranslationLanguage", line[2])
                            .putAttributes("ID", imgName)
                            .build();

            //Ver se o cliente é premium ou free
            //Uma vez que no identificador da imagem tambem esta inserido no primeiro parametro da imagem o id do  utilizador
            //Entao apenas temos de ir buscar o 1 elemento a seguir a imagem e de seguida buscar o servico do cliente
            String userService = (userLogInMap.get(Integer.parseInt(imgName.split("_")[1]))).split(":")[1];

            //Verificar se o utilizador é free ou premium para publicar no pubsub certo
            if (userService.equals("free")) {
                ApiFuture<String> future = pubFree.publish(pubsubMessage);
                String IDmsg = future.get();
                System.out.println("Mensagem free publicada: " + IDmsg);
            } else if (userService.equals("premium")) {
                ApiFuture<String> future = pubPremium.publish(pubsubMessage);
                String IDmsg = future.get();
                System.out.println("Mensagem premium publicada: " + IDmsg);
            }
            ORCReply reply = ORCReply.newBuilder().setRpImgName("Imagem Processada com sucesso!").build();
            replyStreamObserver.onNext(reply);
            replyStreamObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveTextDetected(ORCResultRequest request, StreamObserver<ORCResultReply> responseObserver) {
        try {
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
            FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder().setCredentials(credentials).build();
            Firestore firestore = firestoreOptions.getService();

            String imageName = request.getImgName();
            //Aceder à coleção firestore onde foi guardado o texto detetado e uma vez que so temos o texto detetado, apenas passamos
            //esses parametros
            CollectionReference colRef = firestore.collection("TextDetected");
            DocumentReference docRef = colRef.document(imageName);
            ApiFuture<DocumentSnapshot> futureRef = docRef.get();
            DocumentSnapshot document = futureRef.get();

            String languageDetected = document.getString("language");
            String text = document.getString("text");

            String resultMessage = "language: " + languageDetected + "\n" + "text: " + text;

            ORCResultReply reply = ORCResultReply.newBuilder().setTextResult(resultMessage).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcao de cima podia ter sido feita apenas nesta contudo preferimos realizar uma separacao da mesma
    //de forma a perceber quando a api de translate é chamada
    @Override
    public void receiveTextTranslated(TranslateResultRequest request, StreamObserver<TranslateResultReply> responseObserver) {
        try {
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
            FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder().setCredentials(credentials).build();
            Firestore firestore = firestoreOptions.getService();

            String imageName = request.getImgName();
            String colection = "TextDetected";

            CollectionReference colRef = firestore.collection(colection);
            DocumentReference docRef = colRef.document(imageName);
            ApiFuture<DocumentSnapshot> futureRef = docRef.get();
            DocumentSnapshot document = futureRef.get();

            String languageDetected = document.getString("language");
            String translatedText = document.getString("translatedText");

            String resultMessage = "language: " + languageDetected + "\n" + "translatedText: " + translatedText;

            TranslateResultReply reply = TranslateResultReply.newBuilder().setTextResult(resultMessage).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void UserMapPrint(Map<Integer, String> map) {
        System.out.println("Novo Mapa \n");
        for (Integer id : map.keySet()) {
            String name = map.get(id);
            System.out.println("Session: " + id + "\nUser: " + name);
        }
    }
}
