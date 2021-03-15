import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class EmptyStreamObserver implements StreamObserver<Empty> {
    @Override
    public void onNext(Empty empty) {

    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage() + "\n");
    }

    @Override
    public void onCompleted() {

    }
}