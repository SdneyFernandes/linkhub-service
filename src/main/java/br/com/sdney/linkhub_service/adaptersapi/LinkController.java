package br.com.sdney.linkhub_service.adaptersapi;

import br.com.sdney.linkhub_service.entities.Link;
import br.com.sdney.linkhub_service.repository.LinkServicePort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class LinkController {

    @Autowired
    private LinkServicePort linkService;

    @PostMapping("/links")
    public ResponseEntity<EncurtarUrlRespons> encurtar(@RequestBody EncurtarUrlRequest request, HttpServletRequest servletRequest) {
        Link linkEncurtado = linkService.encurtarUrl(request.longUrl());

        String baseUrl = servletRequest.getRequestURL().toString().replace(servletRequest.getRequestURI(), "");
        String shortUrl = baseUrl + "/" + linkEncurtado.getShortCode();

        return ResponseEntity.ok(new EncurtarUrlRespons(shortUrl));
    }

    @GetMapping("/{shortCode}")
    public void redirecionar(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        String longUrl = linkService.obterUrlOriginal(shortCode);
        response.sendRedirect(longUrl);
    }
}