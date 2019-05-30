import provider.BlockChainServiceImpl;
import utils.exceptions.ReadFailureException;

public class Main {
    public static void main(String[] args) {
        BlockChainServiceImpl blockChainService = new BlockChainServiceImpl();
        try {
            blockChainService.invokeFinancingApply(500, "world");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(blockChainService.queryFinancingApply(500));
        } catch (ReadFailureException e) {
            e.printStackTrace();
        }
        //System.out.println(blockChainService.InsertTransaction("1000", 100, 12, 34, 23, "2018", true, 23.33));
        //System.out.println((blockChainService.QueryTransaction("1000")));
    }
}
