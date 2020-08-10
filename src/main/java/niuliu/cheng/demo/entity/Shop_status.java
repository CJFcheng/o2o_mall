package niuliu.cheng.demo.entity;

public class Shop_status {
    private Integer id;
    private String shop_name;
    private String shop_post;
    private String opentime;
    private String downtime;
    private String sp_status;
    private String activity;
    private String shoplei;

    public Shop_status(String shop_name, String shop_post, String opentime, String downtime, String sp_status, String activity, String shoplei) {
        this.shop_name = shop_name;
        this.shop_post = shop_post;
        this.opentime = opentime;
        this.downtime = downtime;
        this.sp_status = sp_status;
        this.activity = activity;
        this.shoplei = shoplei;
    }

    public String getShoplei() {
        return shoplei;
    }

    public void setShoplei(String shoplei) {
        this.shoplei = shoplei;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopname() {
        return shop_name;
    }

    public void setShopname(String shopname) {
        this.shop_name = shopname;
    }

    public String getShop_post() {
        return shop_post;
    }

    public void setShop_post(String shop_post) {
        this.shop_post = shop_post;
    }


    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }


    public String getDowntime() {
        return downtime;
    }

    public void setDowntime(String downtime) {
        this.downtime = downtime;
    }


    public String getSp_status() {
        return sp_status;
    }

    public void setSp_status(String sp_status) {
        this.sp_status = sp_status;
    }


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
