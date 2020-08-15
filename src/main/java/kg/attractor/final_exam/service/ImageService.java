package kg.attractor.final_exam.service;

import kg.attractor.final_exam.dto.ImageDTO;
import kg.attractor.final_exam.model.Image;
import kg.attractor.final_exam.repo.ImageRepo;
import kg.attractor.final_exam.repo.PlaceRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ImageService {

    private final ImageRepo imageRepo;
    private final PlaceRepo placeRepo;

    public void addImage(MultipartFile image, Long id){

        String path = "../images/";
        File posterFile = new File(path + image.getOriginalFilename());
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(posterFile);
            outputStream.write(image.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageRepo.save(Image.builder()
                .name(image.getOriginalFilename())
                .place(placeRepo.findById(id).get())
                .build());
    }

    public List<ImageDTO> getAllImagesById(Long id){
        return imageRepo.findAllByPlaceId(id).stream().map(ImageDTO::from).collect(Collectors.toList());
    }

    public Page<ImageDTO> getAllImagesById(Pageable pageable){
        return imageRepo.findAllGroupByPlaceId(pageable).map(ImageDTO::from);
    }

    public Page<ImageDTO> getAll(Pageable pageable){
        return imageRepo.findAll(pageable).map(ImageDTO::from);
    }

    public Page<ImageDTO> search(String  name, Pageable pageable){
        return imageRepo.findAllByPlaceTitleOrPlaceDescription(name, pageable).map(ImageDTO::from);
    }
}
