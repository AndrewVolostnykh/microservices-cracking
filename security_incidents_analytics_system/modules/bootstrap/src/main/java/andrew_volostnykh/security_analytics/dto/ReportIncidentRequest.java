package andrew_volostnykh.security_analytics.dto;

import java.time.Instant;

public record ReportIncidentRequest(
	String reporterId,
	int severity,
	double latitude,
	double longitude,
	Instant occurredAt
) {}
