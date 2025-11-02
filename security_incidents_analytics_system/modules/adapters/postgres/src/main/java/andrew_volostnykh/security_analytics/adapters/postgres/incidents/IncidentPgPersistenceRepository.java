package andrew_volostnykh.security_analytics.adapters.postgres.incidents;

import andrew_volostnykh.security_analytics.application.incident.ports.outbound.IncidentPersistenceWriteOutPort;
import andrew_volostnykh.security_analytics.domain.incident.Incident;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static andrew_volostnykh.security_analytics.adapters.postgres.incidents.IncidentPgMapper.toEntity;

@Repository
@RequiredArgsConstructor
public class IncidentPgPersistenceRepository
	implements IncidentPersistenceWriteOutPort {

	private final IncidentJpaRepository jpaRepository;

	@Override
	public void save(Incident incident) {
		jpaRepository.save(
			toEntity(incident)
		);
	}

	@Override
	public Optional<Incident> findById(String incidentId) {
		return
			jpaRepository.findById(incidentId)
				.map(IncidentPgMapper::toDomain);
	}
}
