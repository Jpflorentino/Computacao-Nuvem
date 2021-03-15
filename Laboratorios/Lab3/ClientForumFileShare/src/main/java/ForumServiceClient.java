import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.protobuf.Empty;
import forum.ForumGrpc;
import forum.ForumMessage;
import forum.SubscribeUnSubscribe;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.FileInputStream;
import java.util.Scanner;

public class ForumServiceClient {
    //private static String svcIP = "localhost"; //Para conectar ao servidor local de teste
    private static String svcIP = "35.226.76.124";
    private static int svcPort = 8000;

    static int Menu() {
        Scanner scan = new Scanner(System.in);
        int option;
        do {
            System.out.println("######## MENU ##########");
            System.out.println("Options for Google Storage Operations:");
            System.out.println(" 0: Topic Subscribe");
            System.out.println(" 1: Topic Unsubscribe");
            System.out.println(" 2: Get All Topics");
            System.out.println(" 3: Message Publish");
            System.out.println("..........");
            System.out.println("99: Exit");
            System.out.print("Enter an Option:");
            option = scan.nextInt();
        } while (!((option >= 0 && option <= 3) || option == 99));
        return option;
    }

    public static void main(String[] args) throws Exception {
        // case extfile args[0] - absolutepath do Accountkey.json
        // Assumes the environment variable
        // set GOOGLE_APPLICATION_CREDENTIALS= < ServiceAccountroject.json>
        //Storage storage = StorageOptions.getDefaultInstance().getService();
        Storage storage = null;
        GoogleCredentials credentials = null;
        StorageOptions storageOptions = null;

        if (args.length == 0) {
            storageOptions = StorageOptions.getDefaultInstance();
        } else {
            credentials = GoogleCredentials.fromStream(new FileInputStream(args[0]));
            storageOptions = StorageOptions.newBuilder().setCredentials(credentials).build();
        }
        storage = storageOptions.getService();
        String projID = storageOptions.getProjectId();
        if (projID != null) System.out.println("Current Project ID:" + projID);
        else {
            System.out.println("The environment variable GOOGLE_APPLICATION_CREDENTIALS isn't well defined!!");
            System.exit(-1);
        }
        //StorageOperations soper = new StorageOperations(storage);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(svcIP, svcPort).usePlaintext().build();

        ForumGrpc.ForumStub noBlockStub = ForumGrpc.newStub(channel);

        boolean end = false;

        //Variaveis a serem passados pelo cliente
        Scanner scan = new Scanner(System.in);
        System.out.println("Write Username: ");
        String username = scan.nextLine();
        System.out.println("Complete com o nome do ficheiro que pretende: ");
        String directory = scan.nextLine();

        while (!end) {
            TopicStreamObserver tso = new TopicStreamObserver();
            EmptyStreamObserver eso = new EmptyStreamObserver();
            try {
                int option = Menu();
                switch (option) {
                    case 0:
                        System.out.println("Write topic name: ");
                        String topicName = scan.nextLine();
                        FMStreamObserver fmso = new FMStreamObserver(storage, directory);
                        noBlockStub.topicSubscribe(SubscribeUnSubscribe
                                .newBuilder()
                                .setUsrName(username)
                                .setTopicName(topicName)
                                .build(), fmso);
                        break;
                    case 1:
                        System.out.println("Write topic name: ");
                        String topicName1 = scan.nextLine();
                        noBlockStub.topicUnSubscribe(SubscribeUnSubscribe
                                .newBuilder()
                                .setUsrName(username)
                                .setTopicName(topicName1)
                                .build(), eso);
                        break;
                    case 2:
                        noBlockStub.getAllTopics(Empty.newBuilder().build(), tso);
                        break;
                    case 3:
                        System.out.println("Write topic name: ");
                        String topicName3 = scan.nextLine();
                        System.out.println("Message: ");
                        String message = scan.nextLine();
                        MpStreamObserver mps = new MpStreamObserver(noBlockStub, ForumMessage.newBuilder()
                                .setFromUser(username)
                                .setTopicName(topicName3)
                                .setTxtMsg(message)
                                .build(), eso);
                        noBlockStub.getAllTopics(Empty.newBuilder().build(), mps);
                        break;
                    // Other Operations
                    case 99:
                        System.exit(0);
                }
            } catch (Exception ex) {
                System.out.println("Erro executing operations!");
                ex.printStackTrace();
            }
        }
    }
}
