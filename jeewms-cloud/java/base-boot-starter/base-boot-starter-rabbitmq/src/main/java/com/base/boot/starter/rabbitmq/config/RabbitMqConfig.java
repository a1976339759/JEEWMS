package com.base.boot.starter.rabbitmq.config;


import com.base.boot.starter.rabbitmq.event.BaseRemoteApplicationEvent;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * 消息队列配置类
 *
 * @author zyf
 */
@Configuration
@RemoteApplicationEventScan(basePackageClasses = BaseRemoteApplicationEvent.class)
public class RabbitMqConfig {


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        //设置忽略声明异常
        rabbitAdmin.setIgnoreDeclarationExceptions(true);
        return rabbitAdmin;
    }



    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //当前的消费者数量
        container.setConcurrentConsumers(1);
        //最大的消费者数量
        container.setMaxConcurrentConsumers(1);
        //是否重回队列
        container.setDefaultRequeueRejected(true);

        //消费端的标签策略
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String queue) {
                return queue + "_" + UUID.randomUUID().toString();
            }
        });
        return container;
    }

}
