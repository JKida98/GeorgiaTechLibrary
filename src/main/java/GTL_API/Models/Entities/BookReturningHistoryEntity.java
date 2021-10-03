package GTL_API.Models.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "BookReturningHistory", schema = "Information_Basic")
public class BookReturningHistoryEntity {
    private String ssn;
    private Date borrowDate;
    private String isbn;
    private Date estimatedReturnDate;
    private Date returnedDate;
    private Double payment;
    private Boolean status;

    @Id
    @Column(name = "ssn", nullable = false, length = 11)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Basic
    @Column(name = "borrow_date", nullable = false)
    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Basic
    @Column(name = "isbn", nullable = false, length = 13)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
    @Column(name = "returned_date", nullable = true)
    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
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

        BookReturningHistoryEntity that = (BookReturningHistoryEntity) o;

        if (ssn != null ? !ssn.equals(that.ssn) : that.ssn != null) return false;
        if (borrowDate != null ? !borrowDate.equals(that.borrowDate) : that.borrowDate != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (estimatedReturnDate != null ? !estimatedReturnDate.equals(that.estimatedReturnDate) : that.estimatedReturnDate != null)
            return false;
        if (returnedDate != null ? !returnedDate.equals(that.returnedDate) : that.returnedDate != null) return false;
        if (payment != null ? !payment.equals(that.payment) : that.payment != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = ssn != null ? ssn.hashCode() : 0;
        result = 31 * result + (borrowDate != null ? borrowDate.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (estimatedReturnDate != null ? estimatedReturnDate.hashCode() : 0);
        result = 31 * result + (returnedDate != null ? returnedDate.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
