package br.com.JonasSmendes.plataforma_curso.dto.securityDto;

public record LoginDto(
        String email,
        String passHash
) {
}
