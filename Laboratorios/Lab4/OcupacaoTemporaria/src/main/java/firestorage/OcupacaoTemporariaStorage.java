package firestorage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

import java.io.FileInputStream;
import java.util.Scanner;

public class OcupacaoTemporariaStorage {

    static int Menu() {
        Scanner scan = new Scanner(System.in);
        int option;
        do {
            System.out.println("######## MENU ##########");
            System.out.println("Options for Google FireStorage Operations:");
            System.out.println(" 0: Inserir documentos");
            System.out.println(" 1: Mostrar conteúdo por ID");
            System.out.println(" 2: Apagar campo do documento");
            System.out.println(" 3: Interrogação simples");
            System.out.println(" 4: Interrogação composta");
            System.out.println("..........");
            System.out.println("99: Exit");
            System.out.print("Enter an Option:");
            option = scan.nextInt();
        } while (!((option >= 0 && option <= 4) || option == 99));
        return option;
    }

    public static void main(String[] args) throws Exception {
        // case extfile args[0] - absolutepath do Accountkey.json
        // Assumes the environment variable
        // set GOOGLE_APPLICATION_CREDENTIALS= < ServiceAccountroject.json>
        //Storage storage = StorageOptions.getDefaultInstance().getService();
        Firestore firestore = null;
        GoogleCredentials credentials = null;
        FirestoreOptions firestoreOptions = null;
        if (args.length == 0) {
            firestoreOptions = FirestoreOptions.getDefaultInstance();
        } else {
            credentials = GoogleCredentials.fromStream(new FileInputStream(args[0]));
            firestoreOptions = FirestoreOptions.newBuilder().setCredentials(credentials).build();
        }
        firestore = firestoreOptions.getService();
        String projID = firestoreOptions.getProjectId();
        if (projID != null) System.out.println("Current Project ID:" + projID);
        else {
            System.out.println("The environment variable GOOGLE_APPLICATION_CREDENTIALS isn't well defined!!");
            System.exit(-1);
        }

        FirestoreOperations firestoreOp = new FirestoreOperations(firestore);
        Scanner scan = new Scanner(System.in);

        boolean end = false;
        while (!end) {
            try {
                int option = Menu();
                switch (option) {
                    case 0:
                        System.out.println("Qual o path do ficheiro?");
                        String pathName = scan.nextLine();
                        System.out.println("Qual o nome da collection?");
                        String collectionName = scan.nextLine();
                        firestoreOp.insertDocuments(pathName, firestore, collectionName);
                        break;
                    case 1:
                        System.out.println("Qual o nome da Collection?");
                        String collectionName1 = scan.nextLine();
                        System.out.println("Qual o ID do documento?");
                        String documentId = scan.nextLine();
                        firestoreOp.showDocumentById(firestore, collectionName1, documentId);
                        break;
                    case 2:
                        System.out.println("Qual o nome da Collection?");
                        String collectionName2 = scan.nextLine();
                        System.out.println("Qual o ID do documento?");
                        String documentId1 = scan.nextLine();
                        System.out.println("Qual o nome do campo?");
                        String fieldName = scan.nextLine();
                        firestoreOp.deleteContent(firestore, collectionName2, documentId1, fieldName);
                        break;
                    case 3:
                        System.out.println("Qual o nome da Collection?");
                        String collectionName3 = scan.nextLine();
                        System.out.println("Qual o nome da freguesia?");
                        String freguesia = scan.nextLine();
                        firestoreOp.simpleInterrogation(firestore, collectionName3, freguesia);
                        break;
                    case 4:
                        System.out.println("Qual o nome da Collection?");
                        String collectionName4 = scan.nextLine();
                        System.out.println("Qual o nome do documento?");
                        String documentId2 = scan.nextLine();
                        System.out.println("Qual o nome da freguesia?");
                        String freguesia2 = scan.nextLine();
                        System.out.println("Tipo do evento?");
                        String eventoID = scan.nextLine();
                        firestoreOp.composeInterrogation(firestore, collectionName4, documentId2, freguesia2, eventoID);
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
