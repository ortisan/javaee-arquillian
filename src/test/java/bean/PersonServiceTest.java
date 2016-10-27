package bean;

import entity.Person;
import iterfacess.ICallable;
import iterfacess.IPersonService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
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


//    @EJB(lookup = "java:module/PersonService")


    @Deployment
    public static WebArchive createDeployment() {
        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity().asFile();

        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war");
        webArchive
                .addClasses(Person.class, ICallable.class, IPersonService.class, PersonService.class)
                .addAsLibraries(files)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
//                .addAsResource("jboss-deployment-structure-xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        return webArchive;
    }

    @EJB(beanName = "Marcelo")
    IPersonService personService;

    @Test
    public void shouldPersist() {
        Person person = new Person();
        person.setName("Marcelo");
        personService.create(person);
        Assert.assertTrue(person.getId() != null);
    }
}
