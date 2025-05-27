package br.com.JonasSmendes.plataforma_curso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    private String slug;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("order ASC")
    private List<Video> videos = new ArrayList<>();

    public Course(){}

    public Course( String titulo, String description, String slug) {
        this.titulo = titulo;
        this.description = description;
        this.slug = slug;
    }
}
