package ljx.com.ashin.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息中间件的生产者
 * Created by AshinLiang on 2017/10/21.
 */
public class JmsProducer {

    public static void main(String[] args) {
        //1.创建工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2.创建并开启连接
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //3.创建会话
            Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //4.创建目的地、provider
            Destination destination = session.createQueue("my-queue");
            MessageProducer producer = session.createProducer(destination);
            //5.发送信息
            System.out.println("开始发送消息");
            for (int i = 0; i < 3; i++) {
             /*   TextMessage textMessage = session.createTextMessage();
                textMessage.setStringProperty("msgTextKey"+i,"msgTextValue"+i);*/
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setString("msgMapKey"+i,"msgMapValue"+i);
                mapMessage.setStringProperty("ashinKey"+i,"ashinValue"+i);

            /*    producer.send(destination,textMessage);*/
                producer.send(destination,mapMessage);
            }
            System.out.println("消息发送完毕，提交内容");
            //6.提交内容
            session.commit();
            //关闭连接
            session.close();
            connection.close();
            System.out.println("连接已经关闭");

        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

}
