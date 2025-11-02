package andrew_volostnykh.security_analytics.domain.incident.vo;

import lombok.Getter;

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
}