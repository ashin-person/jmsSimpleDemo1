package ljx.com.ashin.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息订阅者
 * Created by AshinLiang on 2017/10/27.
 */
public class JmsConsumerTopi {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session =  connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("My-topic");

            MessageConsumer messageConsumer = session.createConsumer(destination);

            for (int i = 0; i < 3; i++) {
                System.out.println("即将接收消息"+i);
                MapMessage message = (MapMessage) messageConsumer.receive();

                String vaule = message.getStringProperty("key"+i);
                System.out.println("接收到的信息为："+vaule);
            }
            //提交
            session.commit();
            //关闭
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
