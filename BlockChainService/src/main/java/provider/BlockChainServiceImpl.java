package provider;

import api.BlockChainService;
import provider.fabricSdk.ChaincodeManager;
import utils.exceptions.ReadFailureException;
import utils.exceptions.WriteFailureException;

import java.util.Map;

public class BlockChainServiceImpl implements BlockChainService {
    private static String INVOKE_USER_INFO = "insert_user_info";
    private static String QUERY_USER_INFO = "query_user_info";
    private static String INVOKE_FINANCING_APPLY = "insert_financing_apply_record";
    private static String QUERY_FINANCING_APPLY = "query_financing_apply_record";
    private static String INVOKE_CONTRACT = "insert_contract_record";
    private static String QUERY_CONTRACT = "query_contract_record";
    private static String INVOKE_LOAN = "insert_loan_record";
    private static String QUERY_LOAN = "query_loan_record";
    private static String INVOKE_REPAYMENT = "insert_repayment_record";
    private static String QUERY_REPAYMENT = "query_repayment_record";

    private FabricManager fabricmanager;
    private ChaincodeManager chaincodeManager;

    @Override
    public void invokeUserInformation(String userName, String encrypted_message) throws WriteFailureException {
        boolean result = insert(INVOKE_USER_INFO, userName, encrypted_message);
        if (result) {
            System.out.println("InvokeUserInformation succeed, username=" + userName);
        } else {
            System.out.println("InvokeUserInformation failed, username=" + userName);
            throw new WriteFailureException();
        }
    }

    @Override
    public String queryUserInformation(String userName) throws ReadFailureException {
        String result = query(QUERY_USER_INFO, userName);
        if (result != null) {
            System.out.println("QueryUserInformation succeed, username=" + userName);
        } else {
            System.out.println("QueryUserInformation failed, username=" + userName);
            throw new ReadFailureException();
        }
        return result;
    }

    @Override
    public void invokeFinancingApply(long recordId, String encrypted_message) throws WriteFailureException {
        boolean result = insert(INVOKE_FINANCING_APPLY, String.valueOf(recordId), encrypted_message);
        if (result) {
            System.out.println("InvokeFinancingApply succeed, recordId=" + recordId);
        } else {
            System.out.println("InvokeFinancingApply failed, recordId=" + recordId);
            throw new WriteFailureException();
        }
    }

    @Override
    public String queryFinancingApply(long recordId) throws ReadFailureException {
        String result = query(QUERY_FINANCING_APPLY, String.valueOf(recordId));
        if (result != null) {
            System.out.println("QueryFinancingApply succeed, recordId=" + recordId);
        } else {
            System.out.println("QueryFinancingApply failed, recordId=" + recordId);
            throw new ReadFailureException();
        }
        return result;
    }

    @Override
    public void invokeContract(long recordId, String encrypted_message) throws WriteFailureException {
        boolean result = insert(INVOKE_CONTRACT, String.valueOf(recordId), encrypted_message);
        if (result) {
            System.out.println("InvokeContract succeed, recordId=" + recordId);
        } else {
            System.out.println("InvokeContract failed, recordId=" + recordId);
            throw new WriteFailureException();
        }
    }

    @Override
    public String queryContract(long recordId) throws ReadFailureException {
        String result = query(QUERY_CONTRACT, String.valueOf(recordId));
        if (result != null) {
            System.out.println("QueryContract succeed, recordId=" + recordId);
        } else {
            System.out.println("QueryContract failed, recordId=" + recordId);
            throw new ReadFailureException();
        }
        return result;
    }

    @Override
    public void invokeLoan(long recordId, String encrypted_message) throws WriteFailureException {
        boolean result = insert(INVOKE_LOAN, String.valueOf(recordId), encrypted_message);
        if (result) {
            System.out.println("InvokeLoan succeed, recordId=" + recordId);
        } else {
            System.out.println("InvokeLoan failed, recordId=" + recordId);
            throw new WriteFailureException();
        }
    }

    @Override
    public String queryLoan(long recordId) throws ReadFailureException {
        String result = query(QUERY_LOAN, String.valueOf(recordId));
        if (result != null) {
            System.out.println("QueryLoan succeed, recordId=" + recordId);
        } else {
            System.out.println("QueryLoan failed, recordId=" + recordId);
            throw new ReadFailureException();
        }
        return result;
    }

    @Override
    public void invokeRepayment(long recordId, String encrypted_message) throws WriteFailureException {
        boolean result = insert(INVOKE_REPAYMENT, String.valueOf(recordId), encrypted_message);
        if (result) {
            System.out.println("InvokeFinancingApply succeed, recordId=" + recordId);
        } else {
            System.out.println("InvokeFinancingApply failed, recordId=" + recordId);
            throw new WriteFailureException();
        }
    }

    @Override
    public String queryRepayment(long recordId) throws ReadFailureException {
        String result = query(QUERY_REPAYMENT, String.valueOf(recordId));
        if (result != null) {
            System.out.println("QueryRepayment succeed, recordId=" + recordId);
        } else {
            System.out.println("QueryRepayment failed, recordId=" + recordId);
            throw new ReadFailureException();
        }
        return result;
    }

    private boolean insert(String invokeFunc, String arg, String encrypted_message) {
        try {
            if (fabricmanager == null) {
                fabricmanager = FabricManager.obtain();
            }
            if (chaincodeManager == null) {
                chaincodeManager = fabricmanager.getCh aincodeManager();
            }
            String[] args = new String[2];
            args[0] = arg;
            args[1] = encrypted_message;
            Map<String, String> resultMap = chaincodeManager.invoke(invokeFunc, args);
            return resultMap.get("code").equals("success");
        } catch (Exception e) {
            //    log.debug(e.getMessage());
            return false;
        }
    }

    private String query(String queryFunc, String arg) {
        try {
            if (fabricmanager == null) {
                fabricmanager = FabricManager.obtain();
            }
            if (chaincodeManager == null) {
                chaincodeManager = fabricmanager.getChaincodeManager();
            }
            String[] args = new String[1];
            args[0] = arg;
            Map<String, String> resultMap = chaincodeManager.query(queryFunc, args);
            if (resultMap.get("code").equals("success")) {
                return resultMap.get("data");
            } else {
                return null;
            }
        } catch (Exception e) {
            //log.debug(e.getMessage());
            return null;
        }
    }
}