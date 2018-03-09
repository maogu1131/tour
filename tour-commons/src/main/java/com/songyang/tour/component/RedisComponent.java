package com.songyang.tour.component;/**
 * Created by lenovo on 2017/12/6.
 */

import com.songyang.tour.type.TypeReference;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.SafeEncoder;

import javax.annotation.PreDestroy;
import java.util.*;

/**
 * redis组件
 *
 * @author
 * @create 2017-12-06 17:40
 **/
@Component
public class RedisComponent implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Long LOCK_EXPIRED_TIME = Long.valueOf(5000L);
    private ShardedJedisPool pool;
    private Serializer serializer;

    public RedisComponent() {
    }

    public boolean acquireLock(String lock) {
        return this.acquireLock(lock, this.LOCK_EXPIRED_TIME.longValue());
    }

    public boolean acquireLock(String lock, long expired, int tryTime) {
        long beginTime = System.currentTimeMillis();
        boolean lockFlag = this.acquireLock(lock, expired);

        do {
            if (lockFlag) {
                return true;
            }

            lockFlag = this.acquireLock(lock, expired);
        } while (System.currentTimeMillis() - beginTime < (long) tryTime);

        return lockFlag;
    }

    public boolean acquireLock(String lockKey, long expired) {
        ShardedJedis jedis = null;
        boolean success = false;

        try {
            jedis = (ShardedJedis) this.pool.getResource();
            long value = System.currentTimeMillis() + expired + 1L;
            long acquired = jedis.setnx(lockKey, String.valueOf(value)).longValue();
            if (acquired == 1L) {
                success = true;
            } else {
                long oldValue = Long.valueOf(jedis.get(lockKey)).longValue();
                if (oldValue < System.currentTimeMillis()) {
                    String getValue = jedis.getSet(lockKey, String.valueOf(value));
                    if (Long.valueOf(getValue).longValue() == oldValue) {
                        success = true;
                    } else {
                        success = false;
                    }
                } else {
                    success = false;
                }
            }
        } catch (Throwable var16) {
            this.logger.error("acquireLock error", var16);
        } finally {
            this.returnResource(jedis);
        }

        return success;
    }

    public void releaseLock(String lockKey) {
        ShardedJedis jedis = null;

        try {
            jedis = (ShardedJedis) this.pool.getResource();
            long current = System.currentTimeMillis();
            if (jedis.get(lockKey) != null && current < Long.valueOf(jedis.get(lockKey)).longValue()) {
                jedis.del(lockKey);
            }
        } catch (Throwable var8) {
            this.logger.error("releaseLock error", var8);
        } finally {
            this.returnResource(jedis);
        }

    }

    public <T> T get(final String key, final Class<T> c) {
        return this.execute(new JedisAction<T>() {
            public T action(ShardedJedis jedis) {
                byte[] bs = jedis.get(SafeEncoder.encode(key));
                return RedisComponent.this.deserialization(bs, c);
            }
        });
    }

    public <T> List<T> getList(final String key, final Class<T> c) {
        return this.executeForList(new JedisActionForList<T>() {
            public List<T> action(ShardedJedis jedis) {
                byte[] bs = jedis.get(SafeEncoder.encode(key));
                return RedisComponent.this.deserializationList(bs, c);
            }
        });
    }

    public void set(final String key, final Object value) {
        this.execute(new JedisActionNoResult() {
            public void action(ShardedJedis jedis) {
                jedis.set(SafeEncoder.encode(key), RedisComponent.this.serialzation(value));
            }
        });
    }

    public void set(final String key, final Object value, final int seconds) {
        this.execute(new JedisActionNoResult() {
            public void action(ShardedJedis jedis) {
                jedis.setex(SafeEncoder.encode(key), seconds, RedisComponent.this.serialzation(value));
            }
        });
    }

    public Long setnx(final String key, final Object value) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.setnx(SafeEncoder.encode(key), RedisComponent.this.serialzation(value));
            }
        });
    }

    public Long setnx(final String key, final Object value, final int seconds) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                byte[] byteKey = SafeEncoder.encode(key);
                Long res = jedis.setnx(byteKey, RedisComponent.this.serialzation(value));
                if (res != null && res.longValue() > 0L) {
                    jedis.expire(byteKey, seconds);
                }

                return res;
            }
        });
    }

    public <T> List<T> hvals(final String key, final Class<T> c) {
        return this.executeForList(new JedisActionForList<T>() {
            public List<T> action(ShardedJedis jedis) {
                Collection<byte[]> value = jedis.hvals(SafeEncoder.encode(key));
                List<T> list = new ArrayList(value.size());
                Iterator i$ = value.iterator();

                while (i$.hasNext()) {
                    byte[] bs = (byte[]) i$.next();
                    list.add(RedisComponent.this.deserialization(bs, c));
                }

                return list;
            }
        });
    }

    public <T> T hget(final String key, final Object mapKey, final TypeReference<T> type) {
        return this.execute(new JedisAction<T>() {
            public T action(ShardedJedis jedis) {
                byte[] bs = jedis.hget(SafeEncoder.encode(key), RedisComponent.this.serialzation(mapKey));
                return RedisComponent.this.deserialization(bs, type);
            }
        });
    }

    public <T> T hget(final String key, final Object mapKey, final Class<T> c) {
        return this.execute(new JedisAction<T>() {
            public T action(ShardedJedis jedis) {
                byte[] bs = jedis.hget(SafeEncoder.encode(key), RedisComponent.this.serialzation(mapKey));
                return RedisComponent.this.deserialization(bs, c);
            }
        });
    }

    public <T> List<T> hgetList(final String key, final Object mapKey, final Class<T> c) {
        return this.executeForList(new JedisActionForList<T>() {
            public List<T> action(ShardedJedis jedis) {
                byte[] value = jedis.hget(SafeEncoder.encode(key), RedisComponent.this.serialzation(mapKey));
                return RedisComponent.this.deserializationList(value, c);
            }
        });
    }

    public void hset(final String key, final Object mapKey, final Object mapValue) {
        this.execute(new JedisActionNoResult() {
            public void action(ShardedJedis jedis) {
                jedis.hset(SafeEncoder.encode(key), RedisComponent.this.serialzation(mapKey), RedisComponent.this.serialzation(mapValue));
            }
        });
    }

    public void hset(final String key, final Object mapKey, final Object mapValue, final int second) {
        this.execute(new JedisActionNoResult() {
            public void action(ShardedJedis jedis) {
                jedis.hset(SafeEncoder.encode(key), RedisComponent.this.serialzation(mapKey), RedisComponent.this.serialzation(mapValue));
                jedis.expire(key, second);
            }
        });
    }

    public void hdel(final String key, final Object mapKey) {
        this.execute(new JedisActionNoResult() {
            public void action(ShardedJedis jedis) {
                jedis.hdel(SafeEncoder.encode(key), new byte[][]{RedisComponent.this.serialzation(mapKey)});
            }
        });
    }

    public void hmset(final String key, final Map<Object, Object> map) {
        this.execute(new JedisActionNoResult() {
            public void action(ShardedJedis jedis) {
                if (MapUtils.isNotEmpty(map)) {
                    Map<byte[], byte[]> m = new HashMap(map.size());
                    Iterator it = map.entrySet().iterator();

                    while (it.hasNext()) {
                        Map.Entry<Object, Object> next = (Map.Entry) it.next();
                        m.put(RedisComponent.this.serialzation(next.getKey()), RedisComponent.this.serialzation(next.getValue()));
                    }

                    jedis.hmset(SafeEncoder.encode(key), m);
                }

            }
        });
    }

    public void hmset(final String key, final Map<Object, Object> map, final int expireSeconds) {
        this.execute(new JedisActionNoResult() {
            public void action(ShardedJedis jedis) {
                if (MapUtils.isNotEmpty(map)) {
                    Map<byte[], byte[]> m = new HashMap(map.size());
                    Iterator it = map.entrySet().iterator();

                    while (it.hasNext()) {
                        Map.Entry<Object, Object> next = (Map.Entry) it.next();
                        m.put(RedisComponent.this.serialzation(next.getKey()), RedisComponent.this.serialzation(next.getValue()));
                    }

                    jedis.hmset(SafeEncoder.encode(key), m);
                    jedis.expire(key, expireSeconds);
                }

            }
        });
    }

    public Long del(final String key) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.del(key);
            }
        });
    }

    public Long zadd(final String key, final double score, final Object member) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.zadd(SafeEncoder.encode(key), score, RedisComponent.this.serialzation(member));
            }
        });
    }

    public <T> List<T> zrange(final String key, final long start, final long end, final Class<T> clazz) {
        return this.executeForList(new JedisActionForList<T>() {
            public List<T> action(ShardedJedis jedis) {
                Collection<byte[]> value = jedis.zrange(SafeEncoder.encode(key), start, end);
                List<T> list = new ArrayList(value.size());
                Iterator i$ = value.iterator();

                while (i$.hasNext()) {
                    byte[] b = (byte[]) i$.next();
                    list.add(RedisComponent.this.deserialization(b, clazz));
                }

                return list;
            }
        });
    }

    public <T> List<T> zrangeByScore(final String key, final double min, final double max, final Class<T> clazz) {
        return this.executeForList(new JedisActionForList<T>() {
            public List<T> action(ShardedJedis jedis) {
                Collection<byte[]> value = jedis.zrangeByScore(SafeEncoder.encode(key), min, max);
                List<T> list = new ArrayList(value.size());
                Iterator i$ = value.iterator();

                while (i$.hasNext()) {
                    byte[] b = (byte[]) i$.next();
                    list.add(RedisComponent.this.deserialization(b, clazz));
                }

                return list;
            }
        });
    }

    public Long zremrangeByScore(final String key, final double start, final double end) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }
        });
    }

    public Long zremrange(final String key, final String... members) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.zrem(key, members);
            }
        });
    }

    public Long incr(final String key) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.incr(key);
            }
        });
    }

    public Long incrBy(final String key, final long integer) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.incrBy(key, integer);
            }
        });
    }

    public Long decr(final String key) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.decr(key);
            }
        });
    }

    public Long decrBy(final String key, final long integer) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.decrBy(key, integer);
            }
        });
    }

    public Long expire(final String key, final int seconds) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.expire(key, seconds);
            }
        });
    }

    public Boolean exists(final String key) {
        return (Boolean) this.execute(new JedisAction<Boolean>() {
            public Boolean action(ShardedJedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    public Long sadd(final String key, final Object value) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.sadd(SafeEncoder.encode(key), new byte[][]{RedisComponent.this.serialzation(value)});
            }
        });
    }

    public Long srem(final String key, final Object value) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.srem(SafeEncoder.encode(key), new byte[][]{RedisComponent.this.serialzation(value)});
            }
        });
    }

    public Long ttl(final String key) {
        return (Long) this.execute(new JedisAction<Long>() {
            public Long action(ShardedJedis jedis) {
                return jedis.ttl(key);
            }
        });
    }

    public Set<String> smembers(final String key) {
        return (Set) this.execute(new JedisAction<Set<String>>() {
            public Set<String> action(ShardedJedis jedis) {
                Set<byte[]> byteValues = jedis.smembers(SafeEncoder.encode(key));
                Set<String> stringValues = new HashSet();
                if (byteValues == null) {
                    return stringValues;
                } else {
                    Iterator i$ = byteValues.iterator();

                    while (i$.hasNext()) {
                        byte[] byteValue = (byte[]) i$.next();
                        stringValues.add(RedisComponent.this.deserialization(byteValue, String.class));
                    }

                    return stringValues;
                }
            }
        });
    }

    public <T> T execute(JedisAction<T> jedisAction) {
        ShardedJedis jedis = null;

        Object var3;
        try {
            jedis = (ShardedJedis) this.pool.getResource();
            var3 = jedisAction.action(jedis);
        } finally {
            this.returnResource(jedis);
        }

        return (T) var3;
    }

    public <T> List<T> executeForList(JedisActionForList<T> jedisAction) {
        ShardedJedis jedis = null;

        List var3;
        try {
            jedis = (ShardedJedis) this.pool.getResource();
            var3 = jedisAction.action(jedis);
        } finally {
            this.returnResource(jedis);
        }

        return var3;
    }

    public void execute(JedisActionNoResult jedisAction) {
        ShardedJedis jedis = null;

        try {
            jedis = (ShardedJedis) this.pool.getResource();
            jedisAction.action(jedis);
        } finally {
            this.returnResource(jedis);
        }

    }

    private byte[] serialzation(Object object) {
        return this.serializer.serialzation(object);
    }

    private <T> T deserialization(byte[] byteArray, Class<T> c) {
        return this.serializer.deserialization(byteArray, c);
    }

    private <T> T deserialization(byte[] byteArray, TypeReference<T> type) {
        return this.serializer.deserialization(byteArray, type);
    }

    private <E> List<E> deserializationList(byte[] byteArray, Class<E> elementC) {
        return this.serializer.deserializationList(byteArray, elementC);
    }

    protected void returnResource(ShardedJedis jedis) {
        if (jedis != null) {
            try {
                this.pool.returnResource(jedis);
            } catch (Throwable var3) {
                this.returnBrokenResource(jedis);
            }
        }

    }

    private void returnBrokenResource(ShardedJedis jedis) {
        if (jedis != null) {
            try {
                this.pool.returnBrokenResource(jedis);
            } catch (Throwable var3) {
                this.logger.error("", var3);
            }
        }

    }

    @PreDestroy
    public void destory() {
        try {
            this.pool.destroy();
        } catch (Throwable var2) {
            this.logger.error("", var2);
        }

    }

    public ShardedJedisPool getPool() {
        return this.pool;
    }

    public void setPool(ShardedJedisPool pool) {
        this.pool = pool;
    }

    public Serializer getSerializer() {
        return this.serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    public void afterPropertiesSet() throws Exception {
        if (this.serializer == null) {
            this.serializer = new JsonSerializer();
        }

        this.logger.info("RedisComponent [" + this.toString() + "] is done! serializer:" + this.serializer.toString());
    }


    public interface JedisActionNoResult {
        void action(ShardedJedis var1);
    }

    public interface JedisActionForList<T> {
        List<T> action(ShardedJedis var1);
    }

    public interface JedisAction<T> {
        T action(ShardedJedis var1);
    }
}
