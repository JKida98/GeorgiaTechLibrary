package GTL_API.Models.UpdateModels;

import java.sql.Date;

public class BookUpdate {
    private String isbn;
    private String description;
    private String title;
    private String author;
    private String subjectArea;
    private Integer languageId;
    private Date releasedDate;
    private Integer copiesNumber;
    private Integer availableBooksNumber;
    private Integer coverId;
    private Integer bookTypeId;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Integer getCopiesNumber() {
        return copiesNumber;
    }

    public void setCopiesNumber(Integer copiesNumber) {
        this.copiesNumber = copiesNumber;
    }

    public Integer getAvailableBooksNumber() {
        return availableBooksNumber;
    }

    public void setAvailableBooksNumber(Integer availableBooksNumber) {
        this.availableBooksNumber = availableBooksNumber;
    }

    public Integer getCoverId() {
        return coverId;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }

    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }
}
