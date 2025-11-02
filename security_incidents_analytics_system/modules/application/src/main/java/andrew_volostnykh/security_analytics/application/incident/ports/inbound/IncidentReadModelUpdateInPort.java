package andrew_volostnykh.security_analytics.application.incident.ports.inbound;

import andrew_volostnykh.security_analytics.domain.incident.event.IncidentReportedEvent;

public interface IncidentReadModelUpdateInPort {

	void handle(IncidentReportedEvent event);
}
