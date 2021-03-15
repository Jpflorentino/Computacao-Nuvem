package clientApp;

import io.grpc.stub.StreamObserver;
import operationservice.OperationReply;

import java.util.ArrayList;
import java.util.List;

public class ClientStreamObserver implements StreamObserver<OperationReply> {
    private boolean isCompleted = false;
    private boolean success = false;

    public boolean OnSuccesss() {
        return success;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    List<OperationReply> prim = new ArrayList<OperationReply>();

    public List<OperationReply> getReplays() {
        return prim;
    }

    @Override
    public void onNext(OperationReply prime) {
        System.out.println("Reply (" + prime.getOpReply() + ")");
        prim.add(prime);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error on call:" + throwable.getMessage());
        isCompleted = true;
        success = false;
    }

    @Override
    public void onCompleted() {
        System.out.println("Stream completed");
        isCompleted = true;
        success = true;
    }
}
