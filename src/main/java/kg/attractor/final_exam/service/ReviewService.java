package kg.attractor.final_exam.service;

import kg.attractor.final_exam.dto.ReviewDTO;
import kg.attractor.final_exam.form.ReviewForm;
import kg.attractor.final_exam.model.Review;
import kg.attractor.final_exam.model.User;
import kg.attractor.final_exam.repo.PlaceRepo;
import kg.attractor.final_exam.repo.ReviewRepo;
import kg.attractor.final_exam.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepo reviewRepo;
    private final PlaceRepo placeRepo;
    private final UserRepo userRepo;
    private final PlaceService placeService;

    public Page<ReviewDTO> getAllByPlaceId(Long id, Pageable pageable){
        return reviewRepo.findAllByPlaceIdOrderByDateTime(id, pageable).map(ReviewDTO::from);
    }

    public void addReview(ReviewForm reviewForm, Principal principal){

        User user = userRepo.findByEmail(principal.getName());
        reviewRepo.save(Review.builder()
                .description(reviewForm.getDescription())
                .rating(reviewForm.getRating())
                .user(user)
                .place(placeRepo.findById(reviewForm.getPlaceId()).get())
                .dateTime(LocalDateTime.now())
                .build());

        placeService.ratePlaceById(reviewForm.getPlaceId(), reviewForm.getRating());
    }

    public ReviewDTO getReviewById(Long id){
        return ReviewDTO.from(reviewRepo.findById(id).get());
    }
}
