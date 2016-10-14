package bean;

import entity.Person;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.File;

/**
 * Created by marcelo on 13/10/16.
 */

@RunWith(Arquillian.class)
public class PersonServiceTest {

    @EJB
    PersonService personService;

    @Deployment
    public static WebArchive createDeployment() {
        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity().asFile();


        for (File file : files) {
            System.out.println("file = " + file);
        }

        WebArchive javaArchive = ShrinkWrap.create(WebArchive.class, "test.war");
        javaArchive
                .addClass(Person.class)
                .addClass(PersonService.class)
                .addAsLibraries(files)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
//                .addAsResource("jboss-deployment-structure-xml")
                .addAsManifestResource(org.jboss.shrinkwrap.api.asset.EmptyAsset.INSTANCE, "beans.xml");
        return javaArchive;
    }

    @Test
    public void shouldPersist() {
        Person person = new Person();
        person.setName("Marcelo");
        personService.create(person);
        Assert.assertTrue(person.getId() != null);
    }


}
