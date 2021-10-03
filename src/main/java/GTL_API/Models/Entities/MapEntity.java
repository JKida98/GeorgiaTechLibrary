package GTL_API.Models.Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Map", schema = "Book")
public class MapEntity {
    private String barcode;
    private Date addedDate;
    private Boolean isDeleted;

    @Id
    @Column(name = "barcode", nullable = false, length = 12)
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

        MapEntity mapEntity = (MapEntity) o;

        if (barcode != null ? !barcode.equals(mapEntity.barcode) : mapEntity.barcode != null) return false;
        if (addedDate != null ? !addedDate.equals(mapEntity.addedDate) : mapEntity.addedDate != null) return false;
        return isDeleted != null ? isDeleted.equals(mapEntity.isDeleted) : mapEntity.isDeleted == null;
    }

    @Override
    public int hashCode() {
        int result = barcode != null ? barcode.hashCode() : 0;
        result = 31 * result + (addedDate != null ? addedDate.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }
}
