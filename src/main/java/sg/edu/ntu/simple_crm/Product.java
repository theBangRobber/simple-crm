package sg.edu.ntu.simple_crm;

import java.util.UUID;

public class Product {
  private final String id;
  private String name;
  private String description;
  private String price;

  public Product() {
    this.id = UUID.randomUUID().toString();
  }

  public Product(String name, String description, String price) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrice() {
    return this.price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

}
