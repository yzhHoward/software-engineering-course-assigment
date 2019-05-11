public class Main {

    public String save_in_chain(String module_source, String encrypted_message) throws WriteFailureException {
        return "";
    }

    public void setNameString (String userId, String encrypted_message) throws WriteFailureException {
    }

    public void getNameString (String userId) throws ReadFailureException {
        return "";
    }

    public String save_in_chain(String module_source, String encrypted_message) throws WriteFailureException {
        return "";
    }

    public String save_in_chain(String module_source, String encrypted_message) throws WriteFailureException {
        return "";
    }
    /*
     * 此API用于将数据存储入区块链中，所有需要存储信息的模块共同调用这一API存储数据
     * @description: write模块，上游调用此API
     * @param module_source: String，信息来源的模块，
     * @param encrypted_message: String，加密的信息编码成的字符串，由上游完成
     * @return label: String, 返回一个字符串形式的label，label作为提取信息的凭证和标识。也就是说，在以后想要从链中提取这一次存进去的数据，需要依靠label作为key来提取。
     * @exception WriteFailureException: 写入失败的时候会抛出我们自定义的WriteFailureException异常
     */

    String fetch_from_chain(String label) throws ReadFailureException {
        return "";
    }
    /*
     * 此API用于从区块链中提取数据，所有需要提取信息的模块共同调用这一API提取数据
     * @description: read模块
     * @param label: String, label的初始来源是save_in_chain API，由上游模块持有
     * @return encrypted_message: String, 是返回输入的label的那次方法调用所存入的数据
     * @exception ReadFailureException: 读取失败的时候会抛出我们自定义的ReadFailureException异常
     */
}