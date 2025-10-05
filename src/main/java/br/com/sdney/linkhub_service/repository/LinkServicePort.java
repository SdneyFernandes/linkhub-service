package br.com.sdney.linkhub_service.repository;

import br.com.sdney.linkhub_service.entities.Link;

public interface LinkServicePort {
    Link encurtarUrl(String longUrl);
    String obterUrlOriginal(String shortCode);
}