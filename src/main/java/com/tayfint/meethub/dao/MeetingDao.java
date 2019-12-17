package com.tayfint.meethub.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tayfint.meethub.model.Meeting;

public interface MeetingDao extends CrudRepository<Meeting, Long> {

	Optional<Meeting> findById(Long meetingId);

	Meeting findByName(String name);

	void deleteById(Long meetingId);

	List<Meeting> findAll();

	@Query("select mtg from Meeting mtg WHERE mtg.id NOT IN ("
			+ "select mtg.id from Meeting mtg LEFT JOIN Membership mem ON mtg.id = mem.meeting.id where mem.user.id = :userId"
			+ ") and (UPPER(mtg.name) = :name or UPPER(mtg.name) like concat('%', :name, '%')) and mtg.isPublic = '1'")
	List<Meeting> findPublicMeetingByName(@Param("name") String name, @Param("userId") Long userId);
}
