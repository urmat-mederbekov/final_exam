package kg.attractor.final_exam.repo;

import kg.attractor.final_exam.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepo extends JpaRepository<Image, Long> {

    List<Image> findAllByPlaceId(Long id);

    @Query(value = "SELECT * FROM image i GROUP BY i.place_id", nativeQuery = true)
    Page<Image> findAllGroupByPlaceId(Pageable pageable);

    @Query(value = "SELECT i.* FROM image i, place p Where i.place_id=p.id AND p.title LIKE %?1% OR p.description LIKE %?1% GROUP BY i.place_id", nativeQuery = true)
    Page<Image> findAllByPlaceTitleOrPlaceDescription(String arg, Pageable pageable);
}
