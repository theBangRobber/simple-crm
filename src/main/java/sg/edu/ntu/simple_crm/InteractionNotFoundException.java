package sg.edu.ntu.simple_crm;

public class InteractionNotFoundException extends RuntimeException {
  public InteractionNotFoundException(Long id) {
    super("Could not find interaction: " + id + ".");
  }

}
