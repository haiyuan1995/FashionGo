package gsonbean;

import java.util.List;

/**
 * 获取全国所有省市
 */

public class Province {
    /**
     * data : [{"pCode":"110000","pName":"北京市"},{"pCode":"120000","pName":"天津市"},{"pCode":"130000","pName":"河北省"},{"pCode":"140000","pName":"山西省"},{"pCode":"150000","pName":"内蒙古自治区"},{"pCode":"210000","pName":"辽宁省"},{"pCode":"220000","pName":"吉林省"},{"pCode":"230000","pName":"黑龙江省"},{"pCode":"310000","pName":"上海市"},{"pCode":"320000","pName":"江苏省"},{"pCode":"330000","pName":"浙江省"},{"pCode":"340000","pName":"安徽省"},{"pCode":"350000","pName":"福建省"},{"pCode":"360000","pName":"江西省"},{"pCode":"370000","pName":"山东省"},{"pCode":"410000","pName":"河南省"},{"pCode":"420000","pName":"湖北省"},{"pCode":"430000","pName":"湖南省"},{"pCode":"440000","pName":"广东省"},{"pCode":"450000","pName":"广西壮族自治区"},{"pCode":"460000","pName":"海南省"},{"pCode":"500000","pName":"重庆市"},{"pCode":"510000","pName":"四川省"},{"pCode":"520000","pName":"贵州省"},{"pCode":"530000","pName":"云南省"},{"pCode":"540000","pName":"西藏自治区"},{"pCode":"610000","pName":"陕西省"},{"pCode":"620000","pName":"甘肃省"},{"pCode":"630000","pName":"青海省"},{"pCode":"640000","pName":"宁夏回族自治区"},{"pCode":"650000","pName":"新疆维吾尔自治区"}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * pCode : 110000
     * pName : 北京市
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
        private String pCode;
        private String pName;

        public String getPCode() {
            return pCode;
        }

        public void setPCode(String pCode) {
            this.pCode = pCode;
        }

        public String getPName() {
            return pName;
        }

        public void setPName(String pName) {
            this.pName = pName;
        }
    }
}
