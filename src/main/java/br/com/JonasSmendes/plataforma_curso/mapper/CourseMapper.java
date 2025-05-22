package br.com.JonasSmendes.plataforma_curso.mapper;

import br.com.JonasSmendes.plataforma_curso.dto.videoDto.CourseDto;
import br.com.JonasSmendes.plataforma_curso.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = Mapper.class)
public interface CourseMapper {

    @Mapping(target= "videos", source = "videos")
    CourseDto toDto (Course course);

    List<CourseDto> listDto (List<Course> courses);
}
