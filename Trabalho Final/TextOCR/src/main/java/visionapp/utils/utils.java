package visionapp.utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.ReadChannel;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import visionapp.models.DetectedText;
import visionapp.models.DetectedTextDoc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class utils {
    //Funcao criada pelo professor em vision-api (exemplo)
    public static String detectLanguage(Translate translate, String text) {
        String detectedLanguage = "und";
        Detection detection = translate.detect(text);
        detectedLanguage = detection.getLanguage();
        System.out.println(text + ":" + detectedLanguage + ":" + detection.getConfidence());
        return detectedLanguage;
    }

    //Funcao criada pelo professor
    public static List<DetectedText> detectText(Translate translate, String filePath) throws IOException {
        List<DetectedText> allText = null;

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));
        Image img = Image.newBuilder().setContent(imgBytes).build();
        // https://cloud.google.com/vision/docs/features-list
        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();

        List<AnnotateImageRequest> requests = new ArrayList<>();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feat).setImage(img).build();
        requests.add(request);
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.println("Error:" + res.getError().getMessage());
                    return null;
                }
                // For full list of available annotations, see http://g.co/cloud/vision/docs
                allText = new ArrayList<DetectedText>();
                for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                    DetectedText dt = new DetectedText();
                    dt.text = annotation.getDescription();
                    dt.language = detectLanguage(translate, dt.text);
                    dt.poly = annotation.getBoundingPoly();
                    allText.add(dt);
                }
            }
        }
        return allText;
    }

    //Aceder ao blob de storage de forma a conseguir tratar a imagem
    public static void downloadImageFromBucket(String blobImageName, Storage storage) throws IOException {
        BlobId blobId = BlobId.of("trabcn-bucket-g06", blobImageName);
        Blob blob = storage.get(blobId);
        if (blob == null) {
            System.out.println("No such Blob exists !");
            return;
        }
        //String absFileName = "D:\\Documentos\\Universidade\\6 Semestre\\Computacao na Nuvem\\Trabalhos_CN\\TrabFinal\\ImagesOCR\\" + blobImageName;
        String absFileName = "/var/forum/" + blobImageName;
        Path downloadTo = Paths.get(absFileName);

        PrintStream writeTo = new PrintStream(new FileOutputStream(downloadTo.toFile()));
        //parte 2 storage slide 16/17
        if (blob.getSize() < 1_000_000) {
            // Blob is small read all its content in one request
            byte[] content = blob.getContent();
            writeTo.write(content);
        } else {
            // When Blob size is big or unknown use the blob's channel reader.
            try (ReadChannel reader = blob.reader()) {
                WritableByteChannel channel = Channels.newChannel(writeTo);
                ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
                while (reader.read(bytes) > 0) {
                    bytes.flip();
                    channel.write(bytes);
                    bytes.clear();
                }
            }
        }
        writeTo.close();
        blob.delete();
    }

    //Lab4 insertDocuments em firestore
    public static void insertDocuments(String pathnameCSV, DetectedText detectedText, Firestore db, String collectionName) throws Exception {
        CollectionReference colRef = db.collection(collectionName);
        DocumentReference docRef = colRef.document(pathnameCSV);
        DetectedTextDoc detectedTextDoc = new DetectedTextDoc();
        detectedTextDoc.language = detectedText.language;
        detectedTextDoc.text = detectedText.text;
        detectedTextDoc.translatedText = "";
        ApiFuture<WriteResult> result = docRef.set(detectedTextDoc);

        System.out.println("Documento inserido com sucesso!!");
    }
}
