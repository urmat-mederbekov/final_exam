package kg.attractor.final_exam.repo;

import kg.attractor.final_exam.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    Page<Review> findAllByPlaceIdOrderByDateTime(Long placeId, Pageable pageable);
}
