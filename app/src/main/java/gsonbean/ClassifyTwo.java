package gsonbean;

import java.util.List;

/**
 * Created by haiyuan1995 on 2016/11/15.
 */

public class ClassifyTwo {
    /**
     * data : [{"classifyName":"休闲食品","classifyImage":"http://112.124.118.133:9065/ssg/uploads/6f2cfc28-cbc0-45ca-925c-e49129d2d525.jpg","ClassifyTwoId":67},{"classifyName":"进口食品","classifyImage":"http://112.124.118.133:9065/ssg/uploads/94785c7a-0bf9-4af8-8588-a854b08ef9c0.jpg","ClassifyTwoId":68},{"classifyName":"进品奶粉","classifyImage":"http://112.124.118.133:9065/ssg/uploads/4c34b5bb-565d-4de0-90e1-52fbf68a6f3e.jpg","ClassifyTwoId":69},{"classifyName":"进口护肤","classifyImage":"http://112.124.118.133:9065/ssg/uploads/cc732aa6-20df-4997-8f48-b4f8749e8d0b.jpg","ClassifyTwoId":168},{"classifyName":"个人洗护","classifyImage":"http://112.124.118.133:9065/ssg/uploads/170caa30-8761-4789-a451-17df2783bd74.jpg","ClassifyTwoId":169},{"classifyName":"家居用品","classifyImage":"http://112.124.118.133:9065/ssg/uploads/5f3668fd-f1b4-457a-b6cd-7723e9d2df0c.jpg","ClassifyTwoId":170},{"classifyName":"酒水饮料","classifyImage":"http://112.124.118.133:9065/ssg/uploads/7084c798-eec8-433a-98fa-a3272016cf40.jpg","ClassifyTwoId":171},{"classifyName":"粮油食品","classifyImage":"http://112.124.118.133:9065/ssg/uploads/50f4909d-8191-49e5-b985-7cdad5909899.jpg","ClassifyTwoId":172},{"classifyName":"奶制品/冲饮","classifyImage":"http://112.124.118.133:9065/ssg/uploads/7aab9eed-75b7-47e9-9f90-19da71ff03eb.jpg","ClassifyTwoId":173},{"classifyName":"速冻食品","classifyImage":"http://112.124.118.133:9065/ssg/uploads/8e072d53-496f-4cef-aa7c-f9690825d018.jpg","ClassifyTwoId":174},{"classifyName":"办公用品","classifyImage":"http://192.168.0.11:8090/ssg/uploads/20151104171817.jpg","ClassifyTwoId":175},{"classifyName":"特色熟食","classifyImage":"http://192.168.0.11:8090/ssg/uploads/20151104171845.jpg","ClassifyTwoId":176}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * classifyName : 休闲食品
     * classifyImage : http://112.124.118.133:9065/ssg/uploads/6f2cfc28-cbc0-45ca-925c-e49129d2d525.jpg
     * ClassifyTwoId : 67
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
        private String classifyImage;
        private int ClassifyTwoId;

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }

        public String getClassifyImage() {
            return classifyImage;
        }

        public void setClassifyImage(String classifyImage) {
            this.classifyImage = classifyImage;
        }

        public int getClassifyTwoId() {
            return ClassifyTwoId;
        }

        public void setClassifyTwoId(int ClassifyTwoId) {
            this.ClassifyTwoId = ClassifyTwoId;
        }
    }
}
