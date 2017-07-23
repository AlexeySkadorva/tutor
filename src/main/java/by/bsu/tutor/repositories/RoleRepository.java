package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByCode(String code);

}

