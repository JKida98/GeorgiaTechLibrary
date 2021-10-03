package GTL_API.Models.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "BookReturn", schema = "Loan_Activities")
public class BookReturnEntity {
    private Integer id;
    private Date returnedDate;
    private Date estimatedReturnDate;
    private Double payment;
    private Boolean status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "returned_date", nullable = true)
    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Basic
    @Column(name = "estimated_return_date", nullable = false)
    public Date getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public void setEstimatedReturnDate(Date estimatedReturnDate) {
        this.estimatedReturnDate = estimatedReturnDate;
    }

    @Basic
    @Column(name = "payment", nullable = false, precision = 0)
    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookReturnEntity that = (BookReturnEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (returnedDate != null ? !returnedDate.equals(that.returnedDate) : that.returnedDate != null) return false;
        if (estimatedReturnDate != null ? !estimatedReturnDate.equals(that.estimatedReturnDate) : that.estimatedReturnDate != null)
            return false;
        if (payment != null ? !payment.equals(that.payment) : that.payment != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (returnedDate != null ? returnedDate.hashCode() : 0);
        result = 31 * result + (estimatedReturnDate != null ? estimatedReturnDate.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
