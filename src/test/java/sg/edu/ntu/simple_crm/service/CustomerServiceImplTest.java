package sg.edu.ntu.simple_crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.exception.CustomerNotFoundException;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

  // Mock the customer repository
  @Mock
  private CustomerRepository customerRepository;

  // Inject the mocked customer repository into the customer service
  @InjectMocks
  private CustomerServiceImpl customerService;

  @Test
  public void testCreateCustomer() {

    // 1. SETUP
    // Create a new customer
    Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
        .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

    // Mock the save method of the customer repository
    when((customerRepository.save(customer))).thenReturn(customer);

    // 2. EXECUTE
    // Call the method that we want to test
    Customer savedCustomer = customerService.createCustomer(customer);

    // 3. ASSERT
    // Compare the actual result with the expected result
    assertEquals(customer, savedCustomer, "The saved customer should be the same as the new customer");

    // Also verify that the save method of the customer repository is called once
    verify(customerRepository, times(1)).save(customer);
  }

  @Test
  public void testGetCustomer() {
    // 1. SETUP
    Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@a.com")
        .contactNo("12345678").jobTitle("Agent").yearOfBirth(1975).build();
    Long customerId = 1L;

    when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

    // 2. EXECUTE
    Customer retrievedCustomer = customerService.getCustomer(customerId);

    // 3. ASSERT
    assertEquals(customer, retrievedCustomer);
  }

  @Test
  public void testGetCustomerNotFound() {

    Long customerId = 1L;
    when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

    assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(customerId));
  }
}
