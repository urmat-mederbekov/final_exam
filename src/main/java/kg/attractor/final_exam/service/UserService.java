package kg.attractor.final_exam.service;

import kg.attractor.final_exam.form.UserForm;
import kg.attractor.final_exam.model.User;
import kg.attractor.final_exam.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;


    public void addUser(UserForm userForm){

        userRepo.save(User.builder()
        .password(encoder.encode(userForm.getPassword()))
        .email(userForm.getEmail())
        .name(userForm.getName())
        .build());
    }

    public void checkUserPresence(Model model, Principal principal){
        if(principal != null){
            if (userRepo.existsByEmail(principal.getName())){
                model.addAttribute("user", true);
            }
        }
    }
}
