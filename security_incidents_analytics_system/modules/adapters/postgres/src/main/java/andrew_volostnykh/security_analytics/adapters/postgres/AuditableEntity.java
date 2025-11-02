package andrew_volostnykh.security_analytics.adapters.postgres;

import andrew_volostnykh.security_analytics.adapters.postgres.listeners.AuditEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditEntityListener.class)
@Getter
@Setter
public class AuditableEntity {

	@Column(name = "created_at", updatable = false)
	protected Instant createdAt;

	@Column(name = "updated_at")
	protected Instant updatedAt;
}
