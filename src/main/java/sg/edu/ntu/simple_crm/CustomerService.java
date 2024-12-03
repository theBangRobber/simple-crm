package sg.edu.ntu.simple_crm;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
  Customer createCustomer(Customer customer);

  Customer getCustomer(Long id);

  ArrayList<Customer> getAllCustomers();

  Customer updateCustomer(Long id, Customer customer);

  void deleteCustomer(Long id);

  Interaction addInteractionToCustomer(Long id, Interaction interaction);

  List<Customer> getCustomersByFirstName(String firstName);

  List<Customer> getCustomersWithNoInteraction();

}
