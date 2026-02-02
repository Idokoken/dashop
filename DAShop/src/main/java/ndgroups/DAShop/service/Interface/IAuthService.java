package ndgroups.DAShop.service.Interface;

import ndgroups.DAShop.model.User;
import ndgroups.DAShop.request.CreateUserRequest;

public interface IAuthService {
    User registerUser(CreateUserRequest request);
    void saveUserVerificationToken(User user, String verificationToken);
    String validateToken(String theToken);
    User getAuthenticatedUser();
}
