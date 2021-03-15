package visionapp.utils;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import visionapp.models.DetectedText;

import java.io.IOException;
import java.util.List;

import static visionapp.utils.utils.*;

public class MessageReceiverHandler implements MessageReceiver {
    //GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("D:\\Documentos\\Universidade\\6 Semestre\\Computacao na Nuvem\\Trabalhos_CN\\Lab3\\g05-leirt61d-8a5f23de8d8d.json"));
    //GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\asus\\Desktop\\CN\\Trabalhos_CN\\Lab3\\g05-leirt61d-8270b4aba338.json"));

    //Teve de ser usado getApplicationDefault pois o quando corrido em maquina virtual
    //como esta nao tem acesso ao computador local, nao consegue aceder ao ficheiro das credenciais
    GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
    FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder().setCredentials(credentials).build();
    Firestore firestore = firestoreOptions.getService();
    //iniciação do serviço Storage
    StorageOptions storageOptions = StorageOptions.newBuilder().setCredentials(credentials).build();
    Storage storage = storageOptions.getService();

    private final Publisher publisher;

    public MessageReceiverHandler(Publisher publisher) throws IOException {
        this.publisher = publisher;
    }

    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        System.out.println("Receive Handler");
        try {
            String imageName = pubsubMessage.getData().toStringUtf8();
            String languagem = pubsubMessage.getAttributesOrThrow("TranslationLanguage");
            System.out.println("Language: " + languagem);
            downloadImageFromBucket(imageName, storage);
            //String pathToImage = "D:\\Documentos\\Universidade\\6 Semestre\\Computacao na Nuvem\\Trabalhos_CN\\TrabFinal\\ImagesOCR\\" + imageName;
            String pathToImage = "/var/forum/" + imageName;
            Translate translate = TranslateOptions.getDefaultInstance().getService();
            List<DetectedText> detectedTextList = detectText(translate, pathToImage);

            insertDocuments(imageName, detectedTextList.get(0), firestore, "TextDetected");

            ByteString msgImageId = ByteString.copyFromUtf8(imageName);
            //Criar no topico para ser usado em translate o descritor da linguagem passada
            //de forma a saber qual a linguagem que tem de usar no translate
            PubsubMessage pubsubMessage1 = PubsubMessage.newBuilder()
                    .setData(msgImageId)
                    .putAttributes("TranslationLanguage", languagem)
                    .putAttributes("Detected", detectedTextList.get(0).language)
                    .build();

            ApiFuture<String> future = publisher.publish(pubsubMessage1);
            String messageId = future.get();
            System.out.println("Message Published: " + messageId);
            ackReplyConsumer.ack();
            System.out.println("Message ack");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
