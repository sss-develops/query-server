package project.goorm.queryserver.business.core.domain.company.infrastructure.command;

import org.springframework.data.jpa.repository.JpaRepository;
import project.goorm.queryserver.business.core.domain.company.entity.Company;

public interface CompanyJpaRepository extends JpaRepository<Company, Long> {
}
