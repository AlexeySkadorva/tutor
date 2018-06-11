package by.bsu.tutor.models.entity.tutor;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.base.City;
import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.models.entity.order.LessonType;
import by.bsu.tutor.models.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
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

    @Column(name = "duty")
    private Integer duty;

    @Column(name = "telegram")
    private String telegram;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "city_id")
//    private City city;

    //    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "client_tutor_relations", joinColumns = { @JoinColumn(name = "tutor_id", referencedColumnName = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "id", referencedColumnName = "client_tutor_id") })
    @Transient
    private List<TutorNote> notes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tutor_subject_relations", joinColumns = {@JoinColumn(name = "tutor_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")})
    private List<Subject> subjects;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id")
    private List<TutorSubject> tutorSubjects;

    @OneToMany
    @JoinTable(name = "tutors_lesson_types", joinColumns = {@JoinColumn(name = "tutor_id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_type_id")})
    private List<LessonType> lessonTypes;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private String status;

    @Transient
    private Integer experienceInYear;


    @PostLoad
    private void calculateFields() {
        LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (null != this.firstExperience) {
            this.experienceInYear = currentDate.getYear() - this.firstExperience;
        }
    }

    public Tutor() {
        tutorSubjects = new ArrayList<>(1);
        tutorSubjects.add(new TutorSubject());
    }

}
