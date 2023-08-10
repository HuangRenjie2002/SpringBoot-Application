package pers.hrj.rabbitmq;

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
//        rabbitTemplate.convertAndSend("Exchange","test",user);


        return SimpleRestVo.successVo();
    }
}
