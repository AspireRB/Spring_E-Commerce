package com.uan.ecommerce.controller;

import com.uan.ecommerce.model.User;
import com.uan.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;



    @GetMapping("/register")
    public String create() {
        return "user/register";
    }
    @GetMapping("/user")
    public String dirigir() {
        return "user/user";
    }

    @PostMapping("/save")
    public String save(User user) {
        logger.info("User register: {}", user);
        user.setType("USER");
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    @PostMapping("/acceder")
    public String acceder(User user, HttpSession session,  Model model){
        logger.info("accede: {}", user);
        Optional<User> user1 = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        //logger.info("Usuario de db: {}", user1.get());
        if (user1.isPresent()){
            session.setAttribute("idUser", user1.get().getId());
            if (user1.get().getType().equals("ADMIN")){
                return "redirect:/movies";
            }else {
                return "redirect:/";
            }
        }else{
            logger.info("Usuario no existe o contraseña incorrecta");
            model.addAttribute("error", "Usuario no existe o contraseña incorrecta");
        }

        return "user/login";
    }


}
