package xyz.dmaax.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import xyz.dmaax.entity.Customer;
import xyz.dmaax.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Path("/clientes")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    public List<Customer> retrieveCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
            customers = customerService.findAllCustomers();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return customers;
    }

    @GET
    @Path("/{id}")
    public Response retrieveCustomerById(Long id) {
        Customer customer = customerService.findCustomerById(id);
        if(customer != null) {
            return Response.ok(customer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Customer with id " + id + " not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @POST
    @Transactional
    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return Response.ok(updatedCustomer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerService.deleteCustomer(id);
        return Response.noContent().build();
    }

}
