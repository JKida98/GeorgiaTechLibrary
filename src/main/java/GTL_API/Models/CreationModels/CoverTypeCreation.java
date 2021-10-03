package GTL_API.Models.CreationModels;

import javax.validation.constraints.NotNull;

public class CoverTypeCreation {
    @NotNull(message = "Cover type name must be provided.")
    private String coverType;

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }
}
