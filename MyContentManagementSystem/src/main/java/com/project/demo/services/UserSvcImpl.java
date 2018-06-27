package com.project.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.User;
import com.project.demo.repository.UserRepository;

@Service
public class UserSvcImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
    @Override
    public User register(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
        
        return user;
    }
    
    @Override
    public User registerNewUserAccount(final User accountDto) throws Throwable {
        if (emailExist(accountDto.getEmail())) {
            throw new Exception("There is an account with that email adress: " + accountDto.getEmail());
        }
        final User user = new User();
        user.setId((long) 123);
        user.setUsername("asha");
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	@Override
	public void saveRegisteredUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	} 
	
	private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

}
