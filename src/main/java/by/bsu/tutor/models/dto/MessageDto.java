package by.bsu.tutor.models.dto;

import lombok.Data;

/**
 * @author Kadach Alexey
 */
@Data
public class MessageDto {

    private String message;

    private int userFrom;

    private int userTo;

}
