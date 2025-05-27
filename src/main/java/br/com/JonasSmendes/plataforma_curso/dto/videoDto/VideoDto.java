package br.com.JonasSmendes.plataforma_curso.dto.videoDto;

public record VideoDto(
        Long id,
        String slug,
        String gumletAssetId,
        String title,
        Integer order,
        String playbackUrl
) {
}
