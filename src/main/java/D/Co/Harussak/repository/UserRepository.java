package D.Co.Harussak.repository;

import D.Co.Harussak.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
