package cn.sh.outer.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.sh.ideal.manager.model.RelationMenu;


public interface RelationMenuDao {
	
	 //获取序列ID
	 public  BigDecimal getSeqPrimaryKey();
	
	 //删除所有的关联菜单
	 public  int deleteByMenuKey(BigDecimal id);
	 
	 //批量插入
	 public  void insertRelationMenuBatch(List<RelationMenu> list)throws RuntimeException;

	 public  int insert(RelationMenu record);

	 public  int insertSelective(RelationMenu record);

	 public  RelationMenu selectByPrimaryKey(Long id);

	 public  int updateByPrimaryKeySelective(RelationMenu record);

	 public  int updateByPrimaryKey(RelationMenu record);
}