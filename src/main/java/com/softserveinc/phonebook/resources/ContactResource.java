
package com.softserveinc.phonebook.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.softserveinc.phonebook.api.Contact;
import com.softserveinc.phonebook.service.ContactService;

@RolesAllowed({ "ADMIN", "USER" })
@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
@Service
@Timed(name = "contactTimer")
@Metered(name = "contactMeter")
@ExceptionMetered(name = "contactExceptionMeter")
public class ContactResource {

    @Autowired
    private ContactService contactService;

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {
        Contact contact = contactService.getContactById(id);
        return Response.ok(contact).build();
    }

    @GET
    @Path("/getByFullName")
    public Response getContactsByFullName(@QueryParam("firstName") @NotBlank String firstName,
            @QueryParam("lastName") @NotBlank String lastName) {
        List<Contact> contacts = contactService.getByFullName(firstName, lastName);
        if (contacts.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(contacts).build();
    }

    @GET
    public Response getContactList() {
        List<Contact> contacts = contactService.getAllContacts();
        return Response.ok(contacts).build();
    }

    @POST
    public Response createContact(@Valid Contact contact) throws URISyntaxException {
        int newContactId = contactService.createContact(contact);
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id) {
        contactService.deleteContact(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, @Valid Contact contact) {
        contact.setId(id);
        contactService.updateContact(contact);
        return Response.ok(contact).build();
    }

}
