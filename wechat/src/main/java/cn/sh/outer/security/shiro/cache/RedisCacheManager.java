package cn.sh.outer.security.shiro.cache;

import cn.sh.outer.dao.RedisDao;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * RedisCacheManager
 * 自定义授权缓存管理类
 * 缓存存储两样东西
 *  （1） shiro_cache_cn.sh.ideal.manager.security.SystemAuthorizingRealm.authorizationCache  个人权限信息 用于授权
 *  （2） shiro_cache_shiro-activeSessionCache   活动的会话信息（分布式会话）  可用于会话管理
 *   在subject.logout时清除
 * @author dk
 * @date 2016/10/10
 */
public class RedisCacheManager implements CacheManager {
    /*授权信息存储前缀*/
    private String cacheKeyPrefix = "shiro_cache_";
    /*redis的实现*/
    private RedisDao redisDao;
    /*获取授权管理缓存器的实现*/
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache<K, V>(cacheKeyPrefix+name,redisDao);
    }

    public String getCacheKeyPrefix() {
        return cacheKeyPrefix;
    }

    public void setCacheKeyPrefix(String cacheKeyPrefix) {
        this.cacheKeyPrefix = cacheKeyPrefix;
    }

    public RedisDao getRedisDao() {
        return redisDao;
    }

    public void setRedisDao(RedisDao redisDao) {
        this.redisDao = redisDao;
    }
}
