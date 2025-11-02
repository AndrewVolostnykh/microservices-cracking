package andrew_volostnykh.security_analytics.domain.incident.vo;

import andrew_volostnykh.security_analytics.domain.incident.exceptions.ValidationException;
import lombok.Getter;

import java.util.Arrays;

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

	public static Severity getById(int id) {
		return Arrays.stream(Severity.values())
			.filter(severity -> severity.getId() == id)
			.findFirst()
			.orElseThrow(() -> new ValidationException("Unknown severity id: " + id));
	}
}
