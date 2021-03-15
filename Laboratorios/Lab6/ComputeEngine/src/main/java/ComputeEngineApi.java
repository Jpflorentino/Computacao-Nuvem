import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.compute.Compute;
import com.google.api.services.compute.ComputeScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Utils.ComputeEngineOperations.*;

public class ComputeEngineApi {
    static int Menu() {
        Scanner scan = new Scanner(System.in);
        int option;
        do {
            System.out.println("######## MENU ##########");
            System.out.println("Options for Google Compute Engine Operations:");
            System.out.println(" 0: List VMs instances");
            System.out.println(" 1: Create VM instance");
            System.out.println(" 2: Delete VM instance");
            System.out.println(" 3: List Instance Groups");
            System.out.println(" 4: Change Number of Instances");
            System.out.println("..........");
            System.out.println("99: Exit");
            System.out.print("Enter an Option:");
            option = scan.nextInt();
        } while (!((option >= 0 && option <= 4) || option == 99));
        return option;
    }

    public static void main(String[] args) throws Exception {
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        List<String> scopes = new ArrayList<>();
        scopes.add(ComputeScopes.COMPUTE);
        credentials = credentials.createScoped(scopes);
        HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        HttpRequestInitializer requestInit = new HttpCredentialsAdapter(credentials);
        Compute computeService = new Compute
                .Builder(transport, jacksonFactory, requestInit)
                .setApplicationName("ComputeEngineApi").build();

        Scanner scan = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            try {
                int option = Menu();
                switch (option) {
                    case 0:
                        System.out.println("\n");
                        listVmInstancesInUsCentral(computeService);
                        listVmInstancesInEurope(computeService);
                        break;
                    case 1:
                        System.out.println("Qual o nome da instância? (ALL SMALL LETTERS)");
                        String instanceName1 = scan.nextLine();
                        createInstance(computeService, instanceName1);
                        break;
                    case 2:
                        System.out.println("Qual o nome da instância? (ALL SMALL LETTERS)");
                        String instanceName2 = scan.nextLine();
                        deleteInstance(computeService, instanceName2);
                        break;
                    case 3:
                        listGroupManagers(computeService);
                        break;
                    case 4:
                        System.out.println("Qual o nome do grupo de instâncias? (ALL SMALL LETTERS)");
                        String instanceGroup3 = scan.nextLine();
                        System.out.println("Qual o numero de instâncias?");
                        String instanceNumber = scan.nextLine();
                        resizeNumberOfInstances(computeService, instanceGroup3, Integer.parseInt(instanceNumber));
                        break;
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
