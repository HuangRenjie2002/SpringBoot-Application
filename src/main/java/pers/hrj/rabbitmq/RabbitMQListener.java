package pers.hrj.rabbitmq;

import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pers.hrj.model.entity.User;

import java.io.IOException;
import java.util.Map;

@Component

public class RabbitMQListener {

    @RabbitListener(queues = "Queue")
    public void handler(Channel channel, Message message) throws IOException {
        System.out.println(JSONObject.parseObject(new String(message.getBody()), User.class));
        System.out.println(message);
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        try {
            int a = 1/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            e.printStackTrace();
            Integer retryCount;
            if (!headers.containsKey("retry-count")) {
                retryCount = 0;
            } else {
                retryCount = (Integer) headers.get("retry-count");
            }
            if (retryCount++ < 3) {
                headers.put("retry-count", retryCount);
                //重新发送到MQ中
                System.err.println(retryCount);
                AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder().contentType("text/plain").headers(headers).build();
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
                        message.getMessageProperties().getReceivedRoutingKey(), basicProperties,
                        message.getBody());
            }else {

                channel.basicPublish("dlx.exchange",
                        "dlx", MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBody());
            }
        }
    }

}
