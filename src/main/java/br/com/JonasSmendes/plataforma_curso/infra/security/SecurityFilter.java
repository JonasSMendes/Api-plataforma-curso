package br.com.JonasSmendes.plataforma_curso.infra.security;

import br.com.JonasSmendes.plataforma_curso.model.UserStudent;
import br.com.JonasSmendes.plataforma_curso.repository.StudentRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = this.tokenService.validationToken(token);

        if(login != null){
            UserStudent student = studentRepository.findByEmail(login)
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            var authoritie = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authertication = new UsernamePasswordAuthenticationToken(student, null, authoritie);
            SecurityContextHolder.getContext().setAuthentication(authertication);
        }
    }

    private String recoverToken (HttpServletRequest request){
        var outHeader = request.getHeader("Authorization");
        if(outHeader == null) return null;
        return outHeader.replace("Bearer " , "");
    }
}
