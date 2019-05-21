package blockChainService.api;

public interface BlockChainService {
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
}