package bean;

import entity.Person;
import iterfacess.IPersonService;

import javax.ejb.Local;
import javax.ejb.LocalHome;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by marcelo on 13/10/16.
 */
@Stateless(name = "Marcelo")
public class PersonService implements IPersonService {

    @PersistenceContext
    EntityManager entityManager;

    public Person create(Person person) {
        System.out.println("@@@@@@@@@@@@@entityManager = " + entityManager);
        entityManager.persist(person);
        return person;
    }


    public String call() {
        return "Chamando....";
    }
}
