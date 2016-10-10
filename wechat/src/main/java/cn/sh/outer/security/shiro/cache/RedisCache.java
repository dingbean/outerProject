package cn.sh.outer.security.shiro.cache;

import cn.sh.outer.dao.RedisDao;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * RedisCache
 * 自定义授权信息缓存实现类
 * @author Genghc
 * @date 2015/7/14
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private String cacheKeyName = null;
    private RedisDao redisDao;

    public RedisCache(String cacheKeyName, RedisDao redisDao) {
        this.cacheKeyName = cacheKeyName;
        this.redisDao = redisDao;
    }

    @Override
    public Object get(Object key) throws CacheException {

        Object o = null;
        //从redis获取认证信息
        o = redisDao.mapGetValue(cacheKeyName.toString(), key.toString());
        return o;
    }

    @Override
    public Object put(Object key, Object value) throws CacheException {
        if(key == null) return null;
        redisDao.mapPut(cacheKeyName.toString(),key.toString(),value);
        return value;
    }

    @Override
    public Object remove(Object key) throws CacheException {
        Object value = redisDao.mapGetValue(cacheKeyName.toString(),key.toString());
        //// TODO: 2016/5/5 添加集群功能后此处报错，先屏蔽此功能 
       // redisDao.mapRemove(cacheKeyName.toString(),key.toString());
        return value;
    }

    @Override
    public void clear() throws CacheException {
        redisDao.deleteValue(cacheKeyName.toString());

    }

    @Override
    public int size() {
        Map<Object,Object> map = redisDao.mapGet(cacheKeyName.toString());
        return map.size();
    }

    @Override
    public Set keys() {
        Map<Object,Object> map = redisDao.mapGet(cacheKeyName.toString());
        return map.keySet();
    }

    @Override
    public Collection values() {
        Map<Object,Object> map = redisDao.mapGet(cacheKeyName.toString());
        return map.values();
    }
}
