package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "CoverType", schema = "Book")
public class CoverTypeEntity {
    private Integer id;
    private String coverType;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cover_type", nullable = false, length = 30)
    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoverTypeEntity that = (CoverTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return coverType != null ? coverType.equals(that.coverType) : that.coverType == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (coverType != null ? coverType.hashCode() : 0);
        return result;
    }
}
