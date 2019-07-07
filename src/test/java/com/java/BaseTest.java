package com.java;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-services.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {

}
