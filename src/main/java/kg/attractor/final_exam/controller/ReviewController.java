package kg.attractor.final_exam.controller;

import kg.attractor.final_exam.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/reviews")
@Controller
public class ReviewController {

    private final ReviewService reviewService;
}
