package gsonbean;

/**
 * Created by haiyuan on 2016/11/10.
 */

public class PersonInfo {
    /**
     * image :
     * name :
     * qq :
     * age : 0
     * sex : 0
     * email :
     * pName : 广东省
     * pCode : 440000
     * cName : 广州市
     * cCode : 440100
     * area : 天河区
     * aCode : 440106
     * address :
     */

    private DataBean data;
    /**
     * data : {"image":"","name":"","qq":"","age":0,"sex":0,"email":"","pName":"广东省","pCode":"440000","cName":"广州市","cCode":"440100","area":"天河区","aCode":"440106","address":""}
     * message : success
     * errorcode : 0
     */

    private String message;
    private int errorcode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        private String image;
        private String name;
        private String qq;
        private int age;
        private int sex;
        private String email;
        private String pName;
        private String pCode;
        private String cName;
        private String cCode;
        private String area;
        private String aCode;
        private String address;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPName() {
            return pName;
        }

        public void setPName(String pName) {
            this.pName = pName;
        }

        public String getPCode() {
            return pCode;
        }

        public void setPCode(String pCode) {
            this.pCode = pCode;
        }

        public String getCName() {
            return cName;
        }

        public void setCName(String cName) {
            this.cName = cName;
        }

        public String getCCode() {
            return cCode;
        }

        public void setCCode(String cCode) {
            this.cCode = cCode;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getACode() {
            return aCode;
        }

        public void setACode(String aCode) {
            this.aCode = aCode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
