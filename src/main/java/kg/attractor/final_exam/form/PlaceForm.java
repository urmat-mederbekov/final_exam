package kg.attractor.final_exam.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class PlaceForm {

    @NotBlank(message = "Обязательное поле")
    private String title;

    @NotBlank(message = "Обязательное поле")
    private String description;
}
