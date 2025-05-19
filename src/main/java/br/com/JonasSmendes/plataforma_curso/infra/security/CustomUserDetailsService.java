package br.com.JonasSmendes.plataforma_curso.infra.security;

import br.com.JonasSmendes.plataforma_curso.model.UserStudent;
import br.com.JonasSmendes.plataforma_curso.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserStudent userStudent = this.studentRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return toUserDetails(userStudent);
    }

    private UserDetails toUserDetails (UserStudent student){
        return new org.springframework.security.core.userdetails.User(
                student.getEmail(),
                student.getPassHash(),
                Collections.emptyList()
        );
    }
}
