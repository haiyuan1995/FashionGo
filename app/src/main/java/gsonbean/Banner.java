package gsonbean;

import java.util.List;

/**
 * Created by haiyuan1995 on 2016/11/4.
 */

public class Banner  {
    /**
     * data : [{"ADImage":"http://112.124.118.133:9065/ssg/uploads/1fb8f402-a585-4ba1-903c-dff23294dd71.png"},{"ADImage":"http://112.124.118.133:9065/ssg/uploads/11c0cf8d-112b-405a-a5ba-abc819a5d739.png"},{"ADImage":"http://112.124.118.133:9065/ssg/uploads/d715e5b4-d65f-451f-93d8-30059434ec41.png"}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * ADImage : http://112.124.118.133:9065/ssg/uploads/1fb8f402-a585-4ba1-903c-dff23294dd71.png
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
        private String ADImage;

        public String getADImage() {
            return ADImage;
        }

        public void setADImage(String ADImage) {
            this.ADImage = ADImage;
        }
    }
}
