package GTL_API.Models.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Card", schema = "Information_Basic")
public class CardEntity {
    private Integer number;
    private Date expirationDate;
    private String picture;
    private Integer libraryEmployeeId;
    private Boolean isDeleted;

    @Id
    @Column(name = "number", nullable = false)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "expiration_date", nullable = false)
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Basic
    @Column(name = "picture", nullable = true, length = 50)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "library_employee_id", nullable = false)
    public Integer getLibraryEmployeeId() {
        return libraryEmployeeId;
    }

    public void setLibraryEmployeeId(Integer libraryEmployeeId) {
        this.libraryEmployeeId = libraryEmployeeId;
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

        CardEntity that = (CardEntity) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
            return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (libraryEmployeeId != null ? !libraryEmployeeId.equals(that.libraryEmployeeId) : that.libraryEmployeeId != null)
            return false;
        return isDeleted != null ? isDeleted.equals(that.isDeleted) : that.isDeleted == null;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (libraryEmployeeId != null ? libraryEmployeeId.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }
}
