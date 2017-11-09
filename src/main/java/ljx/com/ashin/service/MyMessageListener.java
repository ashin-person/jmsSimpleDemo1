package ljx.com.ashin.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 监听器
 * Created by AshinLiang on 2017/10/29.
 */
public class MyMessageListener implements MessageListener{

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        try {
            System.out.println("now 获取到发送的消息为:"+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
