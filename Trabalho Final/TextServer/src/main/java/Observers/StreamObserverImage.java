package Observers;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import io.grpc.stub.StreamObserver;
import textservice.ImageReply;
import textservice.ImageRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class StreamObserverImage implements StreamObserver<ImageRequest> {


    private final StreamObserver<ImageReply> imageReplyStreamObserver;
    String imageIden = "Imagem-";
    int imageId;
    int sessionId;

    String linguagem;
    //Acede ao storage de forma a colocar a imagem la antes de ser processada pelo pub/sub
    GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("D:\\Documentos\\Universidade\\6 Semestre\\Computacao na Nuvem\\Trabalhos_CN\\Lab3\\g05-leirt61d-8a5f23de8d8d.json"));
    //GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\asus\\Desktop\\CN\\Trabalhos_CN\\Lab3\\g05-leirt61d-8270b4aba338.json"));
    StorageOptions storageOptions = StorageOptions.newBuilder().setCredentials(credentials).build();
    Storage storage = storageOptions.getService();
    String projectID = storageOptions.getProjectId();

    public StreamObserverImage(StreamObserver<ImageReply> imageReplyStreamObserver) throws IOException {
        this.imageReplyStreamObserver = imageReplyStreamObserver;
    }

    @Override
    public void onNext(ImageRequest value) {
        sessionId = value.getSessionId();
        imageId = value.getImageId();
        linguagem = value.getTranslationId();
        //Cria o blob caso ainda nao esteja criado
        //Lab3 ForumServiceClient
        BlobId blobId = BlobId.of("trabcn-bucket-g06", imageIden + sessionId + "_" + imageId + "_" + linguagem);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(value.getContentType()).build();
        //Valor introduzido ao acaso, como ja tinha sido utilizado em client sendImage na parte de enviar imagem completa
        if (value.getMaxSize() != 2000) {
            //slide storage parte 2 Slide 15
            try (WriteChannel writeChannel = storage.writer(blobInfo)) {
                try {
                    writeChannel.write(ByteBuffer.wrap(value.getBytesSent().toByteArray(), 0, value.getMaxSize()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            storage.create(blobInfo, value.getBytesSent().toByteArray());
        }
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("Error in StreamObserverImageRequest");
    }

    @Override
    public void onCompleted() {
        ImageReply imageReply = ImageReply.newBuilder().setImageId(imageIden + sessionId + "_" + imageId + "_" + linguagem).build();
        imageReplyStreamObserver.onNext(imageReply);
        imageReplyStreamObserver.onCompleted();
    }
}

