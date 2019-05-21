package blockChainService;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main extends Thread {
    @Override
    public void run() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Dubbo.xml","Beans.xml");
        context.start();
        System.out.println("Service started");
        while (true) {

        }
    }
}

