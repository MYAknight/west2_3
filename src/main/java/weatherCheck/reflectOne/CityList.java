package weatherCheck.reflectOne;

import java.util.Date;

public class CityList {

    /*
    如果要进行控制台内添加其他城市的话要用到这个类（摸了，还没有做）
     */

    private String cityList_Id;
    private String city_name;
    private float 纬度 ;
    private float 经度;
    private Date set_time;

    public CityList(String cityListId, String name, float 纬度, float 经度, Date set_time) {
        this.cityList_Id = cityListId;
        this.city_name = name;
        this.纬度 = 纬度;
        this.经度 = 经度;
        this.set_time = set_time;
    }

    public CityList() {

    }

    @Override
    public String toString() {
        return "所查询城市信息如下:" +
                ", 名称 '" + city_name + '\'' +
                "， cityListId=" + cityList_Id +
                ", 纬度=" + 纬度 +
                ", 经度=" + 经度 +
                ", 最后更新时间" + set_time;
    }

    public String getCityListId() {
        return cityList_Id;
    }

    public void setCityListId(String cityListId) {
        this.cityList_Id = cityListId;
    }

    public String getName() {
        return city_name;
    }

    public void setName(String name) {
        this.city_name = name;
    }

    public float get纬度() {
        return 纬度;
    }

    public void set纬度(float 纬度) {
        this.纬度 = 纬度;
    }

    public float get经度() {
        return 经度;
    }

    public void set经度(float 经度) {
        this.经度 = 经度;
    }

    public Date getSet_time() {
        return set_time;
    }

    public void setSet_time(Date set_time) {
        this.set_time = set_time;
    }
}
