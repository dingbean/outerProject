package cn.sh.outer.dao;

import java.io.Serializable;
import java.util.List;
/**
 * 通用泛型DAO接口
 * @author HOPE
 * @date 2014年5月27日
 * @param <T>
 * @param <PK>
 */
public interface GenericDao<T, PK extends Serializable> {
	/**
	 * 新增数据
	 * 
	 * @param t
	 */
	public void insert(T t);

	/**
	 * 根据主键删除数据
	 * 
	 * @param id
	 */
	public void delete(PK id);

	/**
	 * 根据主键加载数据
	 * 
	 * @param id
	 * @return
	 */
	public T load(PK id);

	/**
	 * 根据主键更新数据
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * 查询数据
	 * 
	 * @param t
	 * @return
	 */
	public List<T> query(T t);

	/**
	 * 查询数据总数
	 * 
	 * @param t
	 * @return
	 */
	public int queryCount(T t);

}
