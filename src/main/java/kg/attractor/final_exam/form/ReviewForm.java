package kg.attractor.final_exam.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReviewForm {

    @NotBlank(message = "Обязательное поле")
    private String description;

    @NotNull(message = "Обязательное поле")
    private int rating;

    @NotNull(message = "Обязательное поле")
    private Long placeId;
}
