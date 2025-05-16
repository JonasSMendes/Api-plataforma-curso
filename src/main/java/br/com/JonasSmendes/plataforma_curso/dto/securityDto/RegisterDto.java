package br.com.JonasSmendes.plataforma_curso.dto.securityDto;

public record RegisterDto(
        String name,
        String email,
        String passHash
) {
}
