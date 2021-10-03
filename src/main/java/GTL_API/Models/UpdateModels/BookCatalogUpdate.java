package GTL_API.Models.UpdateModels;

import javax.validation.constraints.NotNull;

public class BookCatalogUpdate {
    @NotNull(message = "ID of a book catalog must be set.")
    private Integer id;
    private String isbn;
    @NotNull(message = "Condition of the book catalog must be set.")
    private Integer bookConditionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getBookConditionId() {
        return bookConditionId;
    }

    public void setBookConditionId(Integer bookConditionId) {
        this.bookConditionId = bookConditionId;
    }
}
