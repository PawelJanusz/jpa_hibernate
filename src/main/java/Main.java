import entity.jpa.Person;
import entity.jpa.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.JpaService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        JpaService jpaService = context.getBean(JpaService.class);

        Person person = new Person();
        jpaService.savePersonInDb();
    }
}
