package br.com.JonasSmendes.plataforma_curso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Progress {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private UserStudent userStudent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "video_id")
    private Video video;

    private Boolean completed;

    private int secondWatch;

    private LocalDateTime lastView;

    public Progress(){}

    public Progress(UUID id, UserStudent userStudent, Video video, Boolean completed, int secondWatch, LocalDateTime lastView) {
        this.id = id;
        this.userStudent = userStudent;
        this.video = video;
        this.completed = completed;
        this.secondWatch = secondWatch;
        this.lastView = lastView;
    }
}
