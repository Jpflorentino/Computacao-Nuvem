import java.util.Scanner;

import static Utils.CnUtils.getMessagesLogger;

public class LoggingApp {
    static int Menu() {
        Scanner scan = new Scanner(System.in);
        int option;
        do {
            System.out.println("######## MENU ##########");
            System.out.println("Options for Google PubSub Operations:");
            System.out.println(" 0: Get Messages");
            System.out.println("..........");
            System.out.println("99: Exit");
            System.out.print("Enter an Option:");
            option = scan.nextInt();
        } while (!((option >= 0 && option <= 1) || option == 99));
        return option;
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            try {
                int option = Menu();
                switch (option) {
                    case 0:
                        System.out.println("Qual o nome da subscription?");
                        String subscription = scan.nextLine();
                        getMessagesLogger(subscription);
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
