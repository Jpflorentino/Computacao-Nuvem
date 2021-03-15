package Server;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import operationservice.OperationReply;
import operationservice.OperationRequest;
import operationservice.OperationServiceGrpc;

import java.util.Scanner;

public class Server extends OperationServiceGrpc.OperationServiceImplBase {
    private static int svcPort = 80;

    public static void main(String[] args) {
        try {
            if (args.length > 0) svcPort = Integer.parseInt(args[0]);
            io.grpc.Server svc = ServerBuilder
                    .forPort(svcPort)
                    .addService(new Server())
                    .build();
            svc.start();
            System.out.println("Server started, listening on " + svcPort);
            Scanner scan = new Scanner(System.in);
            scan.nextLine();
            svc.shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void findPrimes(OperationRequest request, StreamObserver<OperationReply> responseObserver) {
        System.out.println("Function findPrimes called...");
        int numberOfPrimes = request.getOpRequest1();
        int start = request.getOpRequest2();

        while (numberOfPrimes > 0) {
            if (isPrime(start)) {
                OperationReply prime = OperationReply.newBuilder().setOpReply(start).build();
                responseObserver.onNext(prime);
                numberOfPrimes--;
            }
            start++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<OperationRequest> sumIntNumbers(StreamObserver<OperationReply> responseObserver) {
        System.out.println("Function sumIntNumbers called...");
        StreamObserverSum reqs = new StreamObserverSum(responseObserver);
        return reqs;
    }

    @Override
    public StreamObserver<OperationRequest> sumStreamIntNumbers(StreamObserver<OperationReply> responseObserver) {
        System.out.println("Function sumStreamIntNumbers called...");
        StreamObserverSumStream reqs = new StreamObserverSumStream(responseObserver);
        return reqs;
    }

    @Override
    public StreamObserver<OperationRequest> findPrimesStream(StreamObserver<OperationReply> responseObserver) {
        System.out.println("Function findPrimesStream called...");
        StreamObserverFindPrimesStream reqs = new StreamObserverFindPrimesStream(responseObserver);
        return reqs;
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