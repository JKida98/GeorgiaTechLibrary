package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "BookCondition", schema = "Book")
public class BookConditionEntity {
    private Integer id;
    private String bookCondition;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "book_condition", nullable = false, length = 20)
    public String getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(String bookCondition) {
        this.bookCondition = bookCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookConditionEntity that = (BookConditionEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return bookCondition != null ? bookCondition.equals(that.bookCondition) : that.bookCondition == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bookCondition != null ? bookCondition.hashCode() : 0);
        return result;
    }
}
