package br.com.JonasSmendes.plataforma_curso.infra.security;

import br.com.JonasSmendes.plataforma_curso.model.UserStudent;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken (UserStudent student){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(student.getEmail())
                    .withIssuedAt(this.generateExpirentionDate())
                    .sign(algorithm);

            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException(" Error while authenticating" + exception);
        }
    }

    public String validationToken(String token){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try{
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception){
            return null;
        }

    }

    private Instant generateExpirentionDate (){
      return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3.00"));
    }
}
