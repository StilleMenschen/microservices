package tech.tystnad.oauth2.server.config;

import tech.tystnad.oauth2.server.domain.Role;
import tech.tystnad.oauth2.server.domain.User;
import tech.tystnad.oauth2.server.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class UserConfig {

    @Bean(name = "userInitializeCommandLine")
    public CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_STUDENT"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "John Travolta", "john", "1234", Collections.emptyList()));
            userService.saveUser(new User(null, "Will Smith", "will", "1234", Collections.emptyList()));
            userService.saveUser(new User(null, "Jim Carry", "jim", "1234", Collections.emptyList()));
            userService.saveUser(new User(null, "Arnold Schwarzenegger", "arnold", "1234", Collections.emptyList()));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_STUDENT");
            userService.addRoleToUser("will", "ROLE_MANAGER");
            userService.addRoleToUser("jim", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_USER");
        };
    }
}
