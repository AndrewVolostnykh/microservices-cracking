package andrew_volostnykh.security_analytics.domain.incident.vo;

import java.util.UUID;

public record IncidentId(String value) {

	public static IncidentId newId() {
		return
			new IncidentId(
				UUID.randomUUID().toString()
			);
	}
}