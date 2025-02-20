package ndgroups.DAShop.Data;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ndgroups.DAShop.model.Role;
import ndgroups.DAShop.model.User;
import ndgroups.DAShop.repository.RoleRepository;
import ndgroups.DAShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event){
        Set<String>defaultRoles = Set.of("ROLE_ADMIN", "ROLE_USER");
//        createDefaultUserIfNotExist();
        createDefaultRoleIfNotExists(defaultRoles);
//        createDefaultAdminIfNotExist();
    }

//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        createDefaultUserIfNotExist();
//    }

    private void createDefaultUserIfNotExist() {
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        for (int i = 1; i <= 5; i++) {
           String defaultEmail = "user" + i + "@gmail.com";
           if(userRepository.existsByEmail(defaultEmail)){
               continue;
           }
           User user  = new User();
           user.setFistName("The User");
           user.setLastName("User" + i);
           user.setEmail(defaultEmail);
           user.setPassword(passwordEncoder.encode("123456"));
           user.setRoles(Set.of(userRole));
           userRepository.save(user);
            System.out.println("Default user " + i + " created successfully.");
        }
    }

    private void createDefaultAdminIfNotExist() {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
        for (int i = 1; i <= 2; i++) {
            String defaultEmail = "admin" + i + "@gmail.com";
            if(userRepository.existsByEmail(defaultEmail)){
                continue;
            }
            User user  = new User();
            user.setFistName("Admin");
            user.setLastName("Admin" + i);
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRoles(Set.of(adminRole));
            userRepository.save(user);
            System.out.println("Default Admin user " + i + " created successfully.");
        }
    }


    private void createDefaultRoleIfNotExists(Set<String>roles){
        roles.stream()
                .filter(role -> roleRepository.findByName(role).isEmpty())
                .map(Role::new).forEach(roleRepository::save);
    }
}
