package Utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;

public class MessageReceiveWorkerHandler implements MessageReceiver {
    Firestore firestore = null;
    int subscribers;

    public MessageReceiveWorkerHandler(Firestore firestore, int subscribers) {
        this.firestore = firestore;
        this.subscribers = subscribers;
    }

    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        CollectionReference collectionReference = firestore.collection("subscription-worker");
        Document document = new Document();
        document.ID = pubsubMessage.getAttributesOrThrow("ID");
        ByteString data = pubsubMessage.getData();
        String[] dataString = data.toStringUtf8().split(",");
        document.message = data.toStringUtf8();
        document.Caixa = dataString[0];
        document.Item = dataString[1];
        document.Quant = dataString[2];
        document.PrecoUnit = dataString[3];
        document.Total = dataString[4];
        document.workerNumber = "Worker Number: " + subscribers;

        DocumentReference documentReference = collectionReference.document(document.ID);
        ApiFuture<WriteResult> writeResultApiFuture = documentReference.set(document);
        System.out.println("Message (Id: " + pubsubMessage.getMessageId() +
                "Data: " + pubsubMessage.getData().toStringUtf8() + ")");
        ackReplyConsumer.ack();
    }
}
