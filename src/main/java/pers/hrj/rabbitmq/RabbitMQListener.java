package pers.hrj.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pers.hrj.model.entity.User;

@Component
@RabbitListener(queues = "Queue")
public class RabbitMQListener {

    @RabbitHandler
    public void handler(User user){
        System.out.println(user);
    }

}
