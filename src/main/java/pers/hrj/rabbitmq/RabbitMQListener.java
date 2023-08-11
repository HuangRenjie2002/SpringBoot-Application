package pers.hrj.rabbitmq;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pers.hrj.model.entity.User;

@Component

public class RabbitMQListener {

    @RabbitListener(queues = "Queue")
    public void handler(Message message) {
        System.out.println(JSONObject.parseObject(new String(message.getBody()), User.class));
        System.out.println(message);
    }

}
