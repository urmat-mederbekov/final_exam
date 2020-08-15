package kg.attractor.final_exam.controller;

import kg.attractor.final_exam.form.PlaceForm;
import kg.attractor.final_exam.form.ReviewForm;
import kg.attractor.final_exam.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@AllArgsConstructor
@RequestMapping("/places")
@Controller
public class PlaceController {

    private final PlaceService placeService;
    private final UserService userService;
    private final ReviewService reviewService;
    private final PropertiesService propertiesService;
    private final ImageService imageService;

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
        model.addAttribute("images", imageService.getAllImagesById(Long.parseLong(id)));
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

    @PostMapping("{id}/images")
    public String addImage(@PathVariable String id,
                            @RequestParam MultipartFile image){

        imageService.addImage(image, Long.parseLong(id));
        return "redirect:/places/{id}";
    }

    @GetMapping("/image/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) {

        String path = "../images";
        MediaType mediaType = name.toLowerCase().contains(".png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
        try {
            InputStream is = new FileInputStream(new File(path) + "/" + name);
            return ResponseEntity
                    .ok()
                    .contentType(mediaType)
                    .body(StreamUtils.copyToByteArray(is));
        } catch (Exception e) {
            InputStream is;
            try {
                is = new FileInputStream(new File(path) + "/" + name);
                return ResponseEntity
                        .ok()
                        .contentType(mediaType)
                        .body(StreamUtils.copyToByteArray(is));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }
}
