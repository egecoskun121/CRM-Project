package egecoskun121.com.crm.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@SqlResultSetMapping(name = "Complaint.complaintResult", classes = {
        @ConstructorResult(targetClass = Complaint.class,
                columns = {@ColumnResult(name = "ID", type = Long.class), @ColumnResult(name = "ANSWER"), @ColumnResult(name = "COMPLAINT_SUBJECT"),
                        @ColumnResult(name = "CREATED_DATE", type = String.class), @ColumnResult(name = "DETAILS"),
                        @ColumnResult(name = "LAST_MODIFIED_DATE", type = String.class), @ColumnResult(name = "PRODUCT_NAME")
                })
})
@NamedNativeQuery(
        name = "Complaint.complaintResult",
        resultClass = Complaint.class,
        query = "SELECT ID,ANSWER,COMPLAINT_SUBJECT,CREATED_DATE,DETAILS,LAST_MODIFIED_DATE, PRODUCT_NAME FROM COMPLAINT  WHERE USER_ID= {SELECT ID FROM USERS WHERE USER_NAME= (:username)}",
        resultSetMapping = "Complaint.complaintResult")
@Data
@NoArgsConstructor
@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    @Size(min = 5, max = 100)
    private String complaintSubject;
    @Size(min = 5, max = 100)
    private String details;
    @Size(min = 5, max = 100)
    private String answer;
    private String productName;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Complaint(Long id, String answer, String complaintSubject, String createdDate, String details, String lastModifiedDate, String productName) {
        this.id = id;
        this.answer = answer;
        this.complaintSubject = complaintSubject;
        this.createdDate = Timestamp.valueOf(createdDate);
        this.details = details;
        this.lastModifiedDate = Timestamp.valueOf(lastModifiedDate);
        this.productName = productName;
    }

}
