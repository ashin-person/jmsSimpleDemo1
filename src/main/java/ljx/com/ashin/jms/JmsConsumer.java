package ljx.com.ashin.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * 消息中间件的消费者
 * Created by AshinLiang on 2017/10/21.
 */
public class JmsConsumer {
    public static void main(String[] args) {
        //1.创建工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        try {
            //2.创建并开启连接
            Connection connection = connectionFactory.createConnection();
            System.out.println("开始并创建连接");
            connection.start();

            Enumeration names = connection.getMetaData().getJMSXPropertyNames();
            while (names.hasMoreElements()){
                String name = (String)names.nextElement();
                System.out.println("jms name=="+name);
            }

            //3.创建会话,并连接上消息
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("my-queue");
            //4.创建消费者
            MessageConsumer  consumer = session.createConsumer(destination);
            //获取消息
            int i = 3;
            for (int j = 0; j < i; j++) {
//                TextMessage textMessage = (TextMessage) consumer.receive();
                MapMessage message = (MapMessage) consumer.receive();//receive是一个阻塞方法
                //通知服务器，已经获取了该消息
//                message.getStringProperty()
                String strKey = "msgMapKey"+j;
                System.out.println("strKey:"+strKey);
                System.out.println("接收到消息:"+message.getString(strKey));
                String mapKey = "ashinKey"+j;
                System.out.println("接收到消息:"+message.getStringProperty(mapKey));
                session.commit();

            }
            //关闭会话和连接
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
