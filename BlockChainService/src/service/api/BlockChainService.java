package service.api;

import service.utils.exceptions.ReadFailureException;
import service.utils.exceptions.WriteFailureException;

public interface BlockChainService {
    /*
    String QueryTransaction(String recordId);
    String QueryBalanceChange(String recordId);
    boolean InsertTransaction(
            String recordId,
            int paymentInstitutionId,
            int paymentUserId,
            int collectionInstitutionId,
            int collectionUserId,
            String dateTime,
            boolean transactionType,
            double sum
    );
    boolean InsertBalanceChange(
            String recordId,
            int institutionId,
            int userId,
            String dateTime,
            boolean recordType,
            double sum
    );
    */

    void setUserName(String userId, String encrypted_message) throws WriteFailureException;

    String getUserName(String userId) throws ReadFailureException;

    void setFinancingApply(String userId, String encrypted_message) throws WriteFailureException;

    String getFinancingApply(String userId) throws ReadFailureException;

    void setContract(String userId, String encrypted_message) throws WriteFailureException;

    String getContract(String userId) throws ReadFailureException;

    void setLoan(String userId, String encrypted_message) throws WriteFailureException;

    String getLoan(String userId) throws ReadFailureException;

    void setRepayment(String userId, String encrypted_message) throws WriteFailureException;

    String getRepayment(String userId) throws ReadFailureException;
}