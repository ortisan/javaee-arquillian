package rest;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by marcelo on 09/10/16.
 */

@Singleton
@Lock(LockType.READ)
@Path("/ejb")
public class SimpleRestEjb {

    @GET
    public String ejb() {
        return "Why Ejb";
    }


}