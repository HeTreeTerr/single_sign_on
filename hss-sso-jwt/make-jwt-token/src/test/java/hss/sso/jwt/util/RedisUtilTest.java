package hss.sso.jwt.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Test
    public void set() {
        RedisUtil.set("redis-01","jwt-hello");
        System.out.println("存入成功");
    }

    @Test
    public void set1() {
        RedisUtil.set("redis-01","jwt-hello",60*60*1000);
        System.out.println("存入成功");
    }

    @Test
    public void delete() {
        RedisUtil.delete("redis-01");
        System.out.println("删除成功");
    }

    @Test
    public void get(){
        String value = RedisUtil.get("redis-01");
        System.out.println("redis-01="+value);
    }
}