package com.asianrapid.test.common;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @author Administrator
 *
 * 所有测试类都需要继承于这个类，去实现功能。
 */
@ContextConfiguration(locations= {
		"classpath:spring/applicationContext-*.xml","classpath:spring/springmvc.xml"
})
public class BesicTest extends AbstractTransactionalJUnit4SpringContextTests{

}
