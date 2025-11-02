package andrew_volostnykh.security_analytics.domain.incident.event;

import andrew_volostnykh.security_analytics.domain.incident.vo.IncidentId;
import andrew_volostnykh.security_analytics.domain.incident.vo.ReporterId;
import andrew_volostnykh.security_analytics.domain.incident.vo.Severity;

public record IncidentReportedEvent(
	IncidentId incidentId,
	ReporterId reporterId,
	Severity severity
) {}

