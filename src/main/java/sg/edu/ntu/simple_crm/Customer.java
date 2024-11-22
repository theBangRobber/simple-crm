package sg.edu.ntu.simple_crm;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  private final String id = UUID.randomUUID().toString();
  private String firstName;
  private String lastName;
  private String email;
  private String contactNo;
  private String jobTitle;
  private int yearOfBirth;

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
