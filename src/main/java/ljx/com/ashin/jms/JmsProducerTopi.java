package ljx.com.ashin.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 * Created by AshinLiang on 2017/10/24.
 */
public class JmsProducerTopi {
    public static void main(String[] args) {
        //创建工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        try {
            //创建连接
            Connection connection = connectionFactory.createConnection();
            //开启连接
            connection.start();
            //创建会话
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建主题
            Destination destination = session.createTopic("My-topic");
            //创建生产者
            MessageProducer producer = session.createProducer(destination);

            for (int i = 0; i < 3; i++) {
                MapMessage message = session.createMapMessage();
                message.setStringProperty("key"+i,"value"+i);
                producer.send(message);
            }

            session.commit();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
