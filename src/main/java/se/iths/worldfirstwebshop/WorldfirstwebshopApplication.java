package se.iths.worldfirstwebshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.worldfirstwebshop.webshop.security.UserCredentials;
import se.iths.worldfirstwebshop.webshop.security.UserCredentialsRepository;

import static se.iths.worldfirstwebshop.webshop.security.Role.ADMIN;


@SpringBootApplication
public class WorldfirstwebshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorldfirstwebshopApplication.class, args);
    }




        @Bean
        CommandLineRunner runOnStartUp(UserCredentialsRepository repo, PasswordEncoder passwordEncoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                var user = repo.findByName("admin");
                if (user == null) {
                    var admin = new UserCredentials();
                    admin.setName("admin");
                    admin.setPassword(passwordEncoder.encode("password"));
                    admin.setRole(ADMIN);
                    repo.save(admin);
                }
            }
        };
    }

}
