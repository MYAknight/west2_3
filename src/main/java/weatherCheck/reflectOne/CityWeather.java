package weatherCheck.reflectOne;

import java.util.Date;

public class CityWeather {

    String ID;
    String name;
    Date day;
    int maxTemp;
    int minTemp;
    String condition;
    public CityWeather(String ID, String name, Date day, int maxTemp, int minTemp, String condition) {
        this.ID = ID;
        this.name = name;
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return name+day+" 的天气是: " +
                "\n当日最高气温——" + maxTemp +
                "度,  当日最低气温——" + minTemp +
                "度,  白天天气情况——" + condition;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
