package GTL_API.Models.ReturnModels;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class PeopleWhoDidNotReturnedBooksYetReturn {
    private String ssn;
    private String firstName;
    private String lastName;
    private Integer cardNumberId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date borrowDate;
    private String isbn;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date estimatedReturnDate;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCardNumberId() {
        return cardNumberId;
    }

    public void setCardNumberId(Integer cardNumberId) {
        this.cardNumberId = cardNumberId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public void setEstimatedReturnDate(Date estimatedReturnDate) {
        this.estimatedReturnDate = estimatedReturnDate;
    }
}
