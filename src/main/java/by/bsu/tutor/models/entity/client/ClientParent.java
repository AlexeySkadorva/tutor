package by.bsu.tutor.models.entity.client;

import by.bsu.tutor.models.entity.base.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data

@Entity
@Table(name = "client_parent_info")
public class ClientParent extends BaseEntity {

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "secondname")
    private String secondName;

    @Column(name = "phone_number")
    private String phoneNumber;


    public String getFullName() {
        return Stream.of(lastName, firstName, secondName).filter(n -> n != null)
                .collect(Collectors.joining(StringUtils.SPACE));
    }

}
