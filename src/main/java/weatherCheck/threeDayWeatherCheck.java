package weatherCheck;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import weatherCheck.mapper.WeatherInfMapper;
import weatherCheck.reflectOne.CityList;
import weatherCheck.reflectOne.CityWeather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class threeDayWeatherCheck {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入城市的名称，目前只可用（上海，北京，福州）");
        String cityName = sc.nextLine();

//        showCityInfByUser(cityName);  //查看城市信息
//        String cityName = "福州";

//        updateAllByUser();  //数据库内全部内容更新示例
        CheckWeatherByUser(cityName);   //查看城市三日天气
    }
    /*
    输出城市的信息
     */
    private static void showCityInfByUser(String cityName) throws IOException {
        SqlSession sql1 = getSession();
        WeatherInfMapper mapper = sql1.getMapper(WeatherInfMapper.class);
        CityList cityInf = mapper.getCityInf(cityName);
        System.out.println(cityInf);
    }
    /*
    更新在CityList数据库中所有城市的三日数据
     */
    private static void updateAllByUser() throws IOException, ParseException {
        SqlSession sql1 = getSession();
        WeatherInfMapper mapper = sql1.getMapper(WeatherInfMapper.class);
        List<String> id = mapper.findId();
        for (int i = 0; i < id.size(); i++) {
            String cityID= id.get(i);
            deleteByID(cityID);
            String weatherInf = getWeather(cityID);
            String cityName = mapper.getName(cityID);
            List<CityWeather> lCW = dealInf(weatherInf, cityName, cityID);
            deleteByID(cityID);
            for (int j = 0; j < lCW.size(); j++) {
                insertBase(lCW.get(j));
            }
        }
    }

    /*
    用户输入城市名称来查看三日天气
     */
    private static void CheckWeatherByUser(String cityName) throws IOException, ParseException {
        String cityID = getID(cityName);
        String weatherInf = getWeather(cityID);
        List<CityWeather> lCW = dealInf(weatherInf, cityName, cityID);
        deleteByID(cityID);
        for (int i = 0; i < lCW.size(); i++) {
            System.out.println(lCW.get(i));
            insertBase(lCW.get(i));
        }
    }

    /*
    插入数据库
     */
    private static void insertBase(CityWeather cw) throws IOException {     //插入数据，按照传入的CityWeather类
        SqlSession sql1 = getSession();
        WeatherInfMapper mapper = sql1.getMapper(WeatherInfMapper.class);
        mapper.insertInf(cw);
        sql1.commit();
        sql1.close();
    }

    /*
    根据ID删除数据内容
     */
    private static void deleteByID(String ID) throws IOException {
        SqlSession sql1 = getSession();
        WeatherInfMapper mapper = sql1.getMapper(WeatherInfMapper.class);
        mapper.deleteByID(ID);
        sql1.commit();
        sql1.close();
    }

    /*
   利用正则表达式匹配得到的字符串中所需要的内容然后打包成CityWeather类，修改所需内容在这里
    */
    private static List<CityWeather> dealInf(String TDW, String cityName, String cityID){
        List<CityWeather> l1 = new ArrayList<>();
        String temp;
        int l = TDW.indexOf('[');
        int r = TDW.indexOf(']');
        temp = TDW.substring(l+1,r);
        String [] s1=temp.split("}");
        String fxDate ;
        String tempMax ;
        String tempMin ;
        String textDay ;
        for (int i = 0; i < s1.length; i++) {
            fxDate = getSub(s1[i], "\"fxDate\":\"(.*?)\",\"");
            tempMax = getSub(s1[i], "\"tempMax\":\"(.*?)\",\"");
            tempMin = getSub(s1[i], "\"tempMin\":\"(.*?)\",\"");
            textDay = getSub(s1[i], "\"textDay\":\"(.*?)\",\"");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = simpleDateFormat.parse(fxDate);
            } catch (ParseException e) {
                System.out.println("未查询到所需信息");
                e.printStackTrace();
            }
            CityWeather cw = new CityWeather(cityID,cityName,date,Integer.parseInt(tempMax),Integer.parseInt(tempMin),textDay);
            l1.add(cw);
        }
        return l1;
    }

    /*
 正则表达式实现方法
  */
    public static String getSub(String org,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(org);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }

    private static String getID(String cityName)throws IOException{
        SqlSession sqlSession = getSession();
        String c1 = sqlSession.selectOne("selectName",cityName);
        sqlSession.close();
        return c1;
    }
    private static SqlSession getSession() throws IOException {
        String resource = "mybiits-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
    public static String getWeather(String place) throws IOException {
        String requestUrl ="https://devapi.qweather.com/v7/weather/3d?key=7842172b8720488189bdd6969d2f32e1&location=";
        String finalURL = requestUrl+place;
        URL url = new URL(finalURL);
        HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
        httpUrlConn.setDoInput(true);
        httpUrlConn.setRequestMethod("GET");
        httpUrlConn.connect();
        InputStream inputStream = httpUrlConn.getInputStream();
        GZIPInputStream gzipInputStream =new GZIPInputStream(inputStream);
        StringBuilder res =new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new
                InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
        while ((line = br.readLine()) != null) {
            res.append(line);
        }
        String source = "temp.txt";
        return res.toString();
    }
}
