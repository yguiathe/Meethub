package com.tayfint.meethub.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.Membership;
import com.tayfint.meethub.model.User;

public interface MembershipDao extends CrudRepository<Membership,Long> {
	
	List<Membership> findByUser(User user);
	
	Membership findByUserAndMeeting(User user, Meeting meeting);
	
	void deleteById(Long membershipId);
	
	@Query("SELECT m FROM Membership m JOIN FETCH m.accounts WHERE m.id = (:id)")
    Membership findByIdAndFetchAccountsEagerly(@Param("id") Long id);

	Optional<Membership> findById(Long membershipId);

}
