package andrew_volostnykh.security_analytics.adaters.redis;

public class IncidentFalseReportsKey {

	private static final String INCIDENT_FALSE_REPORTS_KEY_PATTERN = "incident:%s:falseReports";

	public static String build(String incidentId) {
		return
			String.format(INCIDENT_FALSE_REPORTS_KEY_PATTERN, incidentId);
	}
}
