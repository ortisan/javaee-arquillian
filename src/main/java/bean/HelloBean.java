package bean;

import javax.ejb.Stateless;

@Stateless
public class HelloBean {

    public String sayHello(String name) {
        return String.format("Hello %s", name);
    }

}
