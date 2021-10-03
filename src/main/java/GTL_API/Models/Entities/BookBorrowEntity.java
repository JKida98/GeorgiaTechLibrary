package GTL_API.Models.Entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "BookBorrow", schema = "Loan_Activities")
public class BookBorrowEntity {
    private Integer id;
    private Integer bookCatalogId;
    private String ssn;
    private Date borrowDate;
    private Integer bookReturnId;

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
    @Column(name = "book_catalog_id", nullable = false)
    public Integer getBookCatalogId() {
        return bookCatalogId;
    }

    public void setBookCatalogId(Integer bookCatalogId) {
        this.bookCatalogId = bookCatalogId;
    }

    @Basic
    @Column(name = "ssn", nullable = false, length = 11)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "borrow_date", nullable = false)
    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Basic
    @Column(name = "book_return_id", nullable = false)
    public Integer getBookReturnId() {
        return bookReturnId;
    }

    public void setBookReturnId(Integer bookReturnId) {
        this.bookReturnId = bookReturnId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookBorrowEntity that = (BookBorrowEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bookCatalogId != null ? !bookCatalogId.equals(that.bookCatalogId) : that.bookCatalogId != null)
            return false;
        if (ssn != null ? !ssn.equals(that.ssn) : that.ssn != null) return false;
        if (borrowDate != null ? !borrowDate.equals(that.borrowDate) : that.borrowDate != null) return false;
        return bookReturnId != null ? bookReturnId.equals(that.bookReturnId) : that.bookReturnId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bookCatalogId != null ? bookCatalogId.hashCode() : 0);
        result = 31 * result + (ssn != null ? ssn.hashCode() : 0);
        result = 31 * result + (borrowDate != null ? borrowDate.hashCode() : 0);
        result = 31 * result + (bookReturnId != null ? bookReturnId.hashCode() : 0);
        return result;
    }
}
