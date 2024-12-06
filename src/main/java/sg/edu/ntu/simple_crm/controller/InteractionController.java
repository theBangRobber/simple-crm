package sg.edu.ntu.simple_crm.controller;

import java.util.List;

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

import jakarta.validation.Valid;
import sg.edu.ntu.simple_crm.service.InteractionService;
import sg.edu.ntu.simple_crm.entity.Interaction;

@RestController
@RequestMapping("/interactions")
public class InteractionController {
  private InteractionService interactionService;

  public InteractionController(InteractionService interactionService) {
    this.interactionService = interactionService;
  }

  @PostMapping("")
  public ResponseEntity<Interaction> saveInteraction(@Valid @RequestBody Interaction interaction) {
    Interaction newInteraction = interactionService.saveInteraction(interaction);
    return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Interaction> getInteraction(@PathVariable Long id) {
    Interaction foundInteraction = interactionService.getInteraction(id);
    return new ResponseEntity<>(foundInteraction, HttpStatus.OK);
  }

  @GetMapping("")
  public ResponseEntity<List<Interaction>> getAllInteractions() {
    return new ResponseEntity<>(interactionService.getAllInteractions(), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Interaction> updateInteraction(@PathVariable Long id, @RequestBody Interaction interaction) {
    Interaction updatedInteraction = interactionService.updateInteraction(id, interaction);
    return new ResponseEntity<>(updatedInteraction, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteInteraction(@PathVariable Long id) {
    interactionService.deleteInteraction(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
