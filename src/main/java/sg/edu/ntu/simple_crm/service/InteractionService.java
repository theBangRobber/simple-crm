package sg.edu.ntu.simple_crm.service;

import java.util.List;

import sg.edu.ntu.simple_crm.entity.Interaction;

public interface InteractionService {
  Interaction saveInteraction(Interaction interaction);

  Interaction getInteraction(Long id);

  List<Interaction> getAllInteractions();

  Interaction updateInteraction(Long id, Interaction interaction);

  void deleteInteraction(Long id);

}
