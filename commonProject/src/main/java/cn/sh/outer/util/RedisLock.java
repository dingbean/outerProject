package cn.sh.outer.util;

import cn.sh.outer.dao.RedisDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class RedisLock {

	/** 加锁标志 */
	public static final String LOCKED = "TRUE";
	/** 毫秒与毫微秒的换算单位 1毫秒 = 1000000毫微秒 */
	public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;
	/** 默认超时时间（毫秒） */
	public static final long DEFAULT_TIME_OUT = 1000;
	public static final Random RANDOM = new Random();
	/** 锁的超时时间（秒），过期删除 */
	public static final int EXPIRE = 3 * 60;
	/** RedisLock对象map */
	private static Map<String,RedisLock> lockMap = new HashMap<String,RedisLock>();;

	private String key;
	private RedisDao<String, String> redisDao;
	
	@SuppressWarnings("unchecked")
	public RedisLock(String key) {
		this.key = "lock_" + key;
		redisDao = (RedisDao<String, String>)SpringContextUtil.getBean("redisDao");
	}

	/**
	 * 加锁 应该以： lock(); try { doSomething(); } finally { unlock()； } 的方式调用
	 * 
	 * @param timeout
	 *            超时时间
	 * @return 成功或失败标志
	 */
	public boolean lock(long timeout) {
		long nano = System.nanoTime();
		timeout *= MILLI_NANO_CONVERSION;
		try {
			while ((System.nanoTime() - nano) < timeout) {
				if (this.redisDao.saveNxValue(this.key, LOCKED,EXPIRE,TimeUnit.SECONDS)) {
					return true;
				}
				// 短暂休眠，避免出现活锁
				Thread.sleep(3, RANDOM.nextInt(500));
			}
		} catch (Exception e) {
			throw new RuntimeException("Locking error", e);
		}
		return false;
	}

	/**
	 * 加锁 应该以： lock(); try { doSomething(); } finally { unlock()； } 的方式调用
	 * 
	 * @param timeout
	 *            超时时间
	 * @param expire
	 *            锁的超时时间（秒），过期删除
	 * @return 成功或失败标志
	 */
	public boolean lock(long timeout, int expire) {
		long nano = System.nanoTime();
		timeout *= MILLI_NANO_CONVERSION;
		try {
			while ((System.nanoTime() - nano) < timeout) {
				if (this.redisDao.saveNxValue(this.key, LOCKED,expire,TimeUnit.SECONDS)) {
					return true;
				}
				// 短暂休眠，避免出现活锁
				Thread.sleep(3, RANDOM.nextInt(500));
			}
		} catch (Exception e) {
			throw new RuntimeException("Locking error", e);
		}
		return false;
	}

	/**
	 * 加锁 应该以： lock(); try { doSomething(); } finally { unlock()； } 的方式调用
	 * 
	 * @return 成功或失败标志
	 */
	public boolean lock() {
		return lock(DEFAULT_TIME_OUT);
	}

	/**
	 * 解锁 无论是否加锁成功，都需要调用unlock 应该以： lock(); try { doSomething(); } finally { unlock()； } 的方式调用
	 */
	public void unlock() {
		try {
			if (this.redisDao.readValue(key) != null) {
				this.redisDao.deleteValue(key);
			}
		} finally {
			//this.shardedJedisPool.returnResource(this.jedis);
		}
	}
	
	/*
	 * 获取锁
	 */
	public static RedisLock getRedisLock(String lockKey){
		RedisLock lock = null;
		if(lockMap.get(lockKey) != null){
			lock = lockMap.get(lockKey);
		}else{
			lock = new RedisLock(lockKey);
			lockMap.put(lockKey, lock);
		}
		
		return lock;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
