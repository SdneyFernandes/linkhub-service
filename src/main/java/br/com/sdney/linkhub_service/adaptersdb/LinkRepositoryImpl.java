package br.com.sdney.linkhub_service.adaptersdb;

import br.com.sdney.linkhub_service.entities.Link;
import br.com.sdney.linkhub_service.repository.LinkRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LinkRepositoryImpl implements LinkRepositoryPort {

    @Autowired
    private LinkRepositoryJPA jpaRepository;

    @Override
    public Link save(Link link) {
        return jpaRepository.save(link);
    }

    @Override
    public Optional<Link> findByShortCode(String shortCode) {
        return jpaRepository.findByShortCode(shortCode);
    }
}
