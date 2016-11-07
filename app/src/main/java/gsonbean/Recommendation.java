package gsonbean;

import java.util.List;

/**
 * Created by haiyuan1995 on 2016/11/7.
 */

public class Recommendation {

    /**
     * data : [{"goodsCode":"9856996","recommenImage":"http://112.124.118.133:9065/ssg/uploads/efbdd156-5e82-491a-b4ab-2ebfd64e1861.jpg"},{"goodsCode":"ABC123","recommenImage":"http://112.124.118.133:9065/ssg/uploads/5bc6904d-5a54-4847-b578-e9280e4ab9d0.jpg"},{"goodsCode":"6547","recommenImage":"http://112.124.118.133:9065/ssg/uploads/8c516d27-94e3-4da8-a3ef-480e6e85c7e7.jpg"},{"goodsCode":"87452","recommenImage":"http://112.124.118.133:9065/ssg/uploads/f82e16ab-7597-498c-9ca0-bbee93d2a585.jpg"},{"goodsCode":"65285","recommenImage":"http://112.124.118.133:9065/ssg/uploads/5eacd779-500b-4fbb-a7d0-06ce9ac5d091.jpg"},{"goodsCode":"65894","recommenImage":"http://112.124.118.133:9065/ssg/uploads/8bc41af9-b213-4f1f-8ca9-9e760cd28b1a.jpg"}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * goodsCode : 9856996
     * recommenImage : http://112.124.118.133:9065/ssg/uploads/efbdd156-5e82-491a-b4ab-2ebfd64e1861.jpg
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
        private String goodsCode;
        private String recommenImage;

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getRecommenImage() {
            return recommenImage;
        }

        public void setRecommenImage(String recommenImage) {
            this.recommenImage = recommenImage;
        }
    }
}
