package configuration.jpa;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Component
public class JpaConfig {

    public EntityManager getEntityManager(){
        return Persistence.createEntityManagerFactory("jpa-configuration")
                .createEntityManager();
    }
}
