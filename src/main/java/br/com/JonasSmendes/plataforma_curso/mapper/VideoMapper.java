package br.com.JonasSmendes.plataforma_curso.mapper;

import br.com.JonasSmendes.plataforma_curso.dto.videoDto.VideoDto;
import br.com.JonasSmendes.plataforma_curso.model.Video;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoDto toDto (Video video);
    List<VideoDto> listaDto (List<Video> videoList);
}
