package by.bsu.tutor.models.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ContactDto {

    private String name;
    private String email;
    private String message;

}
