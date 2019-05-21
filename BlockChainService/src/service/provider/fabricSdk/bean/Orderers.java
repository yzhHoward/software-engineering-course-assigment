package service.provider.fabricSdk.bean;

import java.util.ArrayList;
import java.util.List;

public class Orderers {

    /**
     * orderer 排序服务器所在根域名
     */
    private String ordererDomainName;
    /**
     * orderer 排序服务器集合
     */
    private List<Orderer> orderers;

    public Orderers() {
        orderers = new ArrayList<>();
    }

    public String getOrdererDomainName() {
        return ordererDomainName;
    }

    public void setOrdererDomainName(String ordererDomainName) {
        this.ordererDomainName = ordererDomainName;
    }

    /**
     * 新增排序服务器
     */
    public void addOrderer(String name, String location) {
        orderers.add(new Orderer(name, location));
    }

    /**
     * 获取排序服务器集合
     */
    public List<Orderer> get() {
        return orderers;
    }

    /**
     * 排序服务器对象
     */
    public class Orderer {
        /**
         * orderer 排序服务器的域名
         */
        private String ordererName;
        /**
         * orderer 排序服务器的访问地址
         */
        private String ordererLocation;

        public Orderer(String ordererName, String ordererLocation) {
            super();
            this.ordererName = ordererName;
            this.ordererLocation = ordererLocation;
        }

        public String getOrdererName() {
            return ordererName;
        }

        public void setOrdererName(String ordererName) {
            this.ordererName = ordererName;
        }

        public String getOrdererLocation() {
            return ordererLocation;
        }

        public void setOrdererLocation(String ordererLocation) {
            this.ordererLocation = ordererLocation;
        }
    }
}