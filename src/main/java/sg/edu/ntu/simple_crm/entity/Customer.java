package sg.edu.ntu.simple_crm.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "first_name")
  @NotBlank(message = "First name is mandatory")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email")
  @Email(message = "Email should be valid")
  private String email;
  @Column(name = "contact_no")
  @Size(min = 8, max = 8, message = "Contact number must be 8 characters long")
  private String contactNo;
  @Column(name = "job_title")
  private String jobTitle;
  @Min(value = 1940, message = "Year of birth must not be earlier than 1940")
  @Max(value = 2005, message = "Year of birth must not be later than 2005")
  @Column(name = "year_of_birth")
  private int yearOfBirth;

  @JsonIgnoreProperties("customer")
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Interaction> interactions;

}
