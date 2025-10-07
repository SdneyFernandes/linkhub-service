package br.com.sdney.linkhub_service.service;

import br.com.sdney.linkhub_service.entities.Link;
import br.com.sdney.linkhub_service.repository.LinkRepositoryPort;
import br.com.sdney.linkhub_service.repository.LinkServicePort;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

@Service
public class LinkServiceImpl implements LinkServicePort {

    @Autowired
    private LinkRepositoryPort linkRepository;

    private static final Logger log = LoggerFactory.getLogger(LinkServiceImpl.class);

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
    @Cacheable("links")
    public String obterUrlOriginal(String shortCode) {
        log.info("BUSCANDO NO BANCO DE DDADOS pelo código: {}", shortCode);
        return linkRepository.findByShortCode(shortCode)
                .map(Link::getLongUrl)
                .orElseThrow(() -> new RuntimeException("Link não encontrado para o código: " + shortCode));
    }
}