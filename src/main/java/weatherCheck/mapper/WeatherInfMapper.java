package weatherCheck.mapper;

import weatherCheck.reflectOne.CityList;
import weatherCheck.reflectOne.CityWeather;

import java.util.List;

/*
部分方法未实装
 */

public interface WeatherInfMapper {

    /*
    将得到的数据传入数据库
     */

    Integer insertInf(CityWeather c1);

    Integer insertCityList(CityList cL1);

    Integer deleteCityListByID(String cityList_Id);

    Integer deleteByID(String ID);

    CityList getCityInf(String name);

    String getName(String ID);

    List<String> findId();

    Integer updateInf(CityWeather c1);
    /*
    输出三日天气保存到list里面
    */
    List<CityWeather> checkThreeDay();
}
