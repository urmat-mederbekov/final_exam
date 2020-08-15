package kg.attractor.final_exam.controller;

import kg.attractor.final_exam.form.PlaceForm;
import kg.attractor.final_exam.form.ReviewForm;
import kg.attractor.final_exam.service.PlaceService;
import kg.attractor.final_exam.service.PropertiesService;
import kg.attractor.final_exam.service.ReviewService;
import kg.attractor.final_exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@AllArgsConstructor
@RequestMapping("/places")
@Controller
public class PlaceController {

    private final PlaceService placeService;
    private final UserService userService;
    private final ReviewService reviewService;
    private final PropertiesService propertiesService;

    @GetMapping("/place")
    public String addPlace(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        return "add-place";
    }

    @PostMapping
    public String addPlace(@Valid PlaceForm placeForm,
                           BindingResult validationResult,
                           RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/places/place";
        }

        placeService.addPlace(placeForm);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getPlace(@PathVariable String id, Model model, Principal principal,
                           Pageable pageable, HttpServletRequest uriBuilder){

        userService.checkUserPresence(model, principal);
        model.addAttribute("place", placeService.getPlaceById(Long.parseLong(id)));
        PropertiesService.constructPageable( reviewService.getAllByPlaceId(Long.parseLong(id), pageable),
                propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());

        return "place";
    }

    @PostMapping("{id}/reviews")
    public String addReview(@PathVariable String id,
                            @Valid ReviewForm reviewForm,
                            BindingResult validationResult,
                            RedirectAttributes attributes,
                            Principal principal){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/places/{id}";
        }

        reviewService.addReview(reviewForm, principal);
        return "redirect:/places/{id}";
    }
}
