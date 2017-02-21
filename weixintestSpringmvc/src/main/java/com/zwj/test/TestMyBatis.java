package com.zwj.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zwj.bean.User;
import com.zwj.bean.WeixinTest;
import com.zwj.service.IUserService;
import com.zwj.service.IWeixinTestService;


@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
//	private ApplicationContext ac = null;
	@Resource
	private IUserService userService;
	@Resource
	private IWeixinTestService weixinTestService;

//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}

	@Test
	public void test1() {
		//User user = userService.getUserById(1);
		WeixinTest w=new WeixinTest();
		w.setNickname("22");
		w.setOpenid("333");
		weixinTestService.insert(w);
		// System.out.println(user.getUserName());
		// logger.info("值："+user.getUserName());
		//logger.info(JSON.toJSONString(user));
	}
}
