package andrew_volostnykh.security_analytics.application.incident.ports.outbound;

public interface UpdateRedisOutPort {

	Long incrementFalseReports(String incidentId);
}
