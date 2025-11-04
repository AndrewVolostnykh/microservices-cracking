package andrew_volostnykh.security_analytics.incidents.dto;

import java.time.Instant;

public record ReportIncidentRestRequest(
	String reporterId,
	int severity,
	double latitude,
	double longitude,
	String description,
	Instant occurredAt
) {}
