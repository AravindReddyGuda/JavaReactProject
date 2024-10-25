package com.sb.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sb.demo.model.User;
import com.sb.demo.repository.UserRepository;

@Controller
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String hello(@AuthenticationPrincipal Object principal, Model model) {
        if (principal != null) {
            String name = null;
            if (principal instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) principal;
                name = oauth2User.getAttribute("name");
            } else if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                User user = userRepository.findByEmail(userDetails.getUsername());
                if (user != null) {
                    name = user.getName();
                }
            }
            model.addAttribute("name", name);
        }
        return "index";
    }

    // Remove the login method from here
    // @GetMapping("/login")
    // public String login() {
    //     return "login";
    // }
}
