package GTL_API.Models.CreationModels;

import javax.validation.constraints.NotNull;

public class BookCatalogCreation {
    @NotNull(message = "ISBN must be set while creating book catalog.")
    private String isbn;
    @NotNull(message = "Book catalog of a book catalog must be set.")
    private Integer bookConditionId;

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
