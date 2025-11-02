package andrew_volostnykh.security_analytics.application.incident.ports.outbound;

public interface UpdateElasticOutPort {

	void scheduleUpdate(String incidentId);
}
