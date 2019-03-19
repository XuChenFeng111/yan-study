package cn.yan.study.springboot.rabbitmq.receiver;

import cn.yan.study.springboot.rabbitmq.domain.MyMsg;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by gentlemen_yan on 2019/3/2.
 */
@Component
public class HeadersMsgReceiver {
    //配置监听的哪一个队列，同时在没有queue和exchange的情况下会去创建并建立绑定关系
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "my.exchange.headers",durable = "true"),
            exchange = @Exchange(name="my.exchange.headers",durable = "true",type = "direct"),
            key = "my.exchange.headers"
    )
    )
    @RabbitHandler//如果有消息过来，在消费的时候调用这个方法
    public void onOrderMessage(@Payload String msg, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        //消费者操作
        System.out.println("---------Headers 收到消息，开始消费---------");
        System.out.println(JSON.toJSONString(msg));
    }
}