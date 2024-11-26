package sg.edu.ntu.simple_crm;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
  private final String id = UUID.randomUUID().toString();
  private String name;
  private String description;
  private String price;
}
