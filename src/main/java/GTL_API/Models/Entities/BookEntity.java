package GTL_API.Models.Entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "Book", schema = "Book")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name="findPersonBooksToReturn",
                procedureName = "Loan_Activities.SpecificUserBooksToReturn",
                resultClasses = BookEntity.class,
                parameters =
                @StoredProcedureParameter(
                        mode = ParameterMode.IN,
                        type=Integer.class,
                        name="@cardNumber"
                )
        ),
}
)
public class BookEntity {
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
    private Date addedDate;

    @Id
    @Column(name = "isbn", nullable = false, length = 13)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 5000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "author", nullable = false, length = 60)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "subject_area", nullable = true, length = 2000)
    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }

    @Basic
    @Column(name = "language_id", nullable = false)
    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @Basic
    @Column(name = "released_date", nullable = false)
    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    @Basic
    @Column(name = "copies_number", nullable = false)
    public Integer getCopiesNumber() {
        return copiesNumber;
    }

    public void setCopiesNumber(Integer copiesNumber) {
        this.copiesNumber = copiesNumber;
    }

    @Basic
    @Column(name = "available_books_number", nullable = false)
    public Integer getAvailableBooksNumber() {
        return availableBooksNumber;
    }

    public void setAvailableBooksNumber(Integer availableBooksNumber) {
        this.availableBooksNumber = availableBooksNumber;
    }

    @Basic
    @Column(name = "cover_id", nullable = false)
    public Integer getCoverId() {
        return coverId;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }

    @Basic
    @Column(name = "book_type_id", nullable = false)
    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "added_date", nullable = false)
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (subjectArea != null ? !subjectArea.equals(that.subjectArea) : that.subjectArea != null) return false;
        if (languageId != null ? !languageId.equals(that.languageId) : that.languageId != null) return false;
        if (releasedDate != null ? !releasedDate.equals(that.releasedDate) : that.releasedDate != null) return false;
        if (copiesNumber != null ? !copiesNumber.equals(that.copiesNumber) : that.copiesNumber != null) return false;
        if (availableBooksNumber != null ? !availableBooksNumber.equals(that.availableBooksNumber) : that.availableBooksNumber != null)
            return false;
        if (coverId != null ? !coverId.equals(that.coverId) : that.coverId != null) return false;
        if (bookTypeId != null ? !bookTypeId.equals(that.bookTypeId) : that.bookTypeId != null) return false;
        return addedDate != null ? addedDate.equals(that.addedDate) : that.addedDate == null;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (subjectArea != null ? subjectArea.hashCode() : 0);
        result = 31 * result + (languageId != null ? languageId.hashCode() : 0);
        result = 31 * result + (releasedDate != null ? releasedDate.hashCode() : 0);
        result = 31 * result + (copiesNumber != null ? copiesNumber.hashCode() : 0);
        result = 31 * result + (availableBooksNumber != null ? availableBooksNumber.hashCode() : 0);
        result = 31 * result + (coverId != null ? coverId.hashCode() : 0);
        result = 31 * result + (bookTypeId != null ? bookTypeId.hashCode() : 0);
        result = 31 * result + (addedDate != null ? addedDate.hashCode() : 0);
        return result;
    }
}
