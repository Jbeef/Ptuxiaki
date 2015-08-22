package com.lisp.services.rest;

import com.google.gson.Gson;
import com.lisp.services.ejb.SingletonInterpreterBeanLocal;
import com.lisp.services.ejb.StatelessUserBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.armedbear.lisp.Package;

/**
 * REST Web Service
 *
 * @author Akis
 */
@Path("service")
@Stateless
public class RestService {

    @Context
    private UriInfo context;

    @Inject
    Gson gson;

    @EJB
    private SingletonInterpreterBeanLocal singletonInterpreterBean;
    @EJB
    private StatelessUserBeanLocal statelessUserBean;

    public RestService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/homePackage")
    public String getHomePackage() {
        return gson.toJson(statelessUserBean.getHomePackage());
    }

    /*
     @GET
     @Produces(MediaType.TEXT_PLAIN)
     @Path("/homePackage")
     public Response getHomePackage() {
     NewCookie cookie = new NewCookie("package", statelessUserBean.getHomePackage().getName());
     return Response.ok().cookie(cookie).build();
     }
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/execute/{package}/{command}")
    public String executeCommand(@PathParam("package") String homePackage,
            @PathParam("command") String command) {
        //Package p = gson.fromJson(homePackage, Package.class);
        Package p = new Package(homePackage);

        return gson.toJson(singletonInterpreterBean.executeCommand(p, command));
    }
    /*
     @POST
     @Consumes(MediaType.APPLICATION_JSON)
     //@Produces(MediaType.APPLICATION_JSON)
     @Path("/execute")
     public String executeCommand(String homePackage, String command) {
     Package p = gson.fromJson(homePackage, Package.class);
     System.out.println(singletonInterpreterBean.executeCommand(p, command));
     return gson.toJson(singletonInterpreterBean.executeCommand(p, command));
     }
     */

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

}
