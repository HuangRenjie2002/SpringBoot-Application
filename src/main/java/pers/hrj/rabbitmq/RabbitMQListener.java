package pers.hrj.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RabbitListener(queues = "Queue")
public class RabbitMQListener {

    @RabbitHandler
    public void handler( Object message){
        Message message1 = (Message)message;
        System.out.println(new String(message1.getBody(), StandardCharsets.UTF_8));
    }

}
