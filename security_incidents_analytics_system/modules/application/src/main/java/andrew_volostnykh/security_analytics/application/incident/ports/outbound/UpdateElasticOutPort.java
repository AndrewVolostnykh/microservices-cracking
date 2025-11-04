package andrew_volostnykh.security_analytics.application.incident.ports.outbound;

import andrew_volostnykh.security_analytics.domain.incident.event.IncidentReportedEvent;

public interface UpdateElasticOutPort {

	void update(
		IncidentReportedEvent event
	);
}
