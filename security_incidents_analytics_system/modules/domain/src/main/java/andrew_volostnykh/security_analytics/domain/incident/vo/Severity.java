package andrew_volostnykh.security_analytics.domain.incident.vo;

import lombok.Getter;

@Getter
public enum Severity
	implements EnumVo {

	LOW(1),
	MEDIUM(2),
	HIGH(3),
	CRITICAL(4);

	private final int id;

	Severity(int id) {
		this.id = id;
	}
}
