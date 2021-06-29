package com.te.studentsdata.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.studentsdata.model.Student;
import com.te.studentsdata.repository.StudentRepository;
import com.te.studentsdataexception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/students")
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getEmployeeById(@PathVariable(value = "id") Long studentId)
			throws ResourceNotFoundException {
		Student student = StudentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " +studentId));
		return ResponseEntity.ok().body(student);
	}

	@PostMapping("/student")
	public Student createStudent(@Valid @RequestBody Student student) {
		return studentRepository.save(student);
	}

	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
			@Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
		Student student = StudentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentId));

		student.setEmailId(studentDetails.getmarks());
		student.setLastName(studentDetails.getUserId());
		student.setFirstName(studentDetails.getname());
		final Student updatedStudent = StudentRepository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}

	@DeleteMapping("/sudent/{id}")
	public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId)
			throws ResourceNotFoundException {
		Student student = StudentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " +studentId));

		StudentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
