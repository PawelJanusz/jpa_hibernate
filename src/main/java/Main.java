import configuration.SpringConfig;
import entity.jpa.Book;
import entity.jpa.BookInfo;
import entity.jpa.Person;
import entity.jpa.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.JpaService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        JpaService jpaService = context.getBean(JpaService.class);
//        jpaService.test();
//
//        Person person = new Person();
//        jpaService.savePersonInDb();
//        jpaService.prepareStudentData();

        List<BookInfo> books = jpaService.getBookInfoProjectionWithCriteriaApi("Krakow");
        System.out.println(books);
    }
}
