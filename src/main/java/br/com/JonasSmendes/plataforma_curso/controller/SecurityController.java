package br.com.JonasSmendes.plataforma_curso.controller;

import br.com.JonasSmendes.plataforma_curso.dto.securityDto.LoginDto;
import br.com.JonasSmendes.plataforma_curso.dto.securityDto.RegisterDto;
import br.com.JonasSmendes.plataforma_curso.dto.securityDto.ResponseSecurityDto;
import br.com.JonasSmendes.plataforma_curso.infra.security.TokenService;
import br.com.JonasSmendes.plataforma_curso.model.UserStudent;
import br.com.JonasSmendes.plataforma_curso.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity register (@RequestBody RegisterDto body){

        Optional<UserStudent> student = studentRepository.findByEmail(body.email());

        if (student.isEmpty()){
            UserStudent newUserStudent = new UserStudent();

            newUserStudent.setName(body.name());
            newUserStudent.setEmail(body.email());
            newUserStudent.setPassHash(body.passHash());

            this.studentRepository.save(newUserStudent);

            var token = this.tokenService.generateToken(newUserStudent);
            return ResponseEntity.ok(new ResponseSecurityDto(newUserStudent.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/login")
    public ResponseEntity login (@RequestBody LoginDto body){

        UserStudent userStudent = studentRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (passwordEncoder.matches(body.passHash(), userStudent.getPassHash())){
            var token = tokenService.generateToken(userStudent);
            return ResponseEntity.ok(new ResponseSecurityDto(userStudent.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
