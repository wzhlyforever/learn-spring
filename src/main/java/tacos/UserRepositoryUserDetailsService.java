package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.data.UserRepository;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepository userRepo;


    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(s);
        if (user == null) throw new UsernameNotFoundException("User '" + s + "' not found");
        return user;
    }
}
