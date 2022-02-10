package com.example.usualtest.redis;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@SpringBootTest
public class RedisTEmplateTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void StringTets() {
        //1、通过redisTemplate设置值
        redisTemplate.boundValueOps("StringKey").set("StringValue123");
        redisTemplate.boundValueOps("StringKey").set("StringValue12311", 1, TimeUnit.MINUTES);

        //2、通过BoundValueOperations设置值
        BoundValueOperations stringKey = redisTemplate.boundValueOps("StringKey");
        stringKey.set("StringVaule22");
        stringKey.set("StringValue23", 1, TimeUnit.MINUTES);

        //3、通过ValueOperations设置值
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("StringKey", "StringVaule121");
        ops.set("StringValue", "StringVaule233", 1, TimeUnit.MINUTES);

        //设置过期时间
        redisTemplate.boundValueOps("StringKey").expire(1, TimeUnit.MINUTES);
        redisTemplate.expire("StringKey", 1, TimeUnit.MINUTES);

        //1、通过redisTemplate设置值
        String str1 = (String) redisTemplate.boundValueOps("StringKey").get();

        //2、通过BoundValueOperations获取值
        BoundValueOperations stringKey1 = redisTemplate.boundValueOps("StringKey");
        String str2 = (String) stringKey1.get();

        //3、通过ValueOperations获取值
        ValueOperations ops1 = redisTemplate.opsForValue();
        String str3 = (String) ops1.get("StringKey");

        System.out.println("str1" + str1);
        System.out.println("str2" + str2);
        System.out.println("str3" + str3);
    }


    @Test
    public void HashTest() {
        //1、通过redisTemplate设置值
        redisTemplate.boundHashOps("HashKey").put("SmallKey", "HashVaue");

        //2、通过BoundValueOperations设置值
        BoundHashOperations hashKey = redisTemplate.boundHashOps("HashKey");
        hashKey.put("SmallKey", "HashVaue");

        //3、通过ValueOperations设置值
        HashOperations hashOps = redisTemplate.opsForHash();
        hashOps.put("HashKey", "SmallKey", "HashVaue");

        HashMap<String, String> hashMap = new HashMap<>();
        redisTemplate.boundHashOps("HashKey").putAll(hashMap);

        //---------------获取所有key----------------
        //1、通过redisTemplate获取值
        Set keys1 = redisTemplate.boundHashOps("HashKey").keys();

        //2、通过BoundValueOperations获取值
        BoundHashOperations hashKey1 = redisTemplate.boundHashOps("HashKey");
        Set keys2 = hashKey1.keys();

        //3、通过ValueOperations获取值
        HashOperations hashOps1 = redisTemplate.opsForHash();
        Set keys3 = hashOps1.keys("HashKey");

        //---------------------获取所有value----------------
        //1、通过redisTemplate获取值
        List values1 = redisTemplate.boundHashOps("HashKey").values();

        //2、通过BoundValueOperations获取值
        BoundHashOperations hashKey2 = redisTemplate.boundHashOps("HashKey");
        List values2 = hashKey2.values();

        //3、通过ValueOperations获取值
        HashOperations hashOps2 = redisTemplate.opsForHash();
        List values3 = hashOps2.values("HashKey");

        //---------------------通过key 获取value----------------------
        //1、通过redisTemplate获取
        String value1 = (String) redisTemplate.boundHashOps("HashKey").get("SmallKey");

        //2、通过BoundValueOperations获取值
        BoundHashOperations hashKey3 = redisTemplate.boundHashOps("HashKey");
        String value2 = (String) hashKey3.get("SmallKey");

        //3、通过ValueOperations获取值
        HashOperations hashOps3 = redisTemplate.opsForHash();
        String value3 = (String) hashOps3.get("HashKey", "SmallKey");

        redisTemplate.boundHashOps("HashKey").put("testhash", "dwqdqwdwdq");
        redisTemplate.boundHashOps("HashKey").put("testhash2332", "121211");

        System.out.println(redisTemplate.boundHashOps("HashKey").values());
        System.out.println(redisTemplate.boundHashOps("HashKey").keys());
        //是否包含某个key
        System.out.println(redisTemplate.boundHashOps("HashKey").hasKey("wdwdwd"));
        System.out.println(redisTemplate.boundHashOps("HashKey").get("SmallKey"));
        System.out.println(redisTemplate.boundHashOps("HashKey").entries());


        //删除小key
        redisTemplate.boundHashOps("HashKey").delete("SmallKey");
        //删除大key
        redisTemplate.delete("HashKey");

    }

    @Test
    public void SetTest() {
        redisTemplate.boundSetOps("setkey").add("tets");
        redisTemplate.opsForSet().add("setkey", "test2");
        redisTemplate.opsForSet().add("setkey", "test2", "test3", "test4");
        System.out.println(redisTemplate.boundSetOps("setkey").members());

        //移除指定元素
        Long result1 = redisTemplate.boundSetOps("setkey").remove("test2");
        System.out.println(redisTemplate.opsForSet().members("setkey"));
    }

    @Test
    public void ListTest() {
        redisTemplate.opsForList().leftPush("listkey", "listvalue");
        redisTemplate.opsForList().leftPush("listkey", "rightwqew1");
        redisTemplate.boundListOps("listkey").leftPush("sasas");
        System.out.println("11111111111"+redisTemplate.boundListOps("listkey").leftPop());

      /*  ArrayList<String> list = new ArrayList<>();
        redisTemplate.boundListOps("listkey").rightPushAll(list);
        redisTemplate.boundListOps("listkey").leftPushAll(list);*/

        List listKey1 = redisTemplate.boundListOps("listkey").range(0, 3);
        String listKey2 = (String) redisTemplate.boundListOps("listkey").leftPop();  //从左侧弹出一个元素
        String listKey3 = (String) redisTemplate.boundListOps("listkey").rightPop(); //从右侧弹出一个元素
        String listKey4 = (String) redisTemplate.boundListOps("listkey").index(1);
        System.out.println(listKey2);
        System.out.println(listKey3);
        System.out.println(listKey1);
        //根据索引修改List中的某条数据(key，索引，值)
        redisTemplate.boundListOps("listkey").set(2L, "listLeftValue3");
        //移除N个值为value(key,移除个数，值)
        // redisTemplate.boundListOps("listkey").remove(1L,"rightwqew1");
        System.out.println(listKey1);
        System.out.println(listKey4);


    }


    @Test
    public void test3() {
        String sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

        System.out.println(sdf);

        String timeStr1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        System.out.println(timeStr1);


    }

    @Test
    public void test122() {

        int value = 0;
        char chr = '9';
        //先全部将小写转换为大写

        if ((chr >= 'a') && (chr <= 'z')){
            chr = (char) (chr );
        }



        //将字符转化成相应的数字

        if ((chr >= '0') && (chr <= '9')){

            value = chr - 48;
        }


        else if ((chr >= 'A') && (chr <= 'Z'))

            value = chr - 65 + 10;

        System.out.println("2131231313--------"+value);

       // return value;

    }

    @Test
    public void test4(){
        //判断是否纯数字
        NumberUtils.isNumber(".1");//true
        NumberUtils.isDigits("12.");//false
        System.out.println(NumberUtils.isNumber(".1 "));
    }
}
