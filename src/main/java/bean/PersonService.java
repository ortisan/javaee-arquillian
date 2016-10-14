package bean;

import entity.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by marcelo on 13/10/16.
 */
@Stateless
public class PersonService {

    @PersistenceContext
    EntityManager entityManager;

    public Person create(Person person) {
        entityManager.persist(person);
        return person;
    }


}
