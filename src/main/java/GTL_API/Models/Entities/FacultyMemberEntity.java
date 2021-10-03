package GTL_API.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "FacultyMember", schema = "Information_Sensitive")
public class FacultyMemberEntity {
    private Integer facultyMemberId;
    private Integer facultyMemberTypeId;

    @Id
    @Column(name = "faculty_member_id", nullable = false)
    public Integer getFacultyMemberId() {
        return facultyMemberId;
    }

    public void setFacultyMemberId(Integer facultyMemberId) {
        this.facultyMemberId = facultyMemberId;
    }

    @Basic
    @Column(name = "faculty_member_type_id", nullable = false)
    public Integer getFacultyMemberTypeId() {
        return facultyMemberTypeId;
    }

    public void setFacultyMemberTypeId(Integer facultyMemberTypeId) {
        this.facultyMemberTypeId = facultyMemberTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacultyMemberEntity that = (FacultyMemberEntity) o;

        if (facultyMemberId != null ? !facultyMemberId.equals(that.facultyMemberId) : that.facultyMemberId != null)
            return false;
        return facultyMemberTypeId != null ? facultyMemberTypeId.equals(that.facultyMemberTypeId) : that.facultyMemberTypeId == null;
    }

    @Override
    public int hashCode() {
        int result = facultyMemberId != null ? facultyMemberId.hashCode() : 0;
        result = 31 * result + (facultyMemberTypeId != null ? facultyMemberTypeId.hashCode() : 0);
        return result;
    }
}
