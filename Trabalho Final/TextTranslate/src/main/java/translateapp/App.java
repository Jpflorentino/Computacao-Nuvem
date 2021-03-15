package translateapp;

import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.TopicName;
import translateapp.utils.MessageReceiverHandler;

import java.io.IOException;
import java.util.Scanner;

public class App {

    //Criamos o translate seguindo o mesmo modelo do OCR
    //Dois subscribers que iram aceder à informacao e trata-la
    public static final String PROJECT_ID = "g05-leirt61d";

    private static Subscriber VMfree() throws IOException {
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(PROJECT_ID,"free-translate-message");

        ExecutorProvider executorProvider = InstantiatingExecutorProvider.newBuilder()
                .setExecutorThreadCount(1)
                .build();

        Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, new MessageReceiverHandler())
                .setExecutorProvider(executorProvider).build();

        subscriber.startAsync().awaitRunning();
        return subscriber;
    }

    private static Subscriber VMPremium() throws IOException {
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(PROJECT_ID,"premium-translate-message");

        ExecutorProvider executorProvider = InstantiatingExecutorProvider.newBuilder()
                .setExecutorThreadCount(2)
                .build();

        Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, new MessageReceiverHandler())
                .setExecutorProvider(executorProvider).build();

        subscriber.startAsync().awaitRunning();
        return subscriber;
    }

    public static void main(String[] args) throws IOException {
        /*
        VMfree();
        VMPremium();
        Scanner stop = new Scanner(System.in);
        String press = stop.nextLine();
         */
        System.out.println(args[0]);
        if(args[0].equals("free")) {
            VMfree().awaitTerminated();
        }else if(args[0].equals("premium")) {
            VMPremium().awaitTerminated();
        }
    }
}
