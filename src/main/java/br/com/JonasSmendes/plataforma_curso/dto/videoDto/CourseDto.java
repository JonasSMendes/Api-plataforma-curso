package br.com.JonasSmendes.plataforma_curso.dto.videoDto;

import java.util.List;

public record CourseDto(
        Long id,
        String slug,
        String title,
        String description,
        List<VideoDto> videos
) {
}
