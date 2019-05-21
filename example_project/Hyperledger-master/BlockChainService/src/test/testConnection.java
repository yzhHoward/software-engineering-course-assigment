import blockChainService.api.BlockChainService;
import blockChainService.provider.BlockChainServiceImpl;
import blockChainService.provider.FabricManager;
import org.junit.After;
import org.junit.jupiter.api.*;

public class testConnection {

    static BlockChainService bcs = new BlockChainServiceImpl();
    @DisplayName("test for insert transaction")
    @Test
    public void insertTransaction() throws InterruptedException {
        bcs.InsertTransaction("1000",100,12,34,23,"2018",true,23.33);
    }

    @DisplayName("test for query transaction")
    @Test
    public void queryTransaction() throws InternalError, InterruptedException {
        Thread.sleep(5000);
        System.out.println(bcs.QueryTransaction("1000"));

    }

    @DisplayName("Test for insert balance change")
    @Test
    public void insertBalanceChange() throws InterruptedException {
        bcs.InsertBalanceChange("2000",123,234,"2018",true,232.234);
        Thread.sleep(5000);
    }

    @DisplayName("Test for query balance change")
    @Test
    public void queryBalanceChange() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(bcs.QueryTransaction("2018061621583222051071"));
    }
}