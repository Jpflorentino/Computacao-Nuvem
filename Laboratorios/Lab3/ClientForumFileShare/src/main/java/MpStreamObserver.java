import com.google.protobuf.Empty;
import forum.ExistingTopics;
import forum.ForumGrpc;
import forum.ForumMessage;
import io.grpc.stub.StreamObserver;

public class MpStreamObserver implements StreamObserver<ExistingTopics> {
    private ForumGrpc.ForumStub noBlockStub;
    private ForumMessage fm;
    private StreamObserver<Empty> stf;

    public MpStreamObserver(ForumGrpc.ForumStub noBlockStub, ForumMessage fm, StreamObserver<Empty> stf) {
        this.noBlockStub = noBlockStub;
        this.fm = fm;
        this.stf = stf;
    }

    @Override
    public void onNext(ExistingTopics existingTopics) {
        if (existingTopics.getTopicNameList().contains(fm.getTopicName())) {
            noBlockStub.messagePublish(fm, stf);
        } else {
            onError(new Exception("Topic does not exist."));
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage() + "\n");
    }

    @Override
    public void onCompleted() {

    }
}
