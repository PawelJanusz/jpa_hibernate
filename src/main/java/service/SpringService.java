package service;

import entity.jpa.Student;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import repository.StudentRepository;

import java.util.Optional;

@Service
public class SpringService {

    private final StudentRepository studentRepository;

    public SpringService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable(cacheNames = "myCache", key = "#id")
    public Student getById(Long id){
        Optional<Student> byId = studentRepository.findById(id);
        System.out.println(byId.get().getName() + " " + byId.get().getSurname());
        return byId.get();
    }

    @CachePut(cacheNames = "myCache", key = "#student.id")
    public Student addStudent(Student student){
        System.out.println("Add student");
        return studentRepository.save(student);
    }
}
