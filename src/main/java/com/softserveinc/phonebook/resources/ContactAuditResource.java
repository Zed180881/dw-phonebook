
package com.softserveinc.phonebook.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
import com.softserveinc.phonebook.api.ContactAuditEvent;
import com.softserveinc.phonebook.service.ContactAuditEventService;

@RolesAllowed({ "ADMIN" })
@Path("/contactAudit")
@Produces(MediaType.APPLICATION_JSON)
@Service
@Timed(name = "contactAuditTimer")
@Metered(name = "contactAuditMeter")
@ExceptionMetered(name = "contactAuditExceptionMeter")
public class ContactAuditResource {

    @Autowired
    private ContactAuditEventService contactAuditEventService;

    @GET
    @Path("/getByUsername")
    public Response getContactAuditEventsByFullName(@QueryParam("username") @NotBlank String username) {
        List<ContactAuditEvent> contactAuditEvents = contactAuditEventService.getContactAuditEventsByUsername(username);
        if (contactAuditEvents.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(contactAuditEvents).build();
    }

    @GET
    @Path("/getByContactId")
    public Response getContactAuditEventsByContactId(@QueryParam("contactId") @NotNull int contactId) {
        List<ContactAuditEvent> contactAuditEvents = contactAuditEventService
                .getContactAuditEventsByContactId(contactId);
        if (contactAuditEvents.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(contactAuditEvents).build();
    }

    @GET
    public Response getContactAuditEventList() {
        List<ContactAuditEvent> contactAuditEvents = contactAuditEventService.getAllContactAuditEvents();
        return Response.ok(contactAuditEvents).build();
    }
}
