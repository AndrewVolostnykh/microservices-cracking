package andrew_volostnykh.security_analytics.adapters.postgres.incidents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentJpaRepository
	extends JpaRepository<IncidentEntity, String> {

}
