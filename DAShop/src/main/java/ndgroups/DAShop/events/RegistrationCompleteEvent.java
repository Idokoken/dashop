package ndgroups.DAShop.events;

import lombok.Getter;
import lombok.Setter;
import ndgroups.DAShop.model.User;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    public User user;
    public String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
