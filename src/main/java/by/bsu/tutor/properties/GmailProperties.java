package by.bsu.tutor.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "mail.gmail", ignoreUnknownFields = false)
@Component
public class GmailProperties {

    private String portProperties;
    private String portValue;
    private String authProperties;
    private String authValue;
    private String enableProperties;
    private String enableValue;

    private String email;
    private String password;

    private String transport;
    private String host;

}
