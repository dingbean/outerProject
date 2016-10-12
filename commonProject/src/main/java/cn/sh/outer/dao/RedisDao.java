package cn.sh.outer.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;

/**
 * some method related to redis
 * support data structure:hash list set zset
 * @author wyy
 *
 */
@Repository("redisDao")
@SuppressWarnings("unchecked")
public class RedisDao <K , V extends Serializable> {
	@Autowired
	private RedisTemplate<K, V> rt;
	
	/**
	 * save value
	 * @param key
	 * @param value
	 */
	public void saveValue(K key, V value) {
		rt.opsForValue().set(key, value);
	}
	
	/**
	 * save value with timeout
	 * @param key
	 * @param value
	 * @param timeout
	 * @param timeUnit
	 */
	public void saveValue(K key, V value, int timeout, TimeUnit timeUnit){
		rt.opsForValue().set(key, value,timeout,timeUnit);
	}
	
	/**
	 * read value
	 * @param key
	 * @return
	 */
	public V readValue(K key){
		return rt.opsForValue().get(key);
	}
	
	/**
	 * delte value
	 * @param key
	 */
	public void deleteValue(K key){
		rt.delete(key);
	}
	
	/**
	 * save value if the provided key is absent
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean saveNxValue(K key,V value){
		return rt.opsForValue().setIfAbsent(key, value);
	}
	
	/**
	 * save value with timeout if the provided key is absent
	 * @param key
	 * @param value
	 * @param timeout
	 * @param timeUnit
	 * @return
	 */
	public boolean saveNxValue(K key,V value,int timeout, TimeUnit timeUnit){
		if(saveNxValue(key, value)){
			rt.expire(key, timeout, timeUnit);
			return true;
		}
		return false;
	}
	
	/**
	 * match keys by the pattern
	 * @param pattern
	 * @return
	 */
	public Set<K> getKeysByPattern(K pattern){
		return rt.keys(pattern);
	}
	
	/**
	 * put element into map
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void mapPut(K key,Object hashKey,Object value){
		rt.opsForHash().put(key, hashKey, value);
	}
	
	/**
	 * merge the provided map into the map with specified key 
	 * @param key
	 * @param map
	 */
	public void mapPutAll(K key,Map<Object,Object> map){
		rt.opsForHash().putAll(key, map);
	}
	
	/**
	 * return the element with specified key from the map
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object mapGetValue(K key,Object hashKey){
		return rt.opsForHash().get(key, hashKey);
	}
	
	/**
	 * return the map
	 * @param key
	 * @return
	 */
	public Map<Object, Object> mapGet(K key){
		return rt.opsForHash().entries(key);
	}
	
	/**
	 * remove the element with specified key
	 * @param key
	 * @param hashKeys
	 */
	public void mapRemove(K key,Object ... hashKeys){
		rt.opsForHash().delete(key, hashKeys);
	}
	
	/**
	 * push element into list from the left side
	 * @param key
	 * @param value
	 */
	public void listlPush( K key,  V value){
		rt.opsForList().leftPush(key, value);
	}
	
	/**
	 * push elements into list from the left side
	 * @param key
	 * @param values
	 */
	public void listlPush( K key, List<V> values){
		rt.opsForList().leftPushAll(key, values);
	}
	
	/**
	 * push element into list from the right side
	 * @param key
	 * @param value
	 */
	public void listrPush( K key,  V value){
		rt.opsForList().rightPush(key, value);
	}
	
	/**
	 * push elements into list from the right side
	 * @param key
	 * @param values
	 */
	public void listrPush( K key, List<V> values){
		rt.opsForList().rightPushAll(key, values);
	}
	
	/**
	 * 左侧移除list元素
	 * @param key
	 * @return
	 */
	public V listlPop(K key){
		return rt.opsForList().leftPop(key);
	}
	
	/**
	 * pop the list element from the right side 
	 * @param key
	 * @return
	 */
	public V listrPop(K key){
		return rt.opsForList().rightPop(key);
	}
	
	/**
	 * pop elements begins at the specified beginIndex and 
	 * extends to the index endIndex by the left
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<V> listPopAll(K key,long start,long end){
		List<V> list = new ArrayList<V>();
		for(long i = start; i <= end ; i++){
			V value = listlPop(key);
			if(null == value) break;
			list.add(value);
		}
		return list;
	}
	
	/**
	 * return elements begins at the specified beginIndex and 
	 * extends to the index endIndex by the left
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<V> listRange(K key,long start,long end){
		List<V> list = null; 
		list = rt.opsForList().range(key, start, end);
		return null == list ? new ArrayList<V>() : list;
	}
	
	/**
	 * return previous elements with specified number from the left side 
	 * @param key
	 * @param num
	 * @return
	 */
	public List<V> listRange(K key,long num){
		return listRange(key,0,num-1);
	}
	
	/**
	 * return the list from the left side
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<V> listRangeAll(K key){
		return listRange(key,0,listSize(key));
	}
	
	/**
	 * remove the specified element from the list 
	 * @param key
	 * @param value
	 */
	public void listRemove(  K key, V value){
		rt.opsForList().remove(key, 1, value);
	}
	
	/**
	 * return the list size
	 * 
	 * @param key
	 * @return
	 */
	public long listSize(  K key){
		return rt.opsForList().size(key);
	}
	
