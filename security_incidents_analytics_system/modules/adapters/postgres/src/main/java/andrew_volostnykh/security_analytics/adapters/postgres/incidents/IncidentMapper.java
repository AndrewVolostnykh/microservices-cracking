package andrew_volostnykh.security_analytics.adapters.postgres.incidents;

import andrew_volostnykh.security_analytics.domain.incident.Incident;
import andrew_volostnykh.security_analytics.domain.incident.vo.IncidentId;
import andrew_volostnykh.security_analytics.domain.incident.vo.Location;
import andrew_volostnykh.security_analytics.domain.incident.vo.OccurredAt;
import andrew_volostnykh.security_analytics.domain.incident.vo.ReporterId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
final class IncidentMapper {

	public static IncidentEntity toEntity(Incident domain) {
		IncidentEntity entity = new IncidentEntity();
		entity.setId(
			domain.getId().value()
		);
		entity.setReporterId(
			domain.getReporterId().value()
		);
		entity.setSeverity(
			domain.getSeverity()
		);
		entity.setStatus(
			domain.getStatus()
		);
		entity.setOccurredAt(
			domain.getOccurredAt().value()
		);
		entity.setLatitude(
			domain.getLocation().latitude()
		);
		entity.setLongitude(
			domain.getLocation().longitude()
		);
		entity.setCreatedAt(
			Instant.now()
		);
		entity.setUpdatedAt(
			Instant.now()
		);
		return entity;
	}

	public static Incident toDomain(IncidentEntity entity) {
		return Incident.rehydrate(
			new IncidentId(
				entity.getId()
			),
			new ReporterId(
				entity.getReporterId()
			),
			entity.getSeverity(),
			entity.getStatus(),
			new Location(
				entity.getLatitude(), entity.getLongitude()
			),
			new OccurredAt(
				entity.getOccurredAt()
			)
		);
	}
}
