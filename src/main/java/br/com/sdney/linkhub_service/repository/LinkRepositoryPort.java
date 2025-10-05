package br.com.sdney.linkhub_service.repository;

import br.com.sdney.linkhub_service.entities.Link;


import java.util.Optional;

public interface LinkRepositoryPort {
    Link save(Link link);
    Optional<Link> findByShortCode(String shortCode);
}
