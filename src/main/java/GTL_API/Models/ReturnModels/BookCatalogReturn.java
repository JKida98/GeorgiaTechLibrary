package GTL_API.Models.ReturnModels;

public class BookCatalogReturn {
    private Integer id;
    private String isbn;
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
