package br.com.JonasSmendes.plataforma_curso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "video_play")
public class Video {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;

    private String url;

    @Column(name = "order_index")
    private int order;

    private int durationSecond;

    public Video(){}

    public Video(UUID id, Course course, String title, String url, int order, int durationSecond) {
        this.id = id;
        this.course = course;
        this.title = title;
        this.url = url;
        this.order = order;
        this.durationSecond = durationSecond;
    }
}
