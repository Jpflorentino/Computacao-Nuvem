package Utils;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CnUtils {
    private static final String PROJECT_ID = "g05-leirt61d";

    //Inserto Documents caixa
    public static void insertDocuments(String pathnameCSV) throws Exception {
        int serial_number = 0;
        TopicName topicName = TopicName.ofProjectTopicName(PROJECT_ID, "sales");
        Publisher publisher = Publisher.newBuilder(topicName).build();
        BufferedReader reader = new BufferedReader(new FileReader(pathnameCSV));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] cols = line.split(",");
            ByteString msg = ByteString.copyFromUtf8(line);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                    .setData(msg)
                    .putAttributes("ID", cols[0] + "-" + serial_number)
                    .putAttributes("Caixa", cols[0])
                    .putAttributes("Item", cols[1])
                    .putAttributes("Quant", cols[2])
                    .putAttributes("PrecoUnit", cols[3])
                    .putAttributes("Total", cols[4])
                    .build();
            serial_number += 1;
            ApiFuture<String> future = publisher.publish(pubsubMessage);
            String msgID = future.get();
            System.out.println("Message Published with ID = " + msgID);
        }
        publisher.shutdown();
        System.out.println("Documento inserido com sucesso!!");
    }

    //Obter mensagens para Logger
    public static void getMessagesLogger(String subsName) {
        Scanner stop = new Scanner(System.in);
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(PROJECT_ID, subsName);
        Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, new MessageReceiveLoggerHandler()).build();
        subscriber.startAsync().awaitRunning();

        System.out.println("To stop press enter");
        String option = stop.nextLine();
    }

    //Obter mensagens para Worker
    public static void getMessagesWorker(String subsName, String subscribers) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("D:\\Documentos\\Universidade\\6 Semetre\\Computacao na Nuvem\\Trabalhos_CN\\Lab3\\g05-leirt61d-8a5f23de8d8d.json"));
        FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder().setCredentials(credentials).build();
        Firestore firestore = firestoreOptions.getService();
        Scanner stop = new Scanner(System.in);
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(PROJECT_ID, subsName);
        for (int i = 0; i < Integer.parseInt(subscribers); i++) {
            Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, new MessageReceiveWorkerHandler(firestore, i)).build();
            subscriber.startAsync().awaitRunning();
        }

        System.out.println("To stop press enter");
        String option = stop.nextLine();
    }

}
