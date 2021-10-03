package GTL_API.Models.ReturnModels;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class BookBorrowReturnView {
    private String title;
    private String author;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date releasedDate;
    private Integer catalogId;

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

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }
}
