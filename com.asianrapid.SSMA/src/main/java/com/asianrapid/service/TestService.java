package com.asianrapid.service;

import org.springframework.stereotype.Service;

import com.asianrapid.commons.AsianrapidResult;
import com.asianrapid.commons.BSTableParam;
import com.asianrapid.commons.BSTableResult;
import com.asianrapid.pojo.Student;

@Service
public interface TestService {

	BSTableResult listStudent(BSTableParam param);
	AsianrapidResult insertStudent(Student student);
	AsianrapidResult loadStudent(Integer id);
	AsianrapidResult updateStudent(Student student);
	AsianrapidResult deleteStudent(Integer id);
	AsianrapidResult printStudentList(String url);
}
