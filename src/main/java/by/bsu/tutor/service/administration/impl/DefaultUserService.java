package by.bsu.tutor.service.administration.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.UserRepository;
import by.bsu.tutor.service.administration.UserService;
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

    private ShaPasswordEncoder passwordEncoder;


    @Autowired
    public DefaultUserService(@NotNull UserRepository repository, ShaPasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(@NotNull User user) {
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), StringUtils.EMPTY));
        return super.save(user);
    }

    @NotNull
    @Override
    public User findByLogin(@NotNull String login) {
        return repository.findByLogin(login);
    }

    @Transactional
    @Override
    public void addPhotoToUser(@NotNull MultipartFile file, @NotNull Long userId) throws IOException, LogicException {
        User user = super.get(userId);
        user.setPhoto(file.getBytes());
    }

    @Override
    public byte[] getUserPhoto(@NotNull Long id) throws LogicException {
        return super.get(id).getPhoto();
    }

}
