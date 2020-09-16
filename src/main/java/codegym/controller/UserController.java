package codegym.controller;

import codegym.model.Login;
import codegym.model.User;
import codegym.service.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class UserController {

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("home", "login", new Login());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("home", "login", new Login());
        return modelAndView;
    }

//    @PostMapping("/login")
//    public String dologin(Login login, Model model){
//        System.out.println("da vao post");
//        System.out.println(login.getAccount());
//        System.out.println(login.getPassword());
//        model.addAttribute("login",login);
//        return "user";
//    }


    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("login") Login login){
        User user = UserDAO.checkLogin(login);
        if(user == null){
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("user");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
    }

}
