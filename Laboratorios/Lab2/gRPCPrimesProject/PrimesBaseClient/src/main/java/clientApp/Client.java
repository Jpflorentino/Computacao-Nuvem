package clientApp;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import operationservice.OperationReply;
import operationservice.OperationRequest;
import operationservice.OperationServiceGrpc;

import java.util.Iterator;

public class Client {
    //private static String svcIP = "localhost";
    private static String svcIP = "104.197.42.60";
    private static int svcPort = 80;
    private static ManagedChannel channel;
    private static OperationServiceGrpc.OperationServiceBlockingStub blockingStub;
    private static OperationServiceGrpc.OperationServiceStub noBlockStub;
    private static OperationServiceGrpc.OperationServiceFutureStub futStub;

    static void findPrimes() throws InterruptedException {
        int numOfPrimes = 7;
        int start = 3;
        //sincrono
        System.out.println("FindPrimes stream server blocking stub:");
        OperationRequest req = OperationRequest.newBuilder().setOpRequest1(numOfPrimes).setOpRequest2(start).build();
        Iterator<OperationReply> manyRpys = blockingStub.findPrimes(req);
        while (manyRpys.hasNext()) {
            OperationReply rpy = manyRpys.next();
            System.out.println("Reply for FindPrimes BlockStub: " + rpy.getOpReply());
        }
        //assíncrono
        System.out.println("FindPrimes with no blocking Stub");
        ClientStreamObserver rpyStreamObs = new ClientStreamObserver();
        noBlockStub.findPrimes(req, rpyStreamObs);
        while (!rpyStreamObs.isCompleted()) {
            System.out.println("Active and waiting for FindPrimes completed ");
            Thread.sleep(1 * 1000);
        }
        if (rpyStreamObs.OnSuccesss()) {
            for (OperationReply rpy : rpyStreamObs.getReplays()) {
                System.out.println("Reply for FindPrimes call: " + rpy.getOpReply());
            }
        }
    }

    //Sum
    static void sumIntNumbers(int... numbers) throws InterruptedException {
        System.out.println("\nsumIntNumbers stream client with no blocking Stub");
        ClientSumStreamObserver rpyStreamObs = new ClientSumStreamObserver();
        StreamObserver<OperationRequest> reqs = noBlockStub.sumIntNumbers(rpyStreamObs);
        /*
        for (int i = 0; i < 5; i++) {
            NumOfIntNumbers req = NumOfIntNumbers.newBuilder().setNumOfIntNumbers(i).build();
            reqs.onNext(req);
        }
         */

        for (int i = 0; i < numbers.length; i++) {
            OperationRequest req = OperationRequest.newBuilder().setOpRequest1(numbers[i]).build();
            reqs.onNext(req);
        }

        reqs.onCompleted();
        while (!rpyStreamObs.isCompleted()) {
            System.out.println("cliente active");
            Thread.sleep(1 * 1000);
        }
        // processar as respostas em rpyStreamObs.getReplays()
    }

    //sumWithStream
    static void sumStreamIntNumbers(int... numbers) throws InterruptedException {
        System.out.println("\nsumStreamIntNumbers stream client and stream server with no blocking Stub");
        ClientStreamObserver rpyStreamObs = new ClientStreamObserver();
        StreamObserver<OperationRequest> reqs = noBlockStub.sumStreamIntNumbers(rpyStreamObs);
        /*
        for (int i = 0; i < 5; i++) {
            OperationRequest req = OperationRequest.newBuilder().setOpRequest1(i).build();
            reqs.onNext(req);
        }
         */
        for (int i = 0; i < numbers.length; i += 2) {
            if (i == numbers.length - 1 && numbers.length % 2 != 0) {
                OperationRequest req = OperationRequest.newBuilder().setOpRequest1(numbers[i]).build();
                reqs.onNext(req);
            } else {
                OperationRequest req = OperationRequest.newBuilder().setOpRequest1(numbers[i]).setOpRequest2(numbers[i + 1]).build();
                reqs.onNext(req);
            }
        }

        reqs.onCompleted();
        while (!rpyStreamObs.isCompleted()) {
            System.out.println("cliente active");
            Thread.sleep(1 * 1000);
        }
    }

    static void findPrimesStream(int inicio, int fim) throws InterruptedException {
        System.out.println("\nfindPrimesStream stream client and stream server with no blocking Stub");
        ClientFindPrimesStreamObserver rpyStreamObs = new ClientFindPrimesStreamObserver();
        StreamObserver<OperationRequest> reqs = noBlockStub.findPrimesStream(rpyStreamObs);
        int metade = fim / 2;
        int umQuarto = fim / 4;
        int tresQuartos = metade + umQuarto;

        //Mandar primeira parte
        OperationRequest reqPrimeiro = OperationRequest.newBuilder().setOpRequest1(inicio).setOpRequest2(umQuarto).build();
        reqs.onNext(reqPrimeiro);
        System.out.println("\nPrimeira Parte");
        Thread.sleep(1 * 1000);

        //Mandar segunda parte
        OperationRequest reqSegundo = OperationRequest.newBuilder().setOpRequest1(umQuarto).setOpRequest2(metade).build();
        reqs.onNext(reqSegundo);
        System.out.println("\nSegunda Parte");
        Thread.sleep(1 * 1000);

        //Mandar terceira parte
        OperationRequest reqTerceiro = OperationRequest.newBuilder().setOpRequest1(metade).setOpRequest2(tresQuartos).build();
        reqs.onNext(reqTerceiro);
        System.out.println("\nTerceira Parte");
        Thread.sleep(1 * 1000);

        //Mandar quarta parte
        OperationRequest reqQuarto = OperationRequest.newBuilder().setOpRequest1(tresQuartos).setOpRequest2(fim).build();
        reqs.onNext(reqQuarto);
        System.out.println("\nQuarta Parte");
        Thread.sleep(1 * 1000);

        reqs.onCompleted();
        while (!rpyStreamObs.isCompleted()) {
            System.out.println("cliente active");
            Thread.sleep(1 * 100);
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                svcIP = args[0];
                svcPort = Integer.parseInt(args[1]);
            }
            channel = ManagedChannelBuilder.forAddress(svcIP, svcPort)
                    // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                    // needing certificates.
                    .usePlaintext()
                    .build();
            blockingStub = OperationServiceGrpc.newBlockingStub(channel);
            noBlockStub = OperationServiceGrpc.newStub(channel);
            futStub = OperationServiceGrpc.newFutureStub(channel);

            // invocar as operações disponibilizadas pelo serviço
            //findPrimes();   // stream servidor
            sumIntNumbers(100, 100, 100, 100);// stream sum //Soma tem de ser 400
            //sumStreamIntNumbers(100, 100, 100, 100); // Resposta 200 e 200 (caso array par)
            //sumStreamIntNumbers(100, 100, 100, 100, 100); // Resposta 200 e 200 e 100 (caso array impar)
            //findPrimesStream(1, 100); // stream servidor findPrimes
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
