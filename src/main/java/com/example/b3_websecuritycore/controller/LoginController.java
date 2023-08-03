package com.example.b3_websecuritycore.controller;

import com.example.b3_websecuritycore.entity.User;
import com.example.b3_websecuritycore.model.UserDto;
import com.example.b3_websecuritycore.service.IUserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String loginForm() {
        return "login/login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "login/registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model
    ) {
        User exitUser = userService.findUserByEmail(userDto.getEmail());
        if (exitUser != null) {
            result.rejectValue("email", null, "User already registered!!");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "login/registration";
        }

        userService.saveUser(userDto);
        return "redirect:/registration?success";
    }
}
