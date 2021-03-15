import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import forum.ForumMessage;
import io.grpc.stub.StreamObserver;

import java.nio.file.Paths;

public class FMStreamObserver implements StreamObserver<ForumMessage> {

    private Storage storage;
    private String directory;

    FMStreamObserver(Storage storage, String directory) {
        this.storage = storage;
        this.directory = directory;
    }

    @Override
    public void onNext(ForumMessage forumMessage) {
        //<texto> [ ;<bucket>;<blob> ]
        String msn = forumMessage.getTxtMsg();
        String[] msnSplit = msn.split(";");
        if (msnSplit.length == 3) {
            String bucketStr = msnSplit[1];
            String blobStr = msnSplit[2];

            Bucket bucket = storage.get(bucketStr);
            Blob blob = bucket.get(blobStr);
            blob.downloadTo(Paths.get(directory + "\\" + blobStr));
        }
        System.out.println("\n" + "*********New Message*********" + "\n" +
                "Mensagem de: " + forumMessage.getFromUser() + "\n" +
                "Topic Name: " + forumMessage.getTopicName() + "\n" +
                msnSplit[0]);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage() + "\n");
    }

    @Override
    public void onCompleted() {

    }
}
