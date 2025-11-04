package andrew_volostnykh.security_analytics.application.incident.ports.outbound;

import andrew_volostnykh.security_analytics.domain.incident.event.IncidentEvent;

public interface IncidentEventPublisherOutPort {

	void publish(IncidentEvent event);
}
