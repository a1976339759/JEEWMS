//package com.base.modules.cloud.rabbitmq;
//
//import com.base.modules.cloud.constant.CloudConstant;
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import com.base.boot.starter.rabbitmq.core.BaseRabbiMqHandler;
//import com.base.boot.starter.rabbitmq.listenter.MqListener;
//import com.base.common.annotation.RabbitComponent;
//import com.base.common.base.BaseMap;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//
///**
// * RabbitMq接受者3
// * （@RabbitListener声明类方法上，一个类可以多监听多个队列）
// */
//@Slf4j
//@RabbitComponent(value = "helloReceiver3")
//public class HelloReceiver3 extends BaseRabbiMqHandler<BaseMap> {
//
//    @RabbitListener(queues = CloudConstant.MQ_BASE_PLACE_ORDER)
//    public void onMessage(BaseMap baseMap, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
//        super.onMessage(baseMap, deliveryTag, channel, new MqListener<BaseMap>() {
//            @Override
//            public void handler(BaseMap map, Channel channel) {
//                //业务处理
//                String orderId = map.get("orderId").toString();
//                log.info("MQ Receiver3，orderId : " + orderId);
//            }
//        });
//    }
//
//}