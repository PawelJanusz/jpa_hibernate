import configuration.SpringConfig;
import entity.jpa.Book;
import entity.jpa.BookInfo;
import entity.jpa.Person;
import entity.jpa.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.HibernateService;
import service.JpaService;
import service.SpringService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        JpaService jpaService = context.getBean(JpaService.class);
        HibernateService hibernateService = context.getBean(HibernateService.class);
        SpringService springService = context.getBean(SpringService.class);
//        jpaService.test();
//
//        Person person = new Person();
//        jpaService.savePersonInDb();
//        jpaService.prepareStudentData();
//
//        List<Book> books = jpaService.getBooksTakenByStudentsFromLocationJpql("Krakow");
//        System.out.println(books);
        Student s = springService.getById(1L);

        Student student = new Student();
        student.setName("Aleks");
        student.setSurname("Kedra");
        student = springService.addStudent(student);
        springService.getById(student.getId());
        s = springService.getById((1L));

    }
}
