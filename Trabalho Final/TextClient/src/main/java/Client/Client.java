package Client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import textservice.TextServiceGrpc;

import java.util.Scanner;

import static Utils.clientUtils.*;

public class Client {

    private static String svcIP = "localhost"; //Para conectar ao servidor local de teste
    //private static String svcIP = "35.226.76.124"; //Maquina Virtual
    private static int svcPort = 80;
    private static ManagedChannel channel;


    static int Menu() {
        Scanner scan = new Scanner(System.in);
        int option;
        do {
            System.out.println("######## MENU ##########");
            System.out.println("Options for Text App:");
            System.out.println(" 0: Enviar Imagem");
            System.out.println(" 1: Processar texto");
            System.out.println(" 2: Retornar o texto detetado");
            System.out.println(" 3: Retornar o texto traduzido");
            System.out.println(" 4: Terminar sessão");
            System.out.println("..........");
            System.out.println("99: Exit");
            System.out.print("Enter an Option:");
            option = scan.nextInt();
        } while (!((option >= 0 && option <= 4) || option == 99));
        return option;
    }

    public static void main(String[] args) {

        if (args.length == 2) {
            svcIP = args[0];
            svcPort = Integer.parseInt(args[1]);
        }

        //Lab2 grpcPrimesProject client
        channel = ManagedChannelBuilder.forAddress(svcIP, svcPort)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build();

        noBlockStub = TextServiceGrpc.newStub(channel);
        blockingStub = TextServiceGrpc.newBlockingStub(channel);

        logIn();

        Scanner scan = new Scanner(System.in);

        boolean end = false;
        while (!end) {
            try {
                int option = Menu();
                switch (option) {
                    case 0:
                        sendImage(sessionId);
                        break;
                    case 1:
                        System.out.println("Name of Image");
                        String name = scan.nextLine();
                        detectText(name);
                        break;
                    case 2:
                        System.out.println("Name of Image");
                        String name1 = scan.nextLine();
                        receiveTextDetected(name1);
                        break;
                    case 3:
                        System.out.println("Name of Image");
                        String name2 = scan.nextLine();
                        receiveTextTranslated(name2);
                        break;
                    case 4:
                        logOut(sessionId);
                        System.exit(0);
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
