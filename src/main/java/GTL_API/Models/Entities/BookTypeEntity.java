package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "BookType", schema = "Book")
public class BookTypeEntity {
    private Integer id;
    private String bookType;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "book_type", nullable = false, length = 30)
    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookTypeEntity that = (BookTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return bookType != null ? bookType.equals(that.bookType) : that.bookType == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bookType != null ? bookType.hashCode() : 0);
        return result;
    }
}
