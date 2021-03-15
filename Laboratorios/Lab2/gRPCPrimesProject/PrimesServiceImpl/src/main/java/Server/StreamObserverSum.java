package Server;

import io.grpc.stub.StreamObserver;
import operationservice.OperationReply;
import operationservice.OperationRequest;

public class StreamObserverSum implements StreamObserver<OperationRequest> {

    StreamObserver<OperationReply> sFinalreply;
    int total;

    public StreamObserverSum(StreamObserver<OperationReply> sreplies) {
        this.sFinalreply=sreplies;
    }

    @Override
    public void onNext(OperationRequest request) {
        // More one request
        total+=request.getOpRequest1();
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error in StreamObserverSum");
    }

    @Override
    public void onCompleted() {
        OperationReply rply = OperationReply.newBuilder().setOpReply(total).build();
        sFinalreply.onNext(rply);
        sFinalreply.onCompleted();

    }

}
