package kg.attractor.final_exam.dto;

import kg.attractor.final_exam.model.Review;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDTO {

    private Long id;
    private String description;
    private double rating;
    private UserDTO user;
    private PlaceDTO place;
    private LocalDateTime dateTime;

    public static ReviewDTO from(Review review) {
        return builder()
                .id(review.getId())
                .description(review.getDescription())
                .rating(review.getRating())
                .user(UserDTO.from(review.getUser()))
                .place(PlaceDTO.from(review.getPlace()))
                .dateTime(review.getDateTime())
                .build();
    }
}
