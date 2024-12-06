package sg.edu.ntu.simple_crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.entity.Interaction;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;
import sg.edu.ntu.simple_crm.repository.InteractionRepository;
import sg.edu.ntu.simple_crm.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerRepository customerRepository;
  private InteractionRepository interactionRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository, InteractionRepository interactionRepository) {
    this.customerRepository = customerRepository;
    this.interactionRepository = interactionRepository;
  }

  @Override
  public Customer createCustomer(Customer customer) {

    // Customer testCustomer =
    // Customer.builder().firstName("Tony").lastName("Barton").email("clint@a.com")
    // .contactNo("12345678").jobTitle("Agent").yearOfBirth(1975).build();

    return customerRepository.save(customer);
    // customerRepository.save(customer);
    // return testCustomer;
  }

  @Override
  public Customer getCustomer(Long id) {
    return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
  }

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Customer updateCustomer(Long id, Customer customer) {
    Customer customerToUpdate = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    // Update the customer object that was retrieved
    customerToUpdate.setFirstName(customer.getFirstName());
    customerToUpdate.setLastName(customer.getLastName());
    customerToUpdate.setEmail(customer.getEmail());
    customerToUpdate.setContactNo(customer.getContactNo());
    customerToUpdate.setJobTitle(customer.getJobTitle());
    customerToUpdate.setYearOfBirth(customer.getYearOfBirth());
    // Save updated customer back to db
    return customerRepository.save(customerToUpdate);

  }

  @Override
  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

  @Override
  public Interaction addInteractionToCustomer(Long id, Interaction interaction) {
    // Retrieve customer from the db
    Customer selectedCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    // Add customer to the interaction
    interaction.setCustomer(selectedCustomer);
    // Save interaction to db
    return interactionRepository.save(interaction);
  }

  @Override
  public List<Customer> getCustomersByFirstName(String firstName) {
    List<Customer> foundCustomers = customerRepository.findByFirstNameIgnoreCase(firstName);
    return foundCustomers;
  }

  @Override
  public List<Customer> getCustomersWithNoInteraction() {
    return customerRepository.findByInteractionsIsEmpty();
  }

}
