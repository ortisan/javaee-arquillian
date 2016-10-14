package bean;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

@RunWith(Arquillian.class)
public class HelloBeanTest {

    @EJB
    HelloBean helloBean;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class, "test.jar");
        javaArchive
                .addClass(IHelloRemote.class)
                .addClass(HelloBean.class)
                .addAsManifestResource(org.jboss.shrinkwrap.api.asset.EmptyAsset.INSTANCE, "beans.xml");

        return javaArchive;
    }

    @Test
    public void should_say_hello() {
        Assert.assertEquals("Hello Marcelo", helloBean.sayHello("Marcelo"));
    }


}
