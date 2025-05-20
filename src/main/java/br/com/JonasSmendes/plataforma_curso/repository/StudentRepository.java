package br.com.JonasSmendes.plataforma_curso.repository;

import br.com.JonasSmendes.plataforma_curso.model.UserStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<UserStudent, UUID> {
    Optional<UserStudent> findByEmail (String login);
}
