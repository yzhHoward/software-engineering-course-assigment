import org.springframework.context.support.ClassPathXmlApplicationContext;
import blockChainService.api.BlockChainService;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;



public class StressTestingRun  {
    @DisplayName("Stress testing")
    @Test
    public void run() throws InterruptedException {

        StressTestingUnit.init();
        CountDownLatch latch = new CountDownLatch(1);
        List<Thread> testList = new ArrayList<Thread>();
        for(int i=1;i<=2;i++)
        {
            Thread cntt=new StressTestingUnit(latch,i);
            cntt.start();
            testList.add(cntt);
        };
        latch.countDown();
    }
}

class StressTestingUnit extends Thread
{
    CountDownLatch latch;
    int id;
    static ClassPathXmlApplicationContext context;
    static BlockChainService blockChainService;
    static public void init() {
        context = new ClassPathXmlApplicationContext("StressTesting.xml");
        context.start();
        blockChainService = (BlockChainService) context.getBean(BlockChainService.class);
    }
    public StressTestingUnit(CountDownLatch latch,int num)
    {
        this.latch=latch;
        this.id=num;
    }
    public void run()
    {
        try {
            latch.await();
        } catch (Exception e) {
            System.out.println("An exception.");
        }
        System.out.println(this.id);

        System.out.println((blockChainService.QueryTransaction("1000")));
        blockChainService.InsertTransaction("1000",100,12,34,23,"2018",true,23.33);
//        assert(blockChainService.QueryTransaction("2018061621583222051071")!=null);
    }
}
