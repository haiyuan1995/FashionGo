package gsonbean;

import java.util.List;

/**
 * Created by haiyuan1995 on 2016/11/4.
 */

public class Banner  {

    /**
     * data : [{"bannerImage":"http://112.124.118.133:9065/ssg/uploads/d2cec0bc-30fa-4a5d-a7c8-306fa7a21308.jpg","goodsCode":"5"},{"bannerImage":"http://112.124.118.133:9065/ssg/uploads/a0492961-1f13-4cff-afd9-bc8a405cc6ae.jpg","goodsCode":"2"},{"bannerImage":"http://112.124.118.133:9065/ssg/uploads/3eb65334-b006-4e74-bfff-3f01d1b796db.jpg","goodsCode":"2"},{"bannerImage":"http://112.124.118.133:9065/ssg/uploads/bf1d587d-0560-4313-9f07-b797b1d95b81.jpg","goodsCode":"9856996"},{"bannerImage":"http://112.124.118.133:9065/ssg/uploads/649fc5ec-74da-49d6-b24d-2d9aec8fc861.jpg","goodsCode":"1"}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * bannerImage : http://112.124.118.133:9065/ssg/uploads/d2cec0bc-30fa-4a5d-a7c8-306fa7a21308.jpg
     * goodsCode : 5
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
        private String bannerImage;
        private String goodsCode;

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }
    }
}
