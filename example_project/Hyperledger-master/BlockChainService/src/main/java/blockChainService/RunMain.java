package blockChainService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class RunMain implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        Main main = new Main();
        main.start();
    }
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
