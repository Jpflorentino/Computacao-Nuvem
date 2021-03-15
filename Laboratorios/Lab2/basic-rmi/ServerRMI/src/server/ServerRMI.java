package server;

import contrato.ICallback;
import contrato.IPrimesService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class ServerRMI implements IPrimesService {
    static int registerPort = 7000;
    static int svcPort = 7001;
    static ServerRMI svc = null;

    public static void main(String[] args) {
        try {
            Properties props = System.getProperties();
            props.put("java.rmi.server.hostname", args[0]);

            // create server object
            svc = new ServerRMI();

            IPrimesService stubSvc = (IPrimesService) UnicastRemoteObject.exportObject(svc, svcPort);

            // create local registry
            Registry registry = LocateRegistry.createRegistry(registerPort);

            System.out.println("Server registerd @ " + stubSvc);
            registry.rebind("RemCNServer", stubSvc);  // register skeleton on registry

            System.out.println("Server ready: Press any key to finish server");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String line = scanner.nextLine();
            System.exit(0);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Server unhandled exception: " + ex.toString());
            ex.printStackTrace();
        }
    }

    @Override
    public void findPrimes(int start, int numberOfPrimes, ICallback listener) throws RemoteException {
        System.out.println("Using Callback" + listener);
        while (numberOfPrimes > 0) {
            if (isPrime(start)) {
                listener.nextPrime(start);
                numberOfPrimes--;
            }
            start++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
