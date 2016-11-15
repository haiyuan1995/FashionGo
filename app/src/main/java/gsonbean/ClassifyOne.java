package gsonbean;

import java.util.List;

/**
 *商品一级分类
 */

public class ClassifyOne {
    /**
     * data : [{"classifyName":"多多超市","classifyId":4},{"classifyName":"男装","classifyId":5},{"classifyName":"男鞋","classifyId":6},{"classifyName":"女装","classifyId":7},{"classifyName":"女鞋","classifyId":8},{"classifyName":"美妆","classifyId":9},{"classifyName":"母婴","classifyId":10},{"classifyName":"内衣","classifyId":11},{"classifyName":"小家电/五金","classifyId":166},{"classifyName":"生鲜/水果","classifyId":167}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * classifyName : 多多超市
     * classifyId : 4
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
        private String classifyName;
        private int classifyId;

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }

        public int getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(int classifyId) {
            this.classifyId = classifyId;
        }
    }
}
