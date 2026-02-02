package ndgroups.DAShop.events;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ndgroups.DAShop.model.User;
import ndgroups.DAShop.service.Interface.IAuthService;
import ndgroups.DAShop.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final UserService userService;
    private final JavaMailSender mailSender;
    private User theUser;
    @Autowired
    private IAuthService authService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //1. get the newly registered user
        theUser = event.getUser();
        //2. create a verification token for the user
        String verificationToken  = UUID.randomUUID().toString();
        //3. save the verification token for the user
        authService.saveUserVerificationToken(theUser, verificationToken);
        //4. build the verification url to be sent to the user
        String url  =  event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;
        //5. send the email
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("click the link to complete your registration : {}", url);
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p>Hi, " + theUser.getFirstName() + ", </p>" +
                "<p>Please follow the link to complete your registration </p>" +
                "<a href=\"" + url + "\">verify your email to activate account</a>" +
                "<p>thank you, User Registration Portal Service";
        MimeMessage message  = mailSender.createMimeMessage();
        var messageHelper  = new MimeMessageHelper(message);
        messageHelper.setFrom("idokobryan01@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);

    }
}
