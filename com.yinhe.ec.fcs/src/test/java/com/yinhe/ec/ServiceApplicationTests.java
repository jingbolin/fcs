package com.yinhe.ec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangshilei
 * @date 2021/03/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ServiceApplicationTests {
 
    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }
    
    @Test
    public void test() {}
 
    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}