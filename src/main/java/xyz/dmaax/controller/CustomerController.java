package xyz.dmaax.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
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
    public Customer retrieveCustomerById(Long id) {
        return customerService.findCustomerById(id);
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

}
