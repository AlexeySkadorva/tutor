package by.bsu.tutor.service.user.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.UserRepository;
import by.bsu.tutor.service.mailer.impl.UserMailMessageSender;
import by.bsu.tutor.service.user.UserService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Service
public class DefaultUserService extends DefaultCrudService<User, UserRepository> implements UserService {

    private final ShaPasswordEncoder passwordEncoder;
    private final UserMailMessageSender userMailMessageSender;


    @Autowired
    public DefaultUserService(@NotNull UserRepository repository, ShaPasswordEncoder passwordEncoder, UserMailMessageSender userMailMessageSender) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.userMailMessageSender = userMailMessageSender;
    }

    @Override
    @NotNull
    public User save(@NotNull User user) {
        if (null == user.getId()) {
            userMailMessageSender.send(user);
        }
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), StringUtils.EMPTY));
        return super.save(user);
    }

    @Override
    @NotNull
    public User findByEmail(@NotNull String email) {
        return repository.findByEmail(email);
    }

    @Transactional
    @Override
    public User addPhotoToUser(@NotNull MultipartFile file, @NotNull Long userId) throws IOException, LogicException {
        User user = super.get(userId);
        user.setEmail(user.getEmail());
        user.setPhoto(file.getBytes());

        return user;
    }

    @Override
    public byte[] getUserPhoto(@NotNull Long id) throws LogicException {
        return super.get(id).getPhoto();
    }

}
