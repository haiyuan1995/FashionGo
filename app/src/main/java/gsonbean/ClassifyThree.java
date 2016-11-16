package gsonbean;

import java.util.List;

/**
 * Created by haiyuan1995 on 2016/11/16.
 */

public class ClassifyThree {
    /**
     * data : [{"classifyName":"皮鞋","ClassifyThreeId":78},{"classifyName":"休闲鞋","ClassifyThreeId":79}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * classifyName : 皮鞋
     * ClassifyThreeId : 78
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
        private int ClassifyThreeId;

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }

        public int getClassifyThreeId() {
            return ClassifyThreeId;
        }

        public void setClassifyThreeId(int ClassifyThreeId) {
            this.ClassifyThreeId = ClassifyThreeId;
        }
    }
}
