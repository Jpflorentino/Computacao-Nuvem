import forum.ExistingTopics;
import io.grpc.stub.StreamObserver;

public class TopicStreamObserver implements StreamObserver<ExistingTopics> {
    @Override
    public void onNext(ExistingTopics existingTopics) {
        existingTopics.getTopicNameList().forEach(System.out::println);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage() + "\n");
    }

    @Override
    public void onCompleted() {

    }
}
