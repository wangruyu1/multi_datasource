package com.example.datasource.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 主数据源
 * 
 * @author 王如雨
 *
 */
@Configuration
// @MapperScan(basePackages = MasterDatasourceConfig.MASTER_MAPPER_LOCATION,
// sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDatasourceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(MasterDatasourceConfig.class);

	static final String MASTER_MAPPER_LOCATION = "com.example.datasource.base";
	static final String MASTER_XMLMAPPER_LOCATION = "classpath:mapper/UserInfoMapper.xml";

	@ConfigurationProperties(prefix = "master.datasource")
	@Primary
	@Bean
	public DataSourceProperties masterDatasourceProperties() {
		LOGGER.info("注入主库连接信息.");
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	public DataSource masterDataSource() {
		LOGGER.info("注入主库数据源.");
		DruidDataSource druidDataSource = new DruidDataSource();
		DataSourceProperties dsp = masterDatasourceProperties();
		druidDataSource.setDriverClassName(dsp.getDriverClassName());
		druidDataSource.setUrl(dsp.getUrl());
		druidDataSource.setUsername(dsp.getUsername());
		druidDataSource.setPassword(dsp.getPassword());
		return druidDataSource;
	}

	@Bean
	@Primary
	public DataSourceTransactionManager masterTransactionManager(
			@Qualifier("masterDataSource") DataSource masterDataSource) {
		LOGGER.info("主库事务管理注入.");
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(masterDataSource);
		return dataSourceTransactionManager;
	}

	@Bean
	@Primary
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) {
		LOGGER.info("注入主库sqlSessionFactory.");
		try {
			final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(masterDataSource);
			sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
					.getResources(MasterDatasourceConfig.MASTER_XMLMAPPER_LOCATION));
			return sessionFactory.getObject();
		} catch (Exception e) {
			LOGGER.error("注入主库sqlSessionFactory失败.", e);
		}
		return null;
	}

	/**
	 * 多例的sqlsession很重要
	 * 
	 * @param masterSqlSessionFactory
	 * @return masterSqlSession
	 */
	@Bean
	@Primary
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public SqlSessionTemplate masterSqlSession(
			@Qualifier("masterSqlSessionFactory") SqlSessionFactory masterSqlSessionFactory) {
		SqlSessionTemplate masterSqlSession = new SqlSessionTemplate(masterSqlSessionFactory);
		LOGGER.info("注入主库sqlsession(" + masterSqlSession + ").");
		return masterSqlSession;
	}

}
