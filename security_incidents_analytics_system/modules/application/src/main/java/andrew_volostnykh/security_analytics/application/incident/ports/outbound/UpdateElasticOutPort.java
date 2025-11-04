package andrew_volostnykh.security_analytics.application.incident.ports.outbound;

public interface UpdateElasticOutPort {

	void update(String incidentId, Long falseReportsCount);
}
