package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "Postcode", schema = "Information_Basic")
public class PostcodeEntity {
    private String postcode;
    private String city;
    private String state;

    @Id
    @Column(name = "postcode", nullable = false, length = 5)
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 52)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostcodeEntity that = (PostcodeEntity) o;

        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        return state != null ? state.equals(that.state) : that.state == null;
    }

    @Override
    public int hashCode() {
        int result = postcode != null ? postcode.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
