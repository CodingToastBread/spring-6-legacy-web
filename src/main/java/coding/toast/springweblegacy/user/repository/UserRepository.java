package coding.toast.springweblegacy.user.repository;

import coding.toast.springweblegacy.user.data.table.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends ListCrudRepository<User, Long> {
	
	@Query("""
	select * from users u where u.phone_number like '%' || :phoneNumber || '%'""")
	User findUserWithPhoneNumberFragment(@Param("phoneNumber") String phoneNumber);
	
	// Named Function Also Works!
	//User findByPhoneNumberStartingWith(String phoneNumber);
}
