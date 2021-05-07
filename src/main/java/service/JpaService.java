package service;

import configuration.JpaConfig;
import entity.jpa.Address;
import entity.jpa.Person;
import entity.jpa.Person_;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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


}
