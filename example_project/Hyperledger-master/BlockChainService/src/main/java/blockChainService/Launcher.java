package blockChainService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutDownWork());

        Main main = new Main();
        main.start();
        ServiceReadyWork readyWork = new ServiceReadyWork();
        readyWork.start();
    }
}
