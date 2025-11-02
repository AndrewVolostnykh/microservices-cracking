package andrew_volostnykh.security_analytics.domain.incident.event;

import java.time.Instant;

public record IncidentReportedEvent(
	String incidentId,
	String reporterId,
	int severity,
	double latitude,
	double longitude,
	Instant occurredAt
) implements Event {

}

