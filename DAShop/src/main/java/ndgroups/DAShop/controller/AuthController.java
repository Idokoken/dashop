package ndgroups.DAShop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ndgroups.DAShop.events.RegistrationCompleteEvent;
import ndgroups.DAShop.exception.AlreadyExistException;
import ndgroups.DAShop.model.User;
import ndgroups.DAShop.model.VerificationToken;
import ndgroups.DAShop.repository.VerificationTokenRepository;
import ndgroups.DAShop.request.CreateUserRequest;
import ndgroups.DAShop.request.LoginRequest;
import ndgroups.DAShop.response.ApiResponse;
import ndgroups.DAShop.security.CustomUserDetails;
import ndgroups.DAShop.security.jwt.JwtResponse;
import ndgroups.DAShop.security.jwt.JwtUtils;
import ndgroups.DAShop.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/auth")
public class AuthController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository verificationTokenRepository;

    @PostMapping("/login")
    private ResponseEntity<ApiResponse>login(@Valid @RequestBody LoginRequest request){
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateTokenForUser(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            JwtResponse jwtResponse = new JwtResponse(userDetails.getId(), jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("login successful", jwtResponse));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> registerUser(@RequestBody CreateUserRequest registrationRequest,
                                                    final HttpServletRequest request) {
        try {
            User user =  userService.registerUser(registrationRequest);
            //publish an event to the user
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("Please confirm your email to complete your registration",
                            null));
        } catch (AlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken  = verificationTokenRepository.findByToken(token);
        if(theToken.getUser().isEnabled()){
            return "This account has already been verified, Please login.";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login into your account";
        }
        return "invalid verification token";
    }


}
