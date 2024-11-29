package sg.edu.ntu.simple_crm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// CustomerServiceImprovedVersion2.java
// @Primary
@Service
public class CustomerServiceImpl implements CustomerService {

  // private CustomerRepository customerRepository = new CustomerRepository();
  private CustomerRepository customerRepository;
  private InteractionRepository interactionRepository;

  // Constructor Injection
  public CustomerServiceImpl(CustomerRepository customerRepository, InteractionRepository interactionRepository) {
    this.customerRepository = customerRepository;
    this.interactionRepository = interactionRepository;
  }

  @Override
  public Customer createCustomer(Customer customer) {
    Customer newCustomer = customerRepository.save(customer);
    return newCustomer;
  }

  @Override
  public Customer getCustomer(Long id) {
    Customer foundCustomer = customerRepository.findById(id).get();
    return foundCustomer;
  }

  @Override
  public ArrayList<Customer> getAllCustomers() {
    List<Customer> allCustomers = customerRepository.findAll();
    return (ArrayList<Customer>) allCustomers;
  }

  @Override
  public Customer updateCustomer(Long id, Customer customer) {
    // Retrieve the customer from db
    Customer customerToUpdate = customerRepository.findById(id).get();
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
    Customer selectedCustomer = customerRepository.findById(id).get();
    // Add customer to the interaction
    interaction.setCustomer(selectedCustomer);
    // Save interaction to db
    return interactionRepository.save(interaction);
  }

}
