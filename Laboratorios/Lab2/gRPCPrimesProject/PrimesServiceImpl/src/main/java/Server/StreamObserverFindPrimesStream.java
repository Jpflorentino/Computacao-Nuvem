package Server;

import io.grpc.stub.StreamObserver;
import operationservice.OperationReply;
import operationservice.OperationRequest;

import static Server.Server.isPrime;

public class StreamObserverFindPrimesStream implements StreamObserver<OperationRequest> {

    StreamObserver<OperationReply> sreplies;

    public StreamObserverFindPrimesStream(StreamObserver<OperationReply> sreplies) {
        this.sreplies = sreplies;
    }

    @Override
    public void onNext(OperationRequest request) {
        // More one request to process and one reply
        for (int i = request.getOpRequest1(); i < request.getOpRequest2(); i++) {
            if (isPrime(i)) {
                OperationReply prime = OperationReply.newBuilder().setOpReply(i).build();
                sreplies.onNext(prime);
            }
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
