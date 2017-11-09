package ljx.com.ashin.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * MQ的消费者
 * Created by AshinLiang on 2017/10/29.
 */
@Service
public class MqConsumer {
    @Resource
    private JmsTemplate jmsTemplate;

    /**
     * 接收消息
     */
    public String reviceMsg(){
        String msg = (String) jmsTemplate.receiveAndConvert();
        System.out.println("接收到的消息为："+msg);
        return msg;
    }
}
