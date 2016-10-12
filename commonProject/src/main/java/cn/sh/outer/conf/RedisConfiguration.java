package cn.sh.outer.conf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import cn.sh.outer.dao.SqlExecute;
import cn.sh.outer.model.SysParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

/**
 * redis configration
 * 
 * @author dk
 * 2016-10-12
 * @since v1.1.6 
 *
 */
@Configuration
public class RedisConfiguration {
	private static Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);
	
	private static final String REDIS_MODEL = "REDIS_MODEL";
	private static final String REDIS_MASTER_NAME = "REDIS_MASTER_NAME";
	private static final String REDIS_HOST = "REDIS_HOST";
	private static final String REDIS_PASSWORD = "REDIS_PASSWORD";
	private static final String REDIS_SENTINEL = "REDIS_SENTINEL";
	private static final String REDIS_CLUSTER = "REDIS_CLUSTER";
	
	@Autowired
	private SqlExecute sqlExecute;
	
	private static Map<String,String> redisParam ;
	
	public enum RedisMode{
		SINGLE,SENTINEL,CLUSTER
	}
	
	@PostConstruct
	public void init(){
		List<SysParam> params = sqlExecute.getRedisParam();
		
		if(CollectionUtils.isEmpty(params)){
			throw new NullPointerException("can not find the redis params");
		}
		redisParam = new HashMap<String,String>();
		for(SysParam param : params){
			redisParam.put(param.getParamCode(), param.getParamValue());
		}
	}
	
	
	public RedisSentinelConfiguration redisSentinelConfiguration(){
		String sentinellist = redisParam.get(REDIS_SENTINEL);
		String masterName = redisParam.get(REDIS_MASTER_NAME);
		
		if(null == sentinellist){
			throw new NullPointerException("can not find the redis sentinels");
		}
		
		if(null == masterName){
			throw new NullPointerException("can not find the redis master name");
		}
		
		logger.debug("redis sentinel:{}",sentinellist);
		
		Set<String> sentinels = new HashSet<String>();

		for(String sentinel : sentinellist.split(";")){
			sentinels.add(sentinel);
		}
		
		return new RedisSentinelConfiguration(masterName,sentinels);
		
	}
	
	public JedisShardInfo jedisShardInfo(){
		String host = redisParam.get(REDIS_HOST);
		
		if(null == host){
			throw new NullPointerException("can not find the redis host");
		}
		
		String addr[] = host.split(":");
		
		logger.debug("redis host:{}",host);
		
		JedisShardInfo info = new JedisShardInfo(addr[0],Integer.parseInt(addr[1]));
		String password = redisParam.get(REDIS_PASSWORD);
		
		if(StringUtils.isNotEmpty(password))
			info.setPassword(password);
		
		return info;
	}
	
	public RedisClusterConfiguration redisClusterConfiguration(){
		String clusterList = redisParam.get(REDIS_CLUSTER);
		
		if(null == clusterList){
			throw new NullPointerException("can not find the redis cluster nodes");
		}
		
		logger.debug("redis cluster nodes:{}",clusterList);
		
		Set<String> clusters = new HashSet<String>();

		for(String cluster : clusterList.split(";")){
			clusters.add(cluster);
		}
		RedisClusterConfiguration rc = new RedisClusterConfiguration(clusters);
		rc.setMaxRedirects(5);
		return rc;
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(){
		JedisConnectionFactory jedisConnectionFactory = null;
		
		String modelStr = redisParam.get(REDIS_MODEL);
		
		if(null == modelStr){
			throw new NullPointerException("can not find the redis model");
		}
		
		RedisMode model = RedisMode.valueOf(modelStr);
		
		logger.debug("redis model:{}",model);
		
		switch(model){
			case SINGLE:{
				jedisConnectionFactory = new JedisConnectionFactory(jedisShardInfo());
				break;
			}
			case SENTINEL:{
				jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration());
				String password = redisParam.get(REDIS_PASSWORD);
				if(StringUtils.isNotEmpty(password))
					jedisConnectionFactory.setPassword(password);
				break;
			}
			case CLUSTER:{
				jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration());
				String password = redisParam.get(REDIS_PASSWORD);
				if(StringUtils.isNotEmpty(password))
					jedisConnectionFactory.setPassword(password);
				break;
			}
			
			default:
				throw new RuntimeException("unsupported redis model");
		}
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
		jedisConnectionFactory.setTimeout(10000);
//		jedisConnectionFactory.setSotimeout(10000);

		return jedisConnectionFactory;
	}
	
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisConfig = new JedisPoolConfig();  
		jedisConfig.setMaxTotal(10000);//max connection counts
		jedisConfig.setMaxIdle(3000); 
		jedisConfig.setMaxWaitMillis(3000);
		jedisConfig.setTestOnBorrow(false);
		
		return jedisConfig;
	}
	
	@Bean
	public RedisTemplate<String, Serializable> redisTemplate(){
		RedisTemplate<String, Serializable> redisTemplate  = new RedisTemplate<String, Serializable>();
		StringRedisSerializer rss = new StringRedisSerializer();
		
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(rss);
		redisTemplate.setHashKeySerializer(rss);
		
		
		return redisTemplate;
	}
	
	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(){
		RedisMessageListenerContainer rmlc = new RedisMessageListenerContainer();
		rmlc.setConnectionFactory(jedisConnectionFactory());
		
		return rmlc;
	}
	
	
	
}
