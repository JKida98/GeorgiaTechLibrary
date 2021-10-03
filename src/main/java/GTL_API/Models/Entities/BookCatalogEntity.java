package GTL_API.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "BookCatalog", schema = "Book")
public class BookCatalogEntity {
    private Integer id;
    private String isbn;
    private Integer bookConditionId;
    private Boolean isDeleted;

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
    @Column(name = "isbn", nullable = false, length = 13)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "book_condition_id", nullable = false)
    public Integer getBookConditionId() {
        return bookConditionId;
    }

    public void setBookConditionId(Integer bookConditionId) {
        this.bookConditionId = bookConditionId;
    }

    @Basic
    @Column(name = "is_deleted", nullable = false)
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCatalogEntity that = (BookCatalogEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (bookConditionId != null ? !bookConditionId.equals(that.bookConditionId) : that.bookConditionId != null)
            return false;
        return isDeleted != null ? isDeleted.equals(that.isDeleted) : that.isDeleted == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (bookConditionId != null ? bookConditionId.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }
}
