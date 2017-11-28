package by.bsu.tutor.service.user;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.service.base.CrudService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public interface UserService extends CrudService<User> {

    @NotNull User findByEmail(@NotNull String email);

    @NotNull User addPhotoToUser(@NotNull MultipartFile file, @NotNull Long userId) throws IOException, LogicException;

    byte[] getUserPhoto(@NotNull Long id) throws LogicException;

}
