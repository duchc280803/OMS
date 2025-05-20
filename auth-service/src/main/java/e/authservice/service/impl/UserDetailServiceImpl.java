package e.authservice.service.impl;

import e.authservice.dto.response.UserCustomDetail;
import e.authservice.entity.User;
import e.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return UserCustomDetail
                    .builder()
                    .user(userOptional.get())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
