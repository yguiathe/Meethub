package com.tayfint.meethub.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tayfint.meethub.dao.RoleDao;
import com.tayfint.meethub.dao.UserDao;
import com.tayfint.meethub.model.User;
import com.tayfint.meethub.model.UserRole;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}


	public boolean checkUserExists(String username, String email) {
		if (checkUsernameExists(username) || checkEmailExists(username)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkUsernameExists(String username) {
		if (null != findByUsername(username)) {
			return true;
		}

		return false;
	}

	public boolean checkEmailExists(String email) {
		if (null != findByEmail(email)) {
			return true;
		}

		return false;
	}

	public void enableUser(String username) {
		User user = findByUsername(username);
		user.setIsActive(true);
		userDao.save(user);
	}

	public void disableUser(String username) {
		User user = findByUsername(username);
		user.setIsActive(false);
		userDao.save(user);
	}


	@Override
	public void save(User user) {
		userDao.save(user);
	}


	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userDao.findByUsername(user.getUsername());

        if (localUser != null) {
        	logger.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRoles) {
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            localUser = userDao.save(user);
        }

        return localUser;
	}


	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}


	@Override
	public List<User> findUserList() {
		return userDao.findAll();
	}

	public void IncrementTeamsCnt(Long userId) {
		User user = userDao.findById(userId).get();	
		user.setTeamsCnt(user.getTeamsCnt() + 1);
	}
	
	public void decrementTeamsCnt(Long userId) {
		User user = userDao.findById(userId).get();	
		user.setTeamsCnt(user.getTeamsCnt() - 1);
	}
	
	public void IncrementAppCnt(Long userId) {
		User user = userDao.findById(userId).get();	
		user.setApplicationsCnt(user.getApplicationsCnt() + 1);
	}
	
	public void decrementAppCnt(Long userId) {
		User user = userDao.findById(userId).get();	
		user.setApplicationsCnt(user.getApplicationsCnt() - 1);
	}

}
