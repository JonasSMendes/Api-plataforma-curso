package br.com.JonasSmendes.plataforma_curso.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue
    private UUID id;
    private String titulo;
    private String description;
    private String category;
    private LocalDateTime createdAt;

    public Course(){}

    public Course(UUID id, String titulo, String description, String category, LocalDateTime createdAt) {
        this.id = id;
        this.titulo = titulo;
        this.description = description;
        this.category = category;
        this.createdAt = createdAt;
    }
}
