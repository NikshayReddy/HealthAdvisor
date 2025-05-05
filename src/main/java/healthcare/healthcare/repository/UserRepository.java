package healthcare.healthcare.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import healthcare.healthcare.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> 
{
	User findTopByOrderByUseridDesc();

	User findByUsername(String username);

}
