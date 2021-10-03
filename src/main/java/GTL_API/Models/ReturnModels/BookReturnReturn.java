package GTL_API.Models.ReturnModels;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class BookReturnReturn {
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date returnedDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date estimatedReturnDate;
    private Double payment;
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Date getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public void setEstimatedReturnDate(Date estimatedReturnDate) {
        this.estimatedReturnDate = estimatedReturnDate;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
