package pers.hrj.rabbitmq;

import com.alibaba.fastjson2.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hrj.model.entity.User;
import pers.hrj.model.vo.SimpleRestVo;
import pers.hrj.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@RequestMapping("/rabbitMq")
public class RabbitMQController {

    @Autowired
    UserService userService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/get")
    public SimpleRestVo get(){
        User user = userService.getBaseMapper().selectById(1686311752046559233L);
//        rabbitTemplate.convertAndSend("Exchange","test",user);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("10000");
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Message message = new Message(JSON.toJSONString(user).getBytes(StandardCharsets.UTF_8), messageProperties);

        rabbitTemplate.convertAndSend("Exchange","test",message,correlationData);
        return SimpleRestVo.successVo();
    }
}
