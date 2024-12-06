package sg.edu.ntu.simple_crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.simple_crm.entity.Interaction;
import sg.edu.ntu.simple_crm.exception.InteractionNotFoundException;
import sg.edu.ntu.simple_crm.repository.InteractionRepository;

@Service
public class InteractionServiceImpl implements InteractionService {

  private InteractionRepository interactionRepository;

  public InteractionServiceImpl(InteractionRepository interactionRepository) {
    this.interactionRepository = interactionRepository;
  }

  @Override
  public Interaction saveInteraction(Interaction interaction) {
    Interaction newInteraction = interactionRepository.save(interaction);
    return newInteraction;
  }

  @Override
  public Interaction getInteraction(Long id) {
    return interactionRepository.findById(id).orElseThrow(() -> new InteractionNotFoundException(id));
  }

  @Override
  public List<Interaction> getAllInteractions() {
    return interactionRepository.findAll();
  }

  @Override
  public Interaction updateInteraction(Long id, Interaction interaction) {
    Interaction interactionToUpdate = interactionRepository.findById(id)
        .orElseThrow(() -> new InteractionNotFoundException(id));
    interactionToUpdate.setRemarks(interaction.getRemarks());
    interactionToUpdate.setInteractionDate(interaction.getInteractionDate());
    return interactionRepository.save(interactionToUpdate);
  }

  @Override
  public void deleteInteraction(Long id) {
    interactionRepository.deleteById(id);
  }

}
