package by.bsu.tutor.config;

import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static by.bsu.tutor.config.WebSocketConfig.MESSAGE_PREFIX;

/**
 * @author Kadach Alexey
 */
@Component
public class LogoutListener implements HttpSessionListener {

    private static final String SPRING_SECURITY_ATTRIBUTE_KEY = "SPRING_SECURITY_CONTEXT";

    private final UserService userService;
    private final SimpMessagingTemplate simpMessagingTemplate;


    @Autowired
    public LogoutListener(UserService userService, SimpMessagingTemplate simpMessagingTemplate) {
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // Do nothing
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        SecurityContext securityContext = (SecurityContext) httpSessionEvent.getSession()
                .getAttribute(SPRING_SECURITY_ATTRIBUTE_KEY);
        User principal = (User) securityContext.getAuthentication().getPrincipal();
        simpMessagingTemplate.convertAndSend(MESSAGE_PREFIX + "/removeUser", principal);
    }
}
