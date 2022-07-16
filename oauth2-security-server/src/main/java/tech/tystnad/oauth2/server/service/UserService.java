package tech.tystnad.oauth2.server.service;

import tech.tystnad.oauth2.server.domain.Role;
import tech.tystnad.oauth2.server.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
