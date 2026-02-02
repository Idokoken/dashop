package ndgroups.DAShop.service.impl;

import lombok.RequiredArgsConstructor;
import ndgroups.DAShop.dto.UserDto;
import ndgroups.DAShop.exception.AlreadyExistException;
import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.User;
import ndgroups.DAShop.model.VerificationToken;
import ndgroups.DAShop.repository.UserRepository;
import ndgroups.DAShop.repository.VerificationTokenRepository;
import ndgroups.DAShop.request.CreateUserRequest;
import ndgroups.DAShop.request.UpdateUserRequest;
import ndgroups.DAShop.service.Interface.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setEmail(request.getEmail());
                    user.setPhoneNumber(request.getPhoneNumber());
                    user.setPassword(passwordEncoder.encode(request.getPassword()));
                    return userRepository.save(user);
                }).orElseThrow(() -> new AlreadyExistException(request.getEmail() + " already exist"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Integer userId) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setFirstName(request.getFistName());
            existingUser.setLastName(request.getLastName());
            existingUser.setPhoneNumber(request.getPhoneNumber());
            return userRepository.save(existingUser);
        }).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Integer userId) {
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserDto convertUserToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
