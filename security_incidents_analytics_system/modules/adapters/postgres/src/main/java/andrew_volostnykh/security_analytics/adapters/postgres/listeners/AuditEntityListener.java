package andrew_volostnykh.security_analytics.adapters.postgres.listeners;

import andrew_volostnykh.security_analytics.adapters.postgres.AuditableEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Instant;

public class AuditEntityListener {

	@PrePersist
	public void beforeInsert(Object entity) {
		if (entity instanceof AuditableEntity auditable) {
			var now = Instant.now();
			auditable.setCreatedAt(now);
			auditable.setUpdatedAt(now);
		}
	}

	@PreUpdate
	public void beforeUpdate(Object entity) {
		if (entity instanceof AuditableEntity auditable) {
			auditable.setUpdatedAt(Instant.now());
		}
	}
}
