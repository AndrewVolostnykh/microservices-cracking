package andrew_volostnykh.security_analytics.domain.incident.vo;

import andrew_volostnykh.security_analytics.domain.incident.exceptions.ValidationException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum IncidentStatus
	implements EnumVo {

	REPORTED(1),
	UPDATED(2),
	CLOSED(3);

	private final int id;

	IncidentStatus(int id) {
		this.id = id;
	}

	public static IncidentStatus getById(int id) {
		return Arrays.stream(IncidentStatus.values())
			.filter(severity -> severity.getId() == id)
			.findFirst()
			.orElseThrow(() -> new ValidationException("Unknown incident status id: " + id));
	}
}