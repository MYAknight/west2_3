西二在线第三次

建表文件在OtherFile里面
主要测试文件在weatherCheck.threeDayWeatherCheck
mevan配置在pom.xml
mybatis配置文件在mybiits-config.xml（才发现打错字了）
resource在weather.xml
mapper层在weatherCheck.mapper.WeatherInfMapper
城市列表类和城市天气类在weatherCheck.reflectOne
test是空的（假装符合maven结构）

目前实现的功能：
通过API将三日天气传入到数据库中
查询某一城市三日天气并更新（城市列表目前只有福州，上海，北京三个）
更新所有位于城市列表中城市的数据

之后可能的更新内容：
目前异常全抛了，下次一定
实装控制台内部添加城市的方法

update time：2022/1/13 15点28分

控制台内添加城市功能已经实装
新功能采用了JSONObject处理
添加新城市的时候请输入拼音
因为更新时网络不好，尽管本人已经检查过一遍，但如果发现任何文件丢失劳烦及时联系

之后可能的更新内容：
分页查询，尽管目前数据量太小似乎没有必要，但城市添加后可能会需要。
目前实现思路是将ID排序后以此为limit查找
update time：2022/1/17 22点54分
