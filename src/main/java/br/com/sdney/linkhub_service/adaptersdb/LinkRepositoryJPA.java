package br.com.sdney.linkhub_service.adaptersdb;

import br.com.sdney.linkhub_service.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LinkRepositoryJPA extends JpaRepository<Link, Long> {
    Optional<Link> findByShortCode(String shortCode);
}