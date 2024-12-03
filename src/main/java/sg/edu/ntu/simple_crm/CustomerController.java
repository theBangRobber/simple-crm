package sg.edu.ntu.simple_crm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/*
 * Controller (Req/Res) <-> Service (Business Logic) <-> Repository (CRUD datastore)
 */

@RestController
// @RequestMapping("/v1/customers")
@RequestMapping("/customers")
public class CustomerController {

  // private CustomerService customerService = new CustomerService();
  private CustomerService customerService;

  // Constructor Injection
  // @Autowired
  // @Qualifier lets you specifiy the bean name for the injection
  // public CustomerController(@Qualifier("customerServiceImpl") CustomerService
  // customerService) {
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  // Create a customer
  @PostMapping("")
  public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {

    // if (bindingResult.hasErrors()) {
    // System.out.println(bindingResult.getAllErrors());
    // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }
    Customer newCustomer = customerService.createCustomer(customer);
    return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
  }

  // Read - get all customers
  @GetMapping("")
  public ResponseEntity<ArrayList<Customer>> getAllCustomers() {
    ArrayList<Customer> allCustomers = customerService.getAllCustomers();
    return new ResponseEntity<>(allCustomers, HttpStatus.OK);
  }

  // Read - get one customer
  // localhost:8080/customers/35623130-bade-43d6-9bf4-6ea7189301fb
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
    return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
    Customer updatedCustomer = customerService.updateCustomer(id, customer);
    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // Nested route
  @PostMapping("/{id}/interactions")
  public ResponseEntity<Interaction> addInteractionToCustomer(@PathVariable Long id,
      @RequestBody Interaction interaction) {
    Interaction newInteraction = customerService.addInteractionToCustomer(id, interaction);
    return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
  }

  // localhost:8080/customers/search?firstName=Stephen
  @GetMapping("/search")
  public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String firstName) {
    List<Customer> foundCustomers = customerService.getCustomersByFirstName(firstName);
    return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
  }

  // localhost:8080/customers/no-interactions
  @GetMapping("/no-interactions")
  public ResponseEntity<List<Customer>> getCustomersWithNoInteractions() {
    List<Customer> customers = customerService.getCustomersWithNoInteraction();
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }

}
