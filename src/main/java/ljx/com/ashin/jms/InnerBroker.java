package ljx.com.ashin.jms;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerService;

/**
 * 内嵌的方式启动mq
 * Created by AshinLiang on 2017/10/29.
 */
public class InnerBroker {
    public static void main(String[] args) {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        try {
            //设置地址和端口
            brokerService.addConnector("tcp://localhost:61616");
            //启动
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
