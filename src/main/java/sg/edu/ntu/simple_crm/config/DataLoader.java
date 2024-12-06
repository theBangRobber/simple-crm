package sg.edu.ntu.simple_crm.config;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;

@Component
public class DataLoader {
  private CustomerRepository customerRepository;

  // @Autowired
  public DataLoader(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @PostConstruct
  public void loadData() {
    customerRepository.deleteAll();

    // Load data
    // customerRepository.save(new Customer("Tony", "Stark"));
    // customerRepository.save(new Customer("Bruce", "Banner"));
    // customerRepository.save(new Customer("Peter", "Parker"));
    // customerRepository.save(new Customer("Stephen", "Strange"));
    // Customer c1 =
    // Customer.builder().firstName("Tony").lastName("Stark").email("tony@a.com").contactNo("12345678").yearOfBirth(1975).build();
    customerRepository.save(Customer.builder().firstName("Tony").lastName("Stark").email("tony@a.com")
        .contactNo("12345678").yearOfBirth(1975).build());
    customerRepository.save(Customer.builder().firstName("Bruce").lastName("Banner").email("bruce@a.com")
        .contactNo("12345677").yearOfBirth(1985).build());
    customerRepository.save(Customer.builder().firstName("Peter").lastName("Parker").email("peter@a.com")
        .contactNo("12345676").yearOfBirth(1995).build());
    customerRepository.save(Customer.builder().firstName("Stephen").lastName("Strange").email("stephen@a.com")
        .contactNo("12345675").yearOfBirth(1975).build());

  }

}
