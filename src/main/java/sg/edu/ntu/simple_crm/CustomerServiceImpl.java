package sg.edu.ntu.simple_crm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// import org.springframework.context.annotation.Primary;
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

  public Customer createCustomer(Customer customer) {
    Customer newCustomer = customerRepository.save(customer);
    return newCustomer;
  }

  public Customer getCustomer(Long id) {
    // Customer foundCustomer = customerRepository.findById(id).get();
    // return foundCustomer;
    // Optional<Customer> optionalCustomer = customerRepository.findById(id);

    // if(optionalCustomer.isPresent()) {
    // Customer foundCustomer = optionalCustomer.get();
    // return foundCustomer;
    // }

    // throw new CustomerNotFoundException(id);

    // Shorter method
    // If customer not found, throw the exception
    return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
  }

  public ArrayList<Customer> getAllCustomers() {
    List<Customer> allCustomers = customerRepository.findAll();
    return (ArrayList<Customer>) allCustomers;
  }

  public Customer updateCustomer(Long id, Customer customer) {
    // Retrieve the customer from db
    // Customer customerToUpdate = customerRepository.findById(id).get();
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
