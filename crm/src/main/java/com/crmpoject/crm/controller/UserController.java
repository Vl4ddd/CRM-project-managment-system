package com.crmpoject.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crmpoject.crm.entities.User.User;
import com.crmpoject.crm.repository.UserRepository;

@Controller
@RequestMapping("/view/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String userListView(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

}
