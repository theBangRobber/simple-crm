package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private ArrayList<Product> products = new ArrayList<>();

  public ProductController() {
    products.add(new Product("apple", "This is an apple", "$5"));
    products.add(new Product("book", "This is a book", "$19"));
    products.add(new Product("smartphone", "This is an iPhone", "$999"));
  }

  // Create a product
  @PostMapping("")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    products.add(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  // Read - get all products
  @GetMapping("")
  public ResponseEntity<ArrayList<Product>> getAllProducts() {
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  // Read - get one product
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable String id) {
    try {
      int index = getProductIndex(id);
      return new ResponseEntity<>(products.get(index), HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {

    try {
      int index = getProductIndex(id);
      products.set(index, product);
      Product updatedProduct = products.get(index);
      return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String id) {
    try {
      int index = getProductIndex(id);
      products.remove(index);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Helper
  private int getProductIndex(String id) {
    for (Product product : products) {
      if (product.getId().equals(id)) {
        return products.indexOf(product);
      }
    }
    throw new CustomerNotFoundException(id);
  }
}
