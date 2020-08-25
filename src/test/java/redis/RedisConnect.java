package redis;

import redis.clients.jedis.Jedis;

public class RedisConnect {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("192.168.186.129",6379);
        System.out.println(jedis.ping());
        jedis.quit();
    }
}
