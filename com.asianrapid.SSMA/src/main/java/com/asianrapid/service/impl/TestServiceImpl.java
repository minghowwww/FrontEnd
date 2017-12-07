package com.asianrapid.service.impl;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asianrapid.commons.AsianrapidResult;
import com.asianrapid.commons.BSTableParam;
import com.asianrapid.commons.BSTableResult;
import com.asianrapid.commons.StudentCellStyle;
import com.asianrapid.dao.StudentMapper;
import com.asianrapid.pojo.Student;
import com.asianrapid.pojo.StudentExample;
import com.asianrapid.pojo.StudentExample.Criteria;
import com.asianrapid.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private StudentCellStyle studentCellStyle;
	
	private static final Logger logger = Logger.getLogger(TestService.class);
	
	@Override
	public BSTableResult listStudent(BSTableParam param) {
		
		BSTableResult result = new BSTableResult();
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		if (param.getSearch() != null && !"".equals(param.getSearch())) {
			criteria.andNameLike("%" + param.getSearch() + "%");
		}
		criteria.andIsDeletedEqualTo(0);
		PageHelper ph = new PageHelper();
		ph.startPage(param.getPage(), param.getRows());
		List<Student> list = studentMapper.selectByExample(example);
		PageInfo pi = new PageInfo<>(list);
		result.setRows(list);
		result.setTotal(pi.getTotal());
		
		logger.info("查询student表未删除的列");
		return result;
	}

	@Override
	public AsianrapidResult insertStudent(Student student) {
		try {
			studentMapper.insert(student);
			logger.debug("插入student记录， name=" + student.getName());
			return AsianrapidResult.ok();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AsianrapidResult.build(500, "服务器异常", null);
		}
	}

	@Override
	public AsianrapidResult loadStudent(Integer id) {
		try {
			StudentExample example = new StudentExample();
			Criteria criteria = example.createCriteria();
			criteria.andStuIdEqualTo(id);
			criteria.andIsDeletedEqualTo(0);
			List<Student> list = studentMapper.selectByExample(example);
			logger.info("查询student，id=" + id);
			return AsianrapidResult.ok(list.get(0));
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(e.getMessage());
			return AsianrapidResult.build(500, "服务器异常", null);
		}
		
	}

	@Override
	public AsianrapidResult updateStudent(Student student) {
		try {
			studentMapper.updateByPrimaryKeySelective(student);
			logger.debug("更新student表，用户名=" + student.getName());
			return AsianrapidResult.ok();
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(e.getMessage());
			return AsianrapidResult.build(500, "服务器异常", null);
		}
	}

	@Override
	public AsianrapidResult deleteStudent(Integer id) {
		try {
			Student student = new Student();
			student.setStuId(id);
			student.setIsDeleted(1);
			int key = studentMapper.updateByPrimaryKeySelective(student);
			if (key == 1) {
				return AsianrapidResult.ok();
			}
			
			logger.debug("删除student，id=" + id);
			return AsianrapidResult.build(500, "删除记录有瑕疵", null);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(e.getMessage());
			return AsianrapidResult.build(500, "服务器异常", null);
		}
	}

	@Override
	public AsianrapidResult printStudentList(String url) {
		try {
			StudentExample example = new StudentExample();
			Criteria criteria = example.createCriteria();
			criteria.andIsDeletedEqualTo(0);
			List<Student> list = studentMapper.selectByExample(example);
			studentCellStyle.exportExcel(new FileOutputStream(url), list);
			logger.info("导出student表为excel表格");
			return AsianrapidResult.ok();
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(e.getMessage());
			return AsianrapidResult.build(500, "服务器异常", null);
		}
	}

	
}
