package client;

import contrato.ICallback;
import contrato.IPrimesService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

class ConsoleCallback implements ICallback {
    private int indice;

    public ConsoleCallback() {
        this.indice = -1;
    }

    public ConsoleCallback(int indice) {
        this.indice = indice;
    }

    @Override
    public void nextPrime(int prime) throws RemoteException {
        if (indice < 0) {
            System.out.println("Server reply:" + prime);
        } else {
            System.out.println("Server reply, indice " + indice + ": " + prime);
        }
    }
}

public class ClientRMI {
    //static String serverIP = "10.128.0.2";
    static String serverIP = "localhost"; //para correr no servidor local
    static int registerPort = 7000;

    public static void main(String[] args) {
        try {
            // lookup RMI server in registry
            Registry registry = LocateRegistry.getRegistry(serverIP, registerPort);
            IPrimesService svc = (IPrimesService) registry.lookup("RemCNServer");
            /*
            //
            // call Echo service
            //
            ICallback callback = new ConsoleCallback();
            ICallback callbackStub = (ICallback) UnicastRemoteObject.exportObject(callback, 0);
            System.out.println("Callback registerd @ " + callbackStub);

            // call echo service uma vez
            int start = 3;
            svc.findPrimes(start, 5, callbackStub);


             */

            //chamar echo service varias vezes
            int start = 3;
            int length = 4;
            for (int i = 0; i < length; i++) {
                ICallback callback = new ConsoleCallback(i + 1);
                ICallback callbackStub = (ICallback) UnicastRemoteObject.exportObject(callback, 0);
                svc.findPrimes(start, 5, callbackStub);
            }

            System.out.println("Finished echo test");
            System.exit(0);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Client unhandled exception: " + ex.toString());
            ex.printStackTrace();
        }
    }
}
