package by.bsu.tutor.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailMessageDto {

    private String email;

    private String subject;

    private String content;

}
