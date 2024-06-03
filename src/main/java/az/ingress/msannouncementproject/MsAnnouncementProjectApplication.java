package az.ingress.msannouncementproject;

import az.ingress.msannouncementproject.entity.User;
import az.ingress.msannouncementproject.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Random;

@SpringBootApplication
@EnableCaching
@EnableMethodSecurity
@Slf4j
public class MsAnnouncementProjectApplication implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    public static void main(String[] args) {
        SpringApplication.run(MsAnnouncementProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
