package GTL_API.Models.ReturnModels;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class BookBorrowReturn {
    private Integer id;
    private Integer bookCatalogId;
    private String ssn;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date borrowDate;
    private Integer bookReturnId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookCatalogId() {
        return bookCatalogId;
    }

    public void setBookCatalogId(Integer bookCatalogId) {
        this.bookCatalogId = bookCatalogId;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Integer getBookReturnId() {
        return bookReturnId;
    }

    public void setBookReturnId(Integer bookReturnId) {
        this.bookReturnId = bookReturnId;
    }
}
