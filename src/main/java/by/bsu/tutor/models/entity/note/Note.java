package by.bsu.tutor.models.entity.note;

import by.bsu.tutor.models.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Note extends BaseEntity {

    @Column(name = "note")
    private String note;

    @Column(name = "created_date")
    private Date createdDate;

}
