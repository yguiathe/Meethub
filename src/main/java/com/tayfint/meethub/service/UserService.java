package com.tayfint.meethub.service;

import java.util.List;
import java.util.Set;

import com.tayfint.meethub.model.User;
import com.tayfint.meethub.model.UserRole;

public interface UserService {
	User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    void save (User user);
    
    User createUser(User user, Set<UserRole> userRoles);
    
    User saveUser (User user); 
    
    List<User> findUserList();

    void enableUser (String username);

    void disableUser (String username);
    
    void IncrementTeamsCnt(Long userId);
    
    void decrementTeamsCnt(Long userId);
    
    void IncrementAppCnt(Long userId);
    
    void decrementAppCnt(Long userId);
}
