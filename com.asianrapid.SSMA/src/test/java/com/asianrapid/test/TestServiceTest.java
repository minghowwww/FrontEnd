package com.asianrapid.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.asianrapid.commons.AsianrapidResult;
import com.asianrapid.commons.BSTableParam;
import com.asianrapid.commons.BSTableResult;
import com.asianrapid.pojo.Student;
import com.asianrapid.service.TestService;
import com.asianrapid.test.common.BesicTest;

/**
 * @author Administrator
 * 
 * 每一个service中的方法，都需要有一个对应的测试方法，
 * 在项目结束的时候，项目经理会运行一遍所有的测试方法。
 */
public class TestServiceTest extends BesicTest{

	@Autowired
	private TestService testService;
	
	@Test
	public void getAll() {
		BSTableParam param = new BSTableParam();
		param.setPage(1);
		param.setRows(10);
		param.setSort("stuId");
		BSTableResult result = testService.listStudent(param);
		Assert.assertEquals("查询结果有10个，分页正常", 10, result.getRows().size());
	}
	
	@Test
	public void insertStudent() {
		Student student = new Student();
		student.setName("老大");
		student.setIsDeleted(0);
		student.setAge("12");
		student.setScore("A");
		student.setSchool("清华");
		student.setCreated(new Date());
		student.setUpdated(new Date());
		AsianrapidResult result = testService.insertStudent(student);
//		Assert.assertEquals("插入成功", 200, result.getCode());
		Assert.assertEquals("插入成功", 200, (int)result.getCode());
	}
}
