package kg.attractor.final_exam.service;

import kg.attractor.final_exam.dto.PlaceDTO;
import kg.attractor.final_exam.form.PlaceForm;
import kg.attractor.final_exam.model.Place;
import kg.attractor.final_exam.repo.PlaceRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.security.Principal;

@AllArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepo placeRepo;

    public Page<PlaceDTO> getAll(Pageable pageable){
        return placeRepo.findAll(pageable).map(PlaceDTO::from);
    }

    public void addPlace(PlaceForm placeForm){

        placeRepo.save(Place.builder()
                .title(placeForm.getTitle())
                .description(placeForm.getDescription())
                .build());
    }

    public PlaceDTO getPlaceById(Long id){
        return PlaceDTO.from(placeRepo.findById(id).get());
    }

    public void ratePlaceById(Long id, int rating){
        Place place = placeRepo.findById(id).get();

        place.setVotes(place.getVotes()+1);
        place.setRating((rating + place.getRating())/place.getVotes());

        placeRepo.save(place);
    }
}
