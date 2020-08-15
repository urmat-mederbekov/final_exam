package kg.attractor.final_exam.dto;

import kg.attractor.final_exam.model.Place;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceDTO {

    private Long id;
    private String title;
    private String description;
    private double rating;


    public static PlaceDTO from(Place place) {
        return builder()
                .id(place.getId())
                .title(place.getTitle())
                .description(place.getDescription())
                .rating(place.getRating())
                .build();
    }
}
