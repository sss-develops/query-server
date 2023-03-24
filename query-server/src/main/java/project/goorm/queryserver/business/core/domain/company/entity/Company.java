package project.goorm.queryserver.business.core.domain.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column
    private String companyName;

    @Column
    private int newsCount;

    @Column
    private String mainImageUrl;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 company 외부 패키지에서 호출하지 말 것.
     */
    protected Company() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getNewsCount() {
        return newsCount;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return getCompanyId().equals(company.getCompanyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompanyId());
    }

    @Override
    public String toString() {
        return companyName.toString();
    }
}
