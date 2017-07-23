package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByCode(String code);

}

