package br.com.sdney.linkhub_service.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "links")
@Data
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String shortCode;

   @Column(columnDefinition = "TEXT", nullable = false)
private String longUrl;

    private LocalDateTime createdAt;
}