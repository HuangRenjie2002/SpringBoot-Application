package pers.hrj.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "Queue")
public class RabbitMQListener {
//
//    @RabbitHandler
//    public void handler(Message message){
//        byte[] body = message.getBody();
//        System.out.println(body);
//    }

}
