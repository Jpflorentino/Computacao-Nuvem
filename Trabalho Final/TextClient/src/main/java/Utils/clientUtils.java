package Utils;

import Observers.ClientImageStreamObserver;
import io.grpc.stub.StreamObserver;
import textservice.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.google.protobuf.ByteString.copyFrom;

public class clientUtils {

    public static String user;
    public static int sessionId;
    //ImageMap serve para guardar o id da imagem que é o tamanho do mapa +1 e o path da mesma
    public static Map<String, String> ImageMap = new HashMap<>();

    //Imports do lab2
    public static TextServiceGrpc.TextServiceStub noBlockStub;
    public static TextServiceGrpc.TextServiceBlockingStub blockingStub;

    //Utilitarios
    private static Scanner scanner = new Scanner(System.in);

    public static void logIn() {
        System.out.println("Serviço de Tradução de texto de imagens");
        boolean finish = false;
        while (!finish) {
            System.out.println("Username: ");
            user = scanner.nextLine();
            LoginRequest request = LoginRequest.newBuilder().setUserName(user).build();
            LoginReply reply = blockingStub.login(request);
            if (reply.getSessionId() != 0) {
                finish = true;
                sessionId = reply.getSessionId();
                System.out.println("SUCCESS, sessionID: " + sessionId);
            } else {
                System.out.println("FAILED, TRY AGAIN!");
            }
        }
    }

    public static void logOut(int sessionId) {
        LogOutRequest logOutRequest = LogOutRequest.newBuilder().setSessionId(sessionId).build();
        LogOutReply logOutReply = blockingStub.logout(logOutRequest);
        System.out.println("Log Out com sucesso: " + logOutReply.getMessageLogOut());
    }

    public static void sendImage(int sessionId) throws IOException {
        System.out.println("Qual o ficheiro?");
        String ficheiro = scanner.nextLine();
        System.out.println("Indique a linguagem de tradução (pt, en, es, it, ..)");//Tirado do enunciado
        String linguagem = scanner.nextLine();

        Path uploadFicheiro = Paths.get(ficheiro);
        //https://docs.oracle.com/javase/8/docs/api/java/nio/file/spi/FileTypeDetector.html
        String contentType = Files.probeContentType(uploadFicheiro);

        ClientImageStreamObserver clientImageStreamObserver = new ClientImageStreamObserver();
        StreamObserver<ImageRequest> requestStreamObserver = noBlockStub.sendImage(clientImageStreamObserver);
        //Primeiro valor inserido foi 100KB porem como a divisao em ficheiros mais pequenos nao estava a funcionar decidimos alterar o valor para 1MB
        //pois nao tinhamos nenhum ficheiro acima desse valor para alem disso é o valor utilizado no slide 16 da segunda parte de storage
        if (Files.size(uploadFicheiro) > 1_000_000) {
            byte[] buffer = new byte[65536];
            try (InputStream input = Files.newInputStream(uploadFicheiro)) {
                int max;
                while ((max = input.read(buffer)) >= 0) {
                    ImageRequest request = ImageRequest.newBuilder()
                            .setSessionId(sessionId)
                            .setImageId(ImageMap.size() + 1)
                            .setBytesSent(copyFrom(buffer))
                            .setMaxSize(max)
                            .setTranslationId(linguagem)
                            .setContentType(contentType)
                            .build();
                    requestStreamObserver.onNext(request);
                }
                requestStreamObserver.onCompleted();

                //Tivemos de fazer esta parte porque senão o código nao parava
                while (!clientImageStreamObserver.isCompleted()) {
                    System.out.println("Imagem a ser submetida");
                }

                List<ImageReply> imageReplyList = clientImageStreamObserver.getReplyList();
                ImageReply imageReply = imageReplyList.get(0);
                String imageId = imageReply.getImageId();
                //Insere no mapa o ID da imagem que é o tamanho do mapa + 1 da imagem que foi  inserida e insere o path da imagem
                ImageMap.put(imageId, ficheiro);
                System.out.println("Imagem: " + imageId);
                System.out.println("SUCESSO!!");

            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            //Parte  do codigo que é executada quando se manda uma imagem
            byte[] buffer2 = Files.readAllBytes(uploadFicheiro);
            ImageRequest request2 = ImageRequest.newBuilder()
                    .setSessionId(sessionId)
                    .setImageId(ImageMap.size() + 1)
                    .setBytesSent(copyFrom(buffer2))
                    .setMaxSize(2000) //Usado tambem no server
                    .setTranslationId(linguagem)
                    .setContentType(contentType)
                    .build();
            requestStreamObserver.onNext(request2);
            requestStreamObserver.onCompleted();

            while (!clientImageStreamObserver.isCompleted()) {
                System.out.println("Imagem a ser submetida");
            }

            List<ImageReply> imageReplyList2 = clientImageStreamObserver.getReplyList();
            ImageReply imageReply2 = imageReplyList2.get(0);
            String imageId2 = imageReply2.getImageId();
            ImageMap.put(imageId2, ficheiro);
            System.out.println("Imagem: " + imageId2);
            System.out.println("SUCESSO!!");
        }
    }

    public static void detectText(String name) {
        ORCRequest request = ORCRequest.newBuilder().setImgName(name).build();
        ORCReply orcReply = blockingStub.detectText(request);
        System.out.println(orcReply.getRpImgName());
    }

    public static void receiveTextDetected(String name){
        ORCResultRequest request = ORCResultRequest.newBuilder().setImgName(name).build();
        ORCResultReply reply = blockingStub.receiveTextDetected(request);
        System.out.println(reply.getTextResult());
    }

    public static void receiveTextTranslated(String name){
        TranslateResultRequest request = TranslateResultRequest.newBuilder().setImgName(name).build();
        TranslateResultReply reply = blockingStub.receiveTextTranslated(request);
        System.out.println(reply.getTextResult());
    }
}
