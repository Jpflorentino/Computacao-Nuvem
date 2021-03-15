package Server;

import io.grpc.stub.StreamObserver;
import operationservice.OperationReply;
import operationservice.OperationRequest;

public class StreamObserverSumStream implements StreamObserver<OperationRequest> {

    StreamObserver<OperationReply> sreplies;

    public StreamObserverSumStream(StreamObserver<OperationReply> sreplies) {
        this.sreplies = sreplies;
    }

    @Override
    public void onNext(OperationRequest request) {
        // More one request to process and one reply
        if (request.getOpRequest2() == 0) {
            OperationReply rply = OperationReply.newBuilder().setOpReply(request.getOpRequest1()).build();
            sreplies.onNext(rply);
        } else {
            OperationReply rply = OperationReply.newBuilder().setOpReply(request.getOpRequest1() + request.getOpRequest2()).build();
            sreplies.onNext(rply);
        }
        // pode-se armazenar múltiplos pedidos e só serem respondidos
        // quando o cliente fizer OnCompleted
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        // processar eventuais mensagens de pedido recebidas em OnNext
        // e responder com uma ou mais respostas
        // splies.OnNext(OnlyReply);
        sreplies.onCompleted();
    }
}
