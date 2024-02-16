package xyz.dmaax.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import xyz.dmaax.entity.Customer;
import xyz.dmaax.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Path("/customer")
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

    @POST
    @Transactional
    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

}
