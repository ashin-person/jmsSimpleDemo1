import ljx.com.ashin.service.MqConsumer;
import ljx.com.ashin.service.MqProducer;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by AshinLiang on 2017/10/29.
 */
public class MqServiceTest extends BaseTest{

    @Resource
    private MqProducer mqProducer;
    @Resource
    private MqConsumer mqConsumer;

    @Test
    public void testSendMqMsg(){
        String msg = "test Spring jms msg new test";
        mqProducer.sendMsg(msg);
    }

    @Test
    public void testReciveMsg(){
        String msg = mqConsumer.reviceMsg();
    }

}
