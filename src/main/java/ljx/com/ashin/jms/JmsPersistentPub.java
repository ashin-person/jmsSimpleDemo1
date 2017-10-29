package ljx.com.ashin.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 持久topic的发布端
 * Created by AshinLiang on 2017/10/28.
 */
public class JmsPersistentPub {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        try {
            Connection connection = connectionFactory.createConnection();

            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic("my-persistent-topic");

            // 创建生产者
            MessageProducer messageProducer = session.createProducer(topic);
            //设置接收模式
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //持久化的消息发布，需要配置完之后才启动连接
            connection.start();
            //发送消息
            for (int i = 0; i < 3; i++) {
                TextMessage message = session.createTextMessage();
//                String key = "presistentKey"+i;
                String value = "persistentValue"+i;
//                message.setStringProperty(key,value);
                message.setText(value);
                System.out.println("发送消息:"+value);
                messageProducer.send(message);
            }

            session.commit();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
