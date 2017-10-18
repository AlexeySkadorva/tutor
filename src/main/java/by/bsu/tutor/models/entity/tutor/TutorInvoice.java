package by.bsu.tutor.models.entity.tutor;

import by.bsu.tutor.models.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Alexey Skadorva
 */
@Data
@NoArgsConstructor

@Entity
@Table(name = "tutors_invoice")
public class TutorInvoice extends BaseEntity {

    @OneToOne
    @JsonIgnoreProperties("invoice")
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @Column(name = "interest_rate")
    private byte rate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "paid")
    private double paid;


    public TutorInvoice(Tutor tutor) {
        this.tutor = tutor;
    }

}
