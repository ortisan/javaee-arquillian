package iterfacess;

import entity.Person;

/**
 * Created by marcelo on 26/10/16.
 */
public interface IPersonService extends ICallable {

    Person create(Person person);
}
