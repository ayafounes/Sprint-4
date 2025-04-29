package com.aya.evenements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aya.evenements.entities.Evenement;
import com.aya.evenements.entities.Role;
import com.aya.evenements.entities.User;
import com.aya.evenements.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class EvenementsApplication implements CommandLineRunner {

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(EvenementsApplication.class, args);
    }
    
    /*@PostConstruct
    void init_users() {
    //ajouter les rôles
    userService.addRole(new Role(null,"ADMIN"));
    userService.addRole(new Role(null,"AGENT"));
    userService.addRole(new Role(null,"USER"));
    //ajouter les users
    userService.saveUser(new User(null,"admin","123",true,null));
    userService.saveUser(new User(null,"aya","123",true,null));
    userService.saveUser(new User(null,"user1","123",true,null));
    //ajouter les rôles aux users
    userService.addRoleToUser("admin", "ADMIN");
    userService.addRoleToUser("aya", "USER");
    userService.addRoleToUser("aya", "AGENT");
    userService.addRoleToUser("user1", "USER");
    }
    */
    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Evenement.class);
        
        //System.out.println("Password Encoded BCRYPT :******************** ");
       // System.out.println(passwordEncoder.encode("123"));

    }
    @Bean
    public ModelMapper modelMapper()
    {
    return new ModelMapper();
    }
}