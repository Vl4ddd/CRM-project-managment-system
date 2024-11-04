package com.crmpoject.crm.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crmpoject.crm.entities.User.User;
import com.crmpoject.crm.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findByName")
    public List<User> findByName(@RequestParam String name) {
        return userRepository.findByName(name);
    }

    

    @GetMapping("/findByLoginOrEmail")
    public List<User> findByLoginOrEmail(@RequestParam String email, @RequestParam String login) {
        return userRepository.findByLoginOrEmail(login, email);
    }

    
}
