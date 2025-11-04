package andrew_volostnykh.security_analytics.application.incident.commands;

import java.time.Instant;

public record ReportIncidentCommand(
	String reporterId,
	int severity,
	double latitude,
	double longitude,
	String description,
	Instant occurredAt
) {}
