package service;

import configuration.JpaConfig;
import entity.jpa.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JpaService {

    private final EntityManager entityManager;

    public JpaService(JpaConfig jpaConfig) {
        this.entityManager = jpaConfig.getEntityManager();
    }

    public void test(){
        System.out.println("I am testing JpaService");
    }

    public void savePersonInDb(){
        entityManager.getTransaction().begin();
        Person person = new Person();
        person.setAddress(new Address("Warszawa", "Chojnowska", "54-342"));
        person.setBillingAddress(new Address("Wroclaw", "Legnicka", "53-220"));
        person.setName("Karol");
        person.setSurname("Nowak");
        entityManager.persist(person);
        entityManager.getTransaction().commit();
    }

    public Person getPersonFromDb(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> from = criteriaQuery.from(Person.class);

        criteriaQuery.select(from).where(criteriaBuilder.equal(from.get(Person_.id), id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public Person getPersonFromDbByJpql(Long id){
        String text = "select p from Person p where p.id = ";
        TypedQuery<Person> query = entityManager.createQuery(text + ":idFieldName", Person.class);
        query.setParameter("idFieldName", id);

        return query.getSingleResult();
    }

    public void modifyPerson(Long id){
        Person personFromDb = getPersonFromDb(id);
        entityManager.getTransaction().begin();
        personFromDb.setName("Arek");
        personFromDb = entityManager.merge(personFromDb);
        personFromDb.setSurname("Mokilski");
        entityManager.getTransaction().commit();
    }

    public void addStudentsIntoDb(List<Student> students){
        entityManager.getTransaction().begin();
        students.forEach(student -> entityManager.persist(student));
        entityManager.getTransaction().commit();
    }

    public void lazyLoading(){
        entityManager.getTransaction().begin();
        CriteriaQuery<Student> query = entityManager.getCriteriaBuilder().createQuery(Student.class);
        Root<Student> from = query.from(Student.class);
        query.select(from);

        TypedQuery<Student> typedQuery = entityManager.createQuery(query);
        List<Student> students = typedQuery.getResultList();
        entityManager.close();
        List<Book> books = new ArrayList<>();
        books.addAll(students.get(0).getBooks());
    }

    public List<Student> prepareStudentData() {
        Computer computer = new Computer();
        computer.setSerialNumber("XYZ123");
        computer.setDeviceName("Asus #1");
        computer.setLocalization("Krakow");

        Computer computer2 = new Computer();
        computer2.setSerialNumber("ABC123");
        computer2.setDeviceName("Asus #2");
        computer2.setLocalization("Warszawa");

        Author author = new Author();
        author.setName("Julian");
        author.setSurname("Tuwim");

        Author author2 = new Author();
        author2.setName("Jan");
        author2.setSurname("Brzechwa");

        Author author3 = new Author();
        author3.setName("Paulo");
        author3.setSurname("Coelho");

        Book book = new Book();
        book.setTitle("Wiersze");
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setTitle("Akademia Pana kleksa");
        book2.setAuthor(author2);

        Book book3 = new Book();
        book3.setTitle("Lepszy nóż w plecy niż...");
        book3.setAuthor(author3);

        Student student = new Student();
        student.setName("Andrzej");
        student.setSurname("Duda");
        student.getBooks().add(book);
        book.setStudent(student);
        student.getComputers().add(computer);
        student.getComputers().add(computer2);
        computer.getStudents().add(student);
        computer2.getStudents().add(student);

        Student student2 = new Student();
        student2.setName("Aleksander");
        student2.setSurname("Kwasniewski");
        student2.getBooks().add(book2);
        book2.setStudent(student2);
        student2.getComputers().add(computer);
        computer.getStudents().add(student2);

        Student student3 = new Student();
        student3.setName("Krzysztof");
        student3.setSurname("Kononowicz");
        student3.getBooks().add(book3);
        book3.setStudent(student3);
        student3.getComputers().add(computer2);
        computer2.getStudents().add(student3);
        return Arrays.asList(student, student2, student3);
    }


}
