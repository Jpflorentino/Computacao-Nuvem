package visionapp;

import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.TopicName;
import visionapp.utils.MessageReceiverHandler;

import java.io.IOException;

public class App {

    public static final String PROJECT_ID = "g05-leirt61d";

    //Aceder à subscricão que tem o texto detetado para tratar o texto e publica-lo num novo topico que ira ser lido pelo translate
    private static Subscriber VMfree() throws IOException {
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(PROJECT_ID, "free-ocr-message");
        //Como dito no enunciado, apenas corre numa vm
        //Solucao encontrada em faq.txt na pergunta 3
        ExecutorProvider executorProvider = InstantiatingExecutorProvider.newBuilder()
                .setExecutorThreadCount(1)
                .build();

        TopicName topicName = TopicName.ofProjectTopicName(PROJECT_ID, "free-translate");
        Publisher publisher = Publisher.newBuilder(topicName).build();
        Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, new MessageReceiverHandler(publisher))
                .setExecutorProvider(executorProvider)
                .build();
        subscriber.startAsync().awaitRunning();
        return subscriber;
    }

    private static Subscriber VMPremium() throws IOException {
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(PROJECT_ID, "premium-ocr-message");
        //Como dito no enunciado, corre em varias VMs
        ExecutorProvider executorProvider = InstantiatingExecutorProvider.newBuilder()
                .setExecutorThreadCount(2)
                .build();

        TopicName topicName = TopicName.ofProjectTopicName(PROJECT_ID, "premium-translate");
        Publisher publisher = Publisher.newBuilder(topicName).build();
        Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, new MessageReceiverHandler(publisher))
                .setExecutorProvider(executorProvider)
                .build();
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

        //Solucao encontrada em faq em relacao a awaitTerminated() e o args 0 passamos como  argumento e tipo de vm que pretendemos correr
        //Quando corremos uma vm de forma a saber a qual se ira conectar a api
        System.out.println(args[0]);
        if (args[0].equals("free")) {
            VMfree().awaitTerminated();
        } else if (args[0].equals("premium")) {
            VMPremium().awaitTerminated();
        }
    }
}
