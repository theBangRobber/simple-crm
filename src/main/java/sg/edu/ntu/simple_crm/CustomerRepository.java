package sg.edu.ntu.simple_crm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  // Structure: [Operation]By[FieldName][Condition]
  // find By FirstName

  // Custom query to find all customers with a specific first name
  // List<Customer> findByFirstName(String firstName);
  List<Customer> findByFirstNameIgnoreCase(String firstName);

  // find By Interactions IsEmpty
  // Find all customers with no interactions
  List<Customer> findByInteractionsIsEmpty();

  // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
}
