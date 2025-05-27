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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;

    private String slug;

    private String gumletAssetId;

    @Column(name = "order_index")
    private int order;

    public Video(){}

    public Video(String title, int order, String gumletAssetId, String slug) {
        this.slug = slug;
        this.gumletAssetId = gumletAssetId;
        this.title = title;
        this.order = order;
    }
}
