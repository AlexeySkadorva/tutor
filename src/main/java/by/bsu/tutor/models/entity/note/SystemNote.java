package by.bsu.tutor.models.entity.note;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor

@Entity
@Table(name = "notes")
public class SystemNote extends Note {

    @Column(name = "name")
    private String name;

}
