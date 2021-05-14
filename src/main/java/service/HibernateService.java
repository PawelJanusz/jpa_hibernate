package service;

import configuration.hibernate.HibernateConfig;
import entity.jpa.Book;
import entity.jpa.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class HibernateService {

    private final SessionFactory sessionFactory;

    public HibernateService() {
        this.sessionFactory = HibernateConfig.sessionFactory();
        System.out.println("Succeed");
    }

    public void lazyLoading(){
        Session session = sessionFactory.openSession();
        CriteriaQuery<Student> query = session.getCriteriaBuilder().createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);

        query.select(studentRoot);

        TypedQuery<Student> typedQuery = session.createQuery(query);

        List<Student> students = typedQuery.getResultList();
//        session.close();
        List<Book> books = new ArrayList<>();
        books.addAll(students.get(0).getBooks());
    }

    public void testCache() {
        Session session1 = sessionFactory.openSession();
        Student student1 = session1.byId(Student.class).load(2L);
        Student student2 = session1.byId(Student.class).load(2L);

        session1.clear();
        Student student3 = session1.byId(Student.class).load(2L);
    }
}
