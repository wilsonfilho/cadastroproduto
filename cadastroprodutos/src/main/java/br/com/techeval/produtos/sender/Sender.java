package br.com.techeval.produtos.sender;


import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;

@Component
@Lazy
public class Sender {
    private final String QUEUE_NAME = "LOG_CADASTRO_PRODUTO";

    @Autowired
    RabbitMessagingTemplate template;

    @Bean
    Queue queue(){
        return new Queue(QUEUE_NAME, false);
    }

    public void send(String message){
        template.convertAndSend(QUEUE_NAME, message);
    }
}


