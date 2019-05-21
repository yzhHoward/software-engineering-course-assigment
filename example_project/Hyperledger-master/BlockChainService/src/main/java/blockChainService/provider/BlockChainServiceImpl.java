package blockChainService.provider;

import blockChainService.api.BlockChainService;
import blockChainService.provider.skyvotfabricsdk.ChaincodeManager;
import org.apache.log4j.Logger;



import java.util.Map;
public class BlockChainServiceImpl implements BlockChainService {

    private static String QUERY_TRA_FUNC="queryTransaction";
    private static String QUERY_BC_FUNC="queryBalanceChange";
    private static String INSERT_TRA_FUNC="createTransaction";
    private static String INSERT_BC_FUNC="createBalanceChange";

    private FabricManager fabricmanager;
    private ChaincodeManager chaincodeManager;
    // private static Logger log = Logger.getLogger(BlockChainService.class);

    public String QueryTransaction(String recordId)
    {
        System.out.println("QueryTransaction "+ recordId);
        return Query(recordId, QUERY_TRA_FUNC);

    }
    public String QueryBalanceChange(String recordId)
    {
        System.out.println("QueryBalanceChange "+recordId);
        return Query(recordId, QUERY_BC_FUNC);
    }

    private String Query(String recordId, String queryFunc) {
        try{
            if(fabricmanager==null)fabricmanager= FabricManager.obtain();
            if(chaincodeManager==null)chaincodeManager=fabricmanager.getManager();
            String args[]=new String[1];
            args[0]=recordId;
            Map<String, String> resultMap = chaincodeManager.query(queryFunc,args);
            if(resultMap.get("code")=="success")
            {
                return resultMap.get("data");
            }
            else
            {
                return null;
            }
        }catch (Exception e)
        {
            //log.debug(e.getMessage());
            return null;
        }
    }

    public boolean InsertTransaction(
            String recordId,
            int paymentInstitutionId,
            int paymentUserId,
            int collectionInstitutionId,
            int collectionUserId,
            String dateTime,
            boolean transactionType,
            double sum)
    {
        System.out.println("InsertTransaction");
        try{
            if(fabricmanager==null)fabricmanager= FabricManager.obtain();
            if(chaincodeManager==null)chaincodeManager=fabricmanager.getManager();
            String args[]=new String[8];
            args[0]=recordId;
            args[1]=Integer.toString(paymentInstitutionId);
            args[2]=Integer.toString(paymentUserId);
            args[3]=Integer.toString(collectionInstitutionId);
            args[4]=Integer.toString(collectionUserId);
            args[5]=dateTime;
            args[6]=Boolean.toString(transactionType);
            args[7]=Double.toString(sum);

            Map<String, String> resultMap = chaincodeManager.invoke(INSERT_TRA_FUNC,args);
            if(resultMap.get("code")=="success")
            {
                System.out.println("InsertTransaction success "+recordId);
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e)
        {
            //    log.debug(e.getMessage());
            return false;
        }
    };
    public boolean InsertBalanceChange(
            String recordId,
            int institutionId,
            int userId,
            String dateTime,
            boolean recordType,
            double sum)
    {
        System.out.println("InsertBalanceChange");
        try{
            if(fabricmanager==null)fabricmanager= FabricManager.obtain();
            if(chaincodeManager==null)chaincodeManager=fabricmanager.getManager();
            String args[]=new String[6];
            args[0]=recordId;
            args[1]=Integer.toString(institutionId);
            args[2]=Integer.toString(userId);
            args[3]=dateTime;
            args[4]=Boolean.toString(recordType);
            args[5]=Double.toString(sum);

            Map<String, String> resultMap = chaincodeManager.invoke(INSERT_BC_FUNC,args);
            if(resultMap.get("code")=="success")
            {
                System.out.println("InsertBalanceChange success "+recordId);
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e)
        {
            //   log.debug(e.getMessage());
            return false;
        }

    };
}