	/**
	 * add elements into the set
	 * @param key
	 * @param values
	 */
	public void setAdd(K key, V ... values){
		rt.opsForSet().add(key, values);
	}
	
	/**
	 * remove elements from the set
	 * @param key
	 * @param values
	 */
	public void setRemove(K key, Object ... values){
		rt.opsForSet().remove(key, values);
	}
	
	/**
	 * return the specified element is member of the set
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setIsMemeber(K key, Object value){
		return rt.opsForSet().isMember(key, value);
	}
	
	/**
	 * return the set 
	 * @param key
	 * @return
	 */
	public Set<V> setMembers(K key){
		return rt.opsForSet().members(key);
	}
	
	/**
	 * pop element from the set
	 * @param key
	 * @return
	 */
	public V setPop(K key){
		return rt.opsForSet().pop(key);
	}
	
	/**
	 * return the set size
	 * @param key
	 * @return
	 */
	public long setSize(K key){
		return rt.opsForSet().size(key);
	}
	
	/**
	 * add element into the sorted set
	 * @param key
	 * @param value
	 * @param score the sorted value
	 */
	public void zsetAdd(K key,  V value,double score){
		rt.opsForZSet().add(key, value, score);
	}
	
	/**
	 * add elements into the sored set
	 * @param key
	 * @param tuples
	 */
	public void zsetAddAll(  K key,  Set<TypedTuple<V>> tuples){
		rt.opsForZSet().add(key, tuples);
	}
	
	/**
	 * return the elements begins at the specified beginIndex and 
	 * extends to the index endIndex
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<V> zsetRange(  K key,long start,long end){
		Set<V> set = null;
		set =  rt.opsForZSet().range(key, start, end);
		return set == null ? new HashSet<V>() : set;
	}
	
	/**
	 * return the elements score between min and max
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<V> zsetRangeByScore(  K key,double min,double max){
		Set<V> set = null;
		set =  rt.opsForZSet().rangeByScore(key, min, max);
		return set == null ? new HashSet<V>() : set;
	}
	
	/**
	 * return the elements with the specified number 
	 * @param key
	 * @param num
	 * @return
	 */
	public Set<V> zsetRange(  K key,long num){
		return zsetRange(key,0,num -1);
	}
	
	/**
	 * return the sorted set
	 * @param key
	 * @return
	 */
	public Set<V> zsetRangeAll(  K key){
		return zsetRange(key, 0, zsetSize(key));
	}
	
	/**
	 * return the elements with the specified number by reverse
	 * @param key
	 * @param num
	 * @return
	 */
	public Set<V> zsetReverseRange(  K key,long num){
		Set<V> set = null;
		long size = zsetSize(key);
		set =  rt.opsForZSet().range(key, size - num, size);
		return set == null ? new HashSet<V>() : set;
	}
	
	/**
	 * return the elements score between min and max by reverse
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<V> zsetReverseRangeByScore(  K key,double min,double max){
		Set<V> set = null;
		set =  rt.opsForZSet().reverseRangeByScore(key, min, max);
		return set == null ? new HashSet<V>() : set;
	}
	
	/**
	 * remove the elements begins at the specified beginIndex and 
	 * extends to the index endIndex
	 * @param key
	 * @param start
	 * @param end
	 */
	public void zsetRemoveRange(K key,long start,long end){
		rt.opsForZSet().removeRange(key, start, end);
	}
	
	/**
	 * remove the elements with the specified number
	 * @param key
	 * @param num
	 */
	public void zsetRemoveRange(K key,long num){
		zsetRemoveRange(key,0,num -1);
	}
	
	/**
	 * remove the elements score between min and max
	 * @param key
	 * @param min
	 * @param max
	 */
	public void zsetRemoveByScore(K key,double min,double max){
		rt.opsForZSet().removeRangeByScore(key, min, max);
	}
	
	/**
	 * remove the elements with the specified number by reverse
	 * @param key
	 * @param num
	 */
	public void zsetReverseRomove(  K key,long num){
		long size = zsetSize(key);
		rt.opsForZSet().removeRange(key, size - num, size);
	}
	
	/**
	 * remove the specified elements from sorted set
	 * @param key
	 * @param values
	 */
	public void zsetRemove(  K key,Object ... values){
		rt.opsForZSet().remove(key, values);
	}
	
	/**
	 * return the sorted set size
	 * @param key
	 * @return
	 */
	public long zsetSize(K key){
		return rt.opsForZSet().zCard(key);
	}
	
	/**
	 * return the rank in the zset
	 * @param key
	 * @return
	 */
	public long rank(K key,V value){
		return rt.opsForZSet().rank(key, value);
	}
	
	/**
	 * return whether the specified key exist
	 * @param key
	 * @return
	 */
	public boolean exist(K key){
		return rt.hasKey(key);
	}
	
	/**
	 * increment the counter by specifed count and return the incremented value
	 * @param key
	 * @param count
	 * @return
	 */
	public long increment(K key,long count){
		return rt.opsForValue().increment(key, count);
	}
	
	/**
	 * set the expire time to the specified key
	 * @param key
	 * @param timeout
	 * @param unit
	 */
	public void expire(K key,long timeout,TimeUnit unit){
		rt.expire(key, timeout, unit);
	}
	
	/**
	 * publish the message to the specified channel
	 * @param channel
	 * @param message
	 */
	public void publishMessage(String channel,V message){
		rt.convertAndSend(channel, message);
	}
}
