package by.bsu.tutor.service.administration;

import by.bsu.tutor.models.entity.user.User;

import javax.validation.constraints.NotNull;

public interface MessageSenderService {

    void send(@NotNull Object object);

    void sendToUser(@NotNull Object object, @NotNull User user);

}
