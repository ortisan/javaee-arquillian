package iterfacess;

import entity.Person;

import java.io.Serializable;

/**
 * Created by marcelo on 26/10/16.
 */
public interface ICallable extends Serializable {

    String call();
}
