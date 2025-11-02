package andrew_volostnykh.security_analytics.application.incident.ports.outbound;

public interface UpdateRedisOutPort {

	void incrementFalseReports(String incidentId);
}
