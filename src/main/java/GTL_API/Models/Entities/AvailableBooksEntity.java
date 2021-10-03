package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "AvailableBooks", schema = "Book")
public class AvailableBooksEntity {
    private Integer id;
    private String isbn;
    private Integer bookConditionId;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailableBooksEntity that = (AvailableBooksEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        return bookConditionId != null ? bookConditionId.equals(that.bookConditionId) : that.bookConditionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (bookConditionId != null ? bookConditionId.hashCode() : 0);
        return result;
    }
}
