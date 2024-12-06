package sg.edu.ntu.simple_crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.simple_crm.entity.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {

}
