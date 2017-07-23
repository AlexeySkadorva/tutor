package by.bsu.tutor.models.entity.tutor;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.models.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Data

@Entity
@Table(name = "tutors")
public class Tutor extends BaseEntity {

    @Column(name = "education")
    private String education;

    @Column(name = "first_experience")
    private Integer firstExperience;

    @Column(name = "vk_link")
    private String vkLink;

    @Column(name = "skype_link")
    private String skypeLink;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "tutor_id")
    List<TutorNote> notes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tutor_subject_relations", joinColumns = { @JoinColumn(name = "tutor_id") },
            inverseJoinColumns = { @JoinColumn(name = "subject_id") })
    private List<Subject> subjects;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private String status;

    @Transient
    private Integer experienceInYear;


    @PostLoad
    private void calculateFields(){
        LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      //  this.experienceInYear = currentDate.getYear() - this.firstExperience;
    }

}
