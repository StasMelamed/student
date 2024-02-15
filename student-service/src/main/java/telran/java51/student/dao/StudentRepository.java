package telran.java51.student.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.BaseStream;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mongodb.connection.Stream;

import telran.java51.student.model.Student;


public interface StudentRepository extends CrudRepository<Student, Integer>{
	
	Collection<Student> getByName(String name);
	
   long countByNameInIgnoreCase(Set<String> names);
   
   @Query("{'scores.?0':{$gt:?1}}")
   Collection<Student> getByScoresGreaterThan(String exam,int minScore);
	
//	Student save(Student student);
//	
//	Optional<Student> findById(int id);
//	
//	Collection<Student> findAll();
//	
//	void deleteById(int id);
}
