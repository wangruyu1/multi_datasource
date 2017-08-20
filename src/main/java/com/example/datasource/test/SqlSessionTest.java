package com.example.datasource.test;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


//@Component
public class SqlSessionTest implements ApplicationRunner {
	@Resource(name = "masterSqlSession")
	private SqlSession masterSqlSession;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println(masterSqlSession);
		test();

	}

	public void test() {
		System.out.println(masterSqlSession);
	}

}
