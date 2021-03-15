package translateapp.utils;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.pubsub.v1.PubsubMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import static translateapp.utils.utils.translateText;

public class MessageReceiverHandler implements MessageReceiver {
    //Utilizamos getApplicationDefault pois nao podia ser  usado o caminho devido a esta api estar a correr numa maquina virtual
    //externa ao pc utilizado
    GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
    //GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\asus\\Desktop\\CN\\Trabalhos_CN\\Lab3\\g05-leirt61d-8270b4aba338.json"));
    FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder().setCredentials(credentials).build();
    Firestore firestore = firestoreOptions.getService();

    public MessageReceiverHandler() throws IOException {
    }

    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        try {
            String imageName = pubsubMessage.getData().toStringUtf8();
            String languageDetected = pubsubMessage.getAttributesOrThrow("Detected");
            String languageTranslate = pubsubMessage.getAttributesOrThrow("TranslationLanguage");
            String textAfterTranslate;

            CollectionReference collectionReference = firestore.collection("TextDetected");
            DocumentReference documentReference = collectionReference.document(imageName);

            ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = documentReference.get();
            DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();
            //Acede ao firestore de forma a observar se o texto selecionado pelo utilizador é igual á linguagem
            //pedida pelo utilizador para traduzir
            String textDetected = documentSnapshot.getString("text");

            Translate translate = TranslateOptions.getDefaultInstance().getService();
            //Se a linguagem nao for igual à detetada e se o texto nao tiver vazio entao segue com o processo de traducao
            //Ou entao o texto traduzido fica igual ao texto detetado
            if(!languageDetected.equals(languageTranslate) && !languageDetected.equals("und")){
                textAfterTranslate = translateText(translate, textDetected, languageDetected, languageTranslate);
            }else {
                textAfterTranslate = textDetected;
            }

            //Mapa criado pois a funcao update ira receber um mapa
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("translatedText", textAfterTranslate);
            ApiFuture<WriteResult> resultApiFuture = documentReference.update(map);

            ackReplyConsumer.ack();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
