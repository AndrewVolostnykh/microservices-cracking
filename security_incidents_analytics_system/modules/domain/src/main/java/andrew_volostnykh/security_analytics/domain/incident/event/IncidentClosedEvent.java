package andrew_volostnykh.security_analytics.domain.incident.event;

import andrew_volostnykh.security_analytics.domain.incident.vo.IncidentId;

public record IncidentClosedEvent(
	IncidentId id
) implements Event {

}