package pers.hrj.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue DirectQueue() {
        Map<String, Object> args = new HashMap<>();
        //设置消息有效期，消息到期未被消费，就胡进入到死信交换机，并由死信交换机路由到死信队列
//        args.put("x-message-ttl", 10000);
        //指定死信交换机
        args.put("x-dead-letter-exchange", "dlx.exchange");
        //指定死信队列
        args.put("x-dead-letter-routing-key", "dlx.queue");
        return new Queue("Queue", true, false, false,args);
    }

    @Bean
    DirectExchange DirectExchange() {
        return new DirectExchange("Exchange", true, false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(DirectQueue()).to(DirectExchange()).with("test");
    }

    /**
     * 死信队列与死信交换机的连接
     * @return
     */
    @Bean
    Binding dlxBinding() {
        return BindingBuilder
                .bind(dlxQueue())
                .to(dlxDirectExchange())
                .with("dlx");
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    Queue dlxQueue() {
        return new Queue("dlx.queue", true, false, false);
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    DirectExchange dlxDirectExchange() {
        return new DirectExchange("dlx.exchange", true, false);
    }




//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//        return rabbitTemplate;
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }


}
