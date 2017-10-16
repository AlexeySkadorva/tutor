package by.bsu.tutor.service.administration;

import javax.validation.constraints.NotNull;

public interface MessageSenderService {

    void send(@NotNull Object object);

}
