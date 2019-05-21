package blockChainService;


import buaa.jj.accountservice.api.AccountService;
import buaa.jj.accountservice.exceptions.AccountServiceException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShutDownWork extends Thread{
    @Override
    public void run() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("outerConsumer.xml");
        context.start();
        AccountService accountService = (AccountService) context.getBean(AccountService.class);
        try {
            accountService.BlockChainServiceClosing();
            System.out.println("Service closed");
        } catch (Exception e) {
            if (e instanceof AccountServiceException) {
                e.printStackTrace();
            }
        }
    }
}
