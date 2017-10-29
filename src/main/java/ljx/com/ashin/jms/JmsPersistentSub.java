package ljx.com.ashin.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息订阅者
 * Created by AshinLiang on 2017/10/28.
 */
public class JmsPersistentSub {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        try {
            Connection connection = connectionFactory.createConnection();
            //设置连接的客户端的id
            connection.setClientID("client1");
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            Topic destination = session.createTopic("my-persistent-topic");
            TopicSubscriber topicSubscriber = session.createDurableSubscriber(destination,"c1");
            connection.start();

            //接收消息，该方法会阻塞
            Message message =  topicSubscriber.receive();
            System.out.println("接收消息");
            while (message!=null){
                TextMessage textMessage = (TextMessage)message;
                session.commit();
                String value = textMessage.getText();
                System.out.println("接收到的信息为:"+value);
                message = topicSubscriber.receive(1000l);//获取下一条消息
            }

            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
