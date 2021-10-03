package GTL_API.Models.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "PeopleWhoDidNotReturnedBooksYet", schema = "Information_Basic")
public class PeopleWhoDidNotReturnedBooksYetEntity {
    private String ssn;
    private String firstName;
    private String lastName;
    private Integer cardNumberId;
    private Date borrowDate;
    private String isbn;
    private Date estimatedReturnDate;
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
    @Column(name = "first_name", nullable = false, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "card_number_id", nullable = false)
    public Integer getCardNumberId() {
        return cardNumberId;
    }

    public void setCardNumberId(Integer cardNumberId) {
        this.cardNumberId = cardNumberId;
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

        PeopleWhoDidNotReturnedBooksYetEntity that = (PeopleWhoDidNotReturnedBooksYetEntity) o;

        if (ssn != null ? !ssn.equals(that.ssn) : that.ssn != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (cardNumberId != null ? !cardNumberId.equals(that.cardNumberId) : that.cardNumberId != null) return false;
        if (borrowDate != null ? !borrowDate.equals(that.borrowDate) : that.borrowDate != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (estimatedReturnDate != null ? !estimatedReturnDate.equals(that.estimatedReturnDate) : that.estimatedReturnDate != null)
            return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = ssn != null ? ssn.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (cardNumberId != null ? cardNumberId.hashCode() : 0);
        result = 31 * result + (borrowDate != null ? borrowDate.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (estimatedReturnDate != null ? estimatedReturnDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
