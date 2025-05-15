package br.com.JonasSmendes.plataforma_curso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Enrollment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private UserStudent userStudent;

    private LocalDateTime dataEnrollment;

    public Enrollment(){}

    public Enrollment(UUID id, Course course, UserStudent userStudent, LocalDateTime dataEnrollment) {
        this.id = id;
        this.course = course;
        this.userStudent = userStudent;
        this.dataEnrollment = dataEnrollment;
    }
}
