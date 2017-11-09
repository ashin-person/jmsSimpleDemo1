package ljx.com.ashin.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * ActiveMQ消息发送者
 * Created by AshinLiang on 2017/10/29.
 */
@Service
public class MqProducer {
    @Resource
    private JmsTemplate jmsTemplate;

    /**
     * 发送消息
     * @param msg
     * @return boolean 发送消息是否成功
     */
    public void sendMsg(final String msg){
        try {
            jmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage();
                    System.out.println("发送消息:"+msg);
                    textMessage.setText(msg);
                    return textMessage;
                }
            });
        }catch (Exception e){
            System.out.println("发送消息出错了:"+e.getMessage());
        }
    }

}
