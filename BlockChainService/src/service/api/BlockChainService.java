package service.api;

import service.utils.exceptions.ReadFailureException;
import service.utils.exceptions.WriteFailureException;

public interface BlockChainService {
    // 完成实名
    void invokeUserInformation(String userName, String encrypted_message) throws WriteFailureException;

    String queryUserInformation(String userName) throws ReadFailureException;

    // 申请融资
    void invokeFinancingApply(long recordId, String encrypted_message) throws WriteFailureException;

    String queryFinancingApply(long recordId) throws ReadFailureException;

    // 签署合同
    void invokeContract(long recordId, String encrypted_message) throws WriteFailureException;

    String queryContract(long recordId) throws ReadFailureException;

    // 完成放款
    void invokeLoan(long recordId, String encrypted_message) throws WriteFailureException;

    String queryLoan(long recordId) throws ReadFailureException;

    // 完成还款
    void invokeRepayment(long recordId, String encrypted_message) throws WriteFailureException;

    String queryRepayment(long recordId) throws ReadFailureException;
}
