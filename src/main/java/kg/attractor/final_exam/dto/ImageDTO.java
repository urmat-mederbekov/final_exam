package kg.attractor.final_exam.dto;

import kg.attractor.final_exam.model.Image;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageDTO {

    private Long id;
    private String name;
    private PlaceDTO place;

    public static ImageDTO from(Image image) {
        return builder()
                .id(image.getId())
                .name(image.getName())
                .place(PlaceDTO.from(image.getPlace()))
                .build();
    }
}
