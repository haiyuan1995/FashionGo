package gsonbean;

import java.util.List;

/**
 * 获取指定城市下的区
 */

public class Area {

    /**
     * data : [{"aCode":"440501","area":"市辖区"},{"aCode":"440507","area":"龙湖区"},{"aCode":"440511","area":"金平区"},{"aCode":"440512","area":"濠江区"},{"aCode":"440513","area":"潮阳区"},{"aCode":"440514","area":"潮南区"},{"aCode":"440515","area":"澄海区"},{"aCode":"440523","area":"南澳县"}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * aCode : 440501
     * area : 市辖区
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
        private String aCode;
        private String area;

        public String getACode() {
            return aCode;
        }

        public void setACode(String aCode) {
            this.aCode = aCode;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}
