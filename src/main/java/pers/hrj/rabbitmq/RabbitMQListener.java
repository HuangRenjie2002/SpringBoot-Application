package pers.hrj.rabbitmq;

import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pers.hrj.model.entity.User;

import java.io.IOException;

@Component

public class RabbitMQListener {

    @RabbitListener(queues = "Queue")
    public void handler(Channel channel, Message message) throws IOException {
        System.out.println(JSONObject.parseObject(new String(message.getBody()), User.class));
        System.out.println(message);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
           e.printStackTrace();
//            channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
//                    message.getMessageProperties().getReceivedRoutingKey(), MessageProperties.PERSISTENT_TEXT_PLAIN,
//                    message.getBody());

        }
    }

}
