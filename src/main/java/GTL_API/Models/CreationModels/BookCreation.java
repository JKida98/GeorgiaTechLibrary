package GTL_API.Models.CreationModels;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class BookCreation {
    @NotNull(message = "ISBN must be set.")
    private String isbn;
    private String description;
    @NotNull(message = "Title of a book must be set.")
    private String title;
    @NotNull(message = "Author of a book must be set.")
    private String author;
    private String subjectArea;
    @NotNull(message = "Language of a book must be set.")
    private Integer languageId;
    @NotNull(message = "Release date of a book must be set.")
    private Date releasedDate;
    @NotNull(message = "Number of copies must be set.")
    private Integer copiesNumber;
    @NotNull(message = "Cover of a book must be set.")
    private Integer coverId;
    @NotNull(message = "Type of a book must be set.")
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
