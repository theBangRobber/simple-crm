package sg.edu.ntu.simple_crm.exception;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(Long id) {
    super("Could not find customer with id: " + id + ".");
  }

}
