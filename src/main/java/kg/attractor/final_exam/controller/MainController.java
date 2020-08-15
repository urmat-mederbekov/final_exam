package kg.attractor.final_exam.controller;

import kg.attractor.final_exam.form.UserForm;
import kg.attractor.final_exam.service.ImageService;
import kg.attractor.final_exam.service.PropertiesService;
import kg.attractor.final_exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@AllArgsConstructor
@Controller
public class MainController {

    private final UserService userService;
    private final ImageService imageService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String indexPage(Model model, Principal principal, Pageable pageable, HttpServletRequest uriBuilder){

        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(imageService.getAllImagesById(pageable),
                propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "index";
    }

    @GetMapping("/search")
    public String indexPage(@RequestParam String name, Model model, Principal principal,
                           Pageable pageable, HttpServletRequest uriBuilder){

        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(imageService.search(name, pageable),
                propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());

        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserForm userForm,
                          BindingResult validationResult,
                          RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }

        userService.addUser(userForm);
        return "redirect:/login";
    }

}
