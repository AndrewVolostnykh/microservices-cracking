package andrew_volostnykh.security_analytics.application.incident.ports.outbound;

import andrew_volostnykh.security_analytics.domain.incident.Incident;

import java.util.Optional;

public interface IncidentPersistenceWriteOutPort {

	void save(Incident incident);

	// FIXME: remove
	Optional<Incident> findById(String incidentId);
}
