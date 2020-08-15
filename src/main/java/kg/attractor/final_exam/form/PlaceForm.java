package kg.attractor.final_exam.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PlaceForm {

    @NotBlank(message = "Обязательное поле")
    private String title;

    @NotBlank(message = "Обязательное поле")
    private String description;

    private MultipartFile image;
}
