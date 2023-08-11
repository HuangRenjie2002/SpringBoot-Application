package pers.hrj.rabbitmq;

import com.alibaba.fastjson2.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hrj.model.entity.User;
import pers.hrj.model.vo.SimpleRestVo;
import pers.hrj.service.UserService;

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
        //直接发送实体类需要序列化
//        rabbitTemplate.convertAndSend("Exchange","test",user);
//
        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setDeliveryTag(1L);
//        messageProperties.setExpiration("10000");
//        messageProperties.setContentType("application/json");
        Message message = new Message(JSON.toJSONString(user).getBytes(),messageProperties );
        rabbitTemplate.convertAndSend("Exchange","test",message);
        return SimpleRestVo.successVo();
    }
}
