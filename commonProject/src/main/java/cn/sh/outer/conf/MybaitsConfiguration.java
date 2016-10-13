package cn.sh.outer.conf;

import javax.sql.DataSource;

import cn.sh.outer.dao.SysInitMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * mybatis configuration
 * 
 * @author dk
 * 2016-10-12
 * @since v1.1.6 
 *
 */

@Configuration
public class MybaitsConfiguration {
	/** 
	 * 如果项目涉及数据源，要将autowired注解去掉，并手动在spring配置文件中初始化该属性
	 */
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(){
		Environment environment = new Environment("COMMON", new SpringManagedTransactionFactory(), dataSource);

		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);

		configuration.addMapper(SysInitMapper.class);
		
		return new SqlSessionFactoryBuilder().build(configuration);
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(){
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
