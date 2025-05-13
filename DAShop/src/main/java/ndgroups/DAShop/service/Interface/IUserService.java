package ndgroups.DAShop.service.Interface;

import ndgroups.DAShop.dto.UserDto;
import ndgroups.DAShop.model.User;
import ndgroups.DAShop.request.CreateUserRequest;
import ndgroups.DAShop.request.UpdateUserRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User getUserById(Integer userId);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Integer userId);
    void deleteUser(Integer userId);
    List<User> getAllUsers();

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();

    User registerUser(CreateUserRequest request);
    Optional<User> findByEmail(String email);
    void saveUserVerificationToken(User user, String verificationToken);
    String validateToken(String theToken);

}
