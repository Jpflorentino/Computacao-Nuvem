package Utils;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

public class MessageReceiveLoggerHandler implements MessageReceiver {
    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        System.out.println("Message (Id: " + pubsubMessage.getMessageId() +
                "Data: " + pubsubMessage.getData().toStringUtf8() + ")");
        ackReplyConsumer.ack();
    }
}
