package andrew_volostnykh.security_analytics.adapters.postgres.incidents;

import andrew_volostnykh.security_analytics.adapters.postgres.AuditableEntity;
import andrew_volostnykh.security_analytics.domain.incident.vo.IncidentStatus;
import andrew_volostnykh.security_analytics.domain.incident.vo.Severity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "incidents")
@NoArgsConstructor
@Setter
@Getter
public class IncidentEntity extends AuditableEntity {

	@Id
	private String id;

	private Severity severity;

	private IncidentStatus status;

	private String reporterId;

	private double latitude;
	private double longitude;

	@Column(name = "occurred_at")
	private Instant occurredAt;
}
