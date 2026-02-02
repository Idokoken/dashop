package ndgroups.DAShop.service.impl;

import lombok.RequiredArgsConstructor;
import ndgroups.DAShop.exception.AlreadyExistException;
import ndgroups.DAShop.model.User;
import ndgroups.DAShop.model.VerificationToken;
import ndgroups.DAShop.repository.UserRepository;
import ndgroups.DAShop.repository.VerificationTokenRepository;
import ndgroups.DAShop.request.CreateUserRequest;
import ndgroups.DAShop.service.Interface.IAuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<User> optUser = userRepository.findByEmail(email);
        return optUser.get();

    }


    @Override
    public User registerUser(CreateUserRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if(user.isPresent()){
            throw new AlreadyExistException(
                    "user with email address " + request.getEmail() + " already exist");
        }
        var newUser =  new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());
        return userRepository.save(newUser);
    }


    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }
    @Override
    public String validateToken(String theToken) {
        VerificationToken token  = tokenRepository.findByToken(theToken);
        if(token  == null) {
            return "Invalid verification token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if((token.getTokenExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            tokenRepository.delete(token);
            return "token already expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
}
