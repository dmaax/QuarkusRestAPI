package xyz.dmaax.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import xyz.dmaax.entity.Customer;
import xyz.dmaax.repository.CustomerRepository;

import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll().list();
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void addCustomer(Customer customer) {
        customerRepository.persist(customer);
    }

    @Transactional
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = findCustomerById(id);
        if (existingCustomer == null) {
            throw new IllegalArgumentException("Customer with id " + id + " not found.");
        }

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setAge(updatedCustomer.getAge());
        existingCustomer.setEmail(updatedCustomer.getEmail());

        // The customer object is automatically updated in the database upon transaction commit
        return existingCustomer;
    }

}
