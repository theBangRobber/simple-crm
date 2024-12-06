package sg.edu.ntu.simple_crm.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// Activity
// 1: Create Interaction Entity
// 2: Create InteractionRepository interface
// 3: Create InteractionService interface
// 4: Create InteractionServiceImpl
// 5: Create InteractionController

@Getter
@Setter
@Entity
@Table(name = "interaction")
public class Interaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "remarks")
  @Size(min = 3, max = 30, message = "Remarks must be between 3 and 30 characters")
  private String remarks;
  @Column(name = "interaction_date")
  @PastOrPresent(message = "Interaction date cannot be in the future")
  private LocalDate interactionDate;

  // This ignores the customer field
  // @JsonBackReference
  // This ignores the interactions field
  @JsonIgnoreProperties("interactions")
  @ManyToOne(optional = false)
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;

}
