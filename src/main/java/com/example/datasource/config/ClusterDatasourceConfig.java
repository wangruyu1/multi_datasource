package com.example.datasource.config;

import java.io.IOException;

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
 * 从数据源
 * 
 * @author 王如雨
 *
 */
@Configuration
// @MapperScan(basePackages = ClusterDatasourceConfig.CLUSTER_MAPPER_LOCATION,
// sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDatasourceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClusterDatasourceConfig.class);

	static final String CLUSTER_MAPPER_LOCATION = "com.example.datasource.base";
	static final String CLUSTER_XMLMAPPER_LOCATION = "classpath:mapper/UserInfoMapper.xml";

	@ConfigurationProperties(prefix = "cluster.datasource")
	@Bean
	public DataSourceProperties clusteDatasourceProperties() {
		LOGGER.info("注入库连接信息.\n");
		return new DataSourceProperties();
	}

	@Bean
	public DataSource clusterDataSource() {
		LOGGER.info("注入从库连接信息.\n");
		DruidDataSource druidDataSource = new DruidDataSource();
		DataSourceProperties dsp = clusteDatasourceProperties();
		druidDataSource.setDriverClassName(dsp.getDriverClassName());
		druidDataSource.setUrl(dsp.getUrl());
		druidDataSource.setUsername(dsp.getUsername());
		druidDataSource.setPassword(dsp.getPassword());
		return druidDataSource;
	}

	@Bean
	public DataSourceTransactionManager clusteTransactionManager(
			@Qualifier("masterDataSource") DataSource clusterDataSource) {
		LOGGER.info("从库事务管理注入.");
		return new DataSourceTransactionManager(clusterDataSource);
	}

	@Bean
	public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusteDataSource) {
		LOGGER.info("注入从库sqlSessionFactory.");
		try {
			final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(clusteDataSource);
			sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
					.getResources(ClusterDatasourceConfig.CLUSTER_XMLMAPPER_LOCATION));
			return sessionFactory.getObject();
		} catch (Exception e) {
			LOGGER.error("注入从库sqlSessionFactory失败.", e);
		}
		return null;
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public SqlSessionTemplate clusterSqlSession(
			@Qualifier("clusterSqlSessionFactory") SqlSessionFactory clusterSqlSessionFactory) {
		SqlSessionTemplate clusterSqlSession = new SqlSessionTemplate(clusterSqlSessionFactory);
		LOGGER.info("注入从库sqlsession(" + clusterSqlSession + ").");
		return clusterSqlSession;
	}

}
