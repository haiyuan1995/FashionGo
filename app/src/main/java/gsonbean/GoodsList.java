package gsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by haiyuan1995 on 2016/11/16.
 */

public class GoodsList {
    /**
     * data : [{"goodsCode":"65852414","goodsImage":"http://112.124.118.133:9065/ssg/uploads/e13e7815-318a-4407-bc1b-b64b250017e1.jpg","goodsName":"时尚男鞋","originalPrice":258,"price":158}]
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;
    /**
     * goodsCode : 65852414
     * goodsImage : http://112.124.118.133:9065/ssg/uploads/e13e7815-318a-4407-bc1b-b64b250017e1.jpg
     * goodsName : 时尚男鞋
     * originalPrice : 258
     * price : 158
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

    public static class DataBean implements Serializable{
        private String goodsCode;
        private String goodsImage;
        private String goodsName;
        private int originalPrice;
        private int price;

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(int originalPrice) {
            this.originalPrice = originalPrice;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
