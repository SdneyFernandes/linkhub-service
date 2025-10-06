package br.com.sdney.linkhub_service.service;

import br.com.sdney.linkhub_service.entities.Link;
import br.com.sdney.linkhub_service.repository.LinkRepositoryPort;
import br.com.sdney.linkhub_service.repository.LinkServicePort;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkServiceImpl implements LinkServicePort {

    @Autowired
    private LinkRepositoryPort linkRepository;

    @Override
    public Link encurtarUrl(String longUrl) {
        String shortCode = RandomStringUtils.randomAlphanumeric(7);

        Link link = new Link();
        link.setLongUrl(longUrl);
        link.setShortCode(shortCode);
        link.setCreatedAt(LocalDateTime.now());

        return linkRepository.save(link);
    }

    @Override
    public String obterUrlOriginal(String shortCode) {
        return linkRepository.findByShortCode(shortCode)
                .map(Link::getLongUrl)
                .orElseThrow(() -> new RuntimeException("Link não encontrado para o código: " + shortCode));
    }
}