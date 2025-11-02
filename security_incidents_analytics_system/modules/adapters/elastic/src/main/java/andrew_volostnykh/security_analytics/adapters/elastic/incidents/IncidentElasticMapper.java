package andrew_volostnykh.security_analytics.adapters.elastic.incidents;

import andrew_volostnykh.security_analytics.domain.incident.Incident;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.time.Instant;

public final class IncidentElasticMapper {

	public static IncidentDocument toDocument(Incident incident) {
		var doc = new IncidentDocument();
		doc.setId(incident.getId().value());
		doc.setReporterId(incident.getReporterId().value());
		doc.setSeverity(incident.getSeverity().getId());
		doc.setStatus(incident.getStatus().getId());
		doc.setLocation(
			new GeoPoint(
				incident.getLocation().latitude(),
				incident.getLocation().longitude()
			)
		);
		doc.setOccurredAt(incident.getOccurredAt().value());
		doc.setCreatedAt(Instant.now());
		return doc;
	}
}
