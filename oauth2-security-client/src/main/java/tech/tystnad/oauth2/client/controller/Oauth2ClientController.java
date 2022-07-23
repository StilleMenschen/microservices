package tech.tystnad.oauth2.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class Oauth2ClientController {
    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public ModelAndView index(Principal principal)
    {
        ModelAndView mav=new ModelAndView();
        mav.addObject("username",principal.getName());
        mav.setViewName("index");
        return mav;
    }
}
