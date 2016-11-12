package gsonbean;

import java.util.List;

/**
 * 获取指定省份下的城市列表
 */

public class City {

    /**
     * data : [{"cCode":"440100","cName":"广州市"},{"cCode":"440200","cName":"韶关市"},{"cCode":"440300","cName":"深圳市"},{"cCode":"440400","cName":"珠海市"},{"cCode":"440500","cName":"汕头市"},{"cCode":"440600","cName":"佛山市"},{"cCode":"440700","cName":"江门市"},{"cCode":"440800","cName":"湛江市"},{"cCode":"440900","cName":"茂名市"},{"cCode":"441200","cName":"肇庆市"},{"cCode":"441300","cName":"惠州市"},{"cCode":"441400","cName":"梅州市"},{"cCode":"441500","cName":"汕尾市"},{"cCode":"441600","cName":"河源市"},{"cCode":"441700","cName":"阳江市"},{"cCode":"441800","cName":"清远市"},{"cCode":"441900","cName":"东莞市"},{"cCode":"442000","cName":"中山市"},{"cCode":"445100","cName":"潮州市"},{"cCode":"445200","cName":"揭阳市"},{"cCode":"445300","cName":"云浮市"}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * cCode : 440100
     * cName : 广州市
     */

    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String cCode;
        private String cName;

        public String getCCode() {
            return cCode;
        }

        public void setCCode(String cCode) {
            this.cCode = cCode;
        }

        public String getCName() {
            return cName;
        }

        public void setCName(String cName) {
            this.cName = cName;
        }
    }
}
