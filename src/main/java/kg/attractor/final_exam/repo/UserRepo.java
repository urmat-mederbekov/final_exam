package kg.attractor.final_exam.repo;

import kg.attractor.final_exam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    User findByEmail(String email);
}
