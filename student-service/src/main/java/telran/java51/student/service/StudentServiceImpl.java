package telran.java51.student.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import telran.java51.student.dao.StudentRepository;
import telran.java51.student.dto.ScoreDto;
import telran.java51.student.dto.StudentCreateDto;
import telran.java51.student.dto.StudentDto;
import telran.java51.student.dto.StudentUpdateDto;
import telran.java51.student.dto.exceptions.StudentNotFoundException;
import telran.java51.student.model.Student;

@Component
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Boolean addStudent(StudentCreateDto studentCreateDto) {
		if (studentRepository.findById(studentCreateDto.getId()).isPresent()) {
			return false;
		}
//		Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(),
//				studentCreateDto.getPassword());
		
		Student student = modelMapper.map(studentCreateDto, Student.class);//using ModelMapper
		
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		//return new StudentDto(id, student.getName(), student.getScores());
		
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto removeStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		studentRepository.deleteById(id);
//		return new StudentDto(id, student.getName(), student.getScores());
		
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		if (studentUpdateDto.getName() != null) {
			student.setName(studentUpdateDto.getName());
		}
		if (studentUpdateDto.getPassword() != null) {
			student.setPassword(studentUpdateDto.getPassword());
		}
		student =  studentRepository.save(student);
//		return new StudentCreateDto(student.getId(), student.getName(), student.getPassword());
		
		return modelMapper.map(student, StudentCreateDto.class);
	}

	@Override
	public Boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		
		boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		student = studentRepository.save(student);
		return res;
	
	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {
//		return StreamSupport.stream(studentRepository.findAll().spliterator(), false) 
//								.filter(s -> name.equalsIgnoreCase(s.getName()))
//								.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
//								.collect(Collectors.toList());
		
		return studentRepository.getByName(name).stream()
//		.map(s->new StudentDto(s.getId(), s.getName(), s.getScores()))
		.map(s->modelMapper.map(s, StudentDto.class))
		.toList();
	}

	@Override
	public long getStudentsNamesQuantity(Set<String> names) {
//		return StreamSupport.stream(studentRepository.findAll().spliterator(), false) 
//				.filter(s -> names.contains(s.getName()))
//				.count();				
		
	return studentRepository.countByNameInIgnoreCase(names);
	}

	@Override
	public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
//		return StreamSupport.stream(studentRepository.findAll().spliterator(), false) 
//				.filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) > minScore)
//				.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
//				.collect(Collectors.toList());
		
		return studentRepository.getByScoresGreaterThan(exam,minScore).stream()
			//.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
			.map(s->modelMapper.map(s, StudentDto.class))	
			.collect(Collectors.toList());
	}

	

}
