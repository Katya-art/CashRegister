package com.epam.KaterynaKravchenko.controller;

import com.epam.KaterynaKravchenko.domain.User;
import com.epam.KaterynaKravchenko.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        //user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/main";
    }
}
