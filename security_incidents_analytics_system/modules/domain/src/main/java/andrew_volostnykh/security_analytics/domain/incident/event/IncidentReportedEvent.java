package andrew_volostnykh.security_analytics.domain.incident.event;

import andrew_volostnykh.security_analytics.domain.incident.vo.Description;
import andrew_volostnykh.security_analytics.domain.incident.vo.IncidentId;
import andrew_volostnykh.security_analytics.domain.incident.vo.IncidentStatus;
import andrew_volostnykh.security_analytics.domain.incident.vo.Location;
import andrew_volostnykh.security_analytics.domain.incident.vo.OccurredAt;
import andrew_volostnykh.security_analytics.domain.incident.vo.ReporterId;
import andrew_volostnykh.security_analytics.domain.incident.vo.Severity;

public record IncidentReportedEvent(
	IncidentId id,
	ReporterId reporterId,
	Severity severity,
	IncidentStatus status,
	Location location,
	Description description,
	OccurredAt occurredAt
) implements IncidentEvent {

	@Override
	public IncidentId getId() {
		return id;
	}
}

