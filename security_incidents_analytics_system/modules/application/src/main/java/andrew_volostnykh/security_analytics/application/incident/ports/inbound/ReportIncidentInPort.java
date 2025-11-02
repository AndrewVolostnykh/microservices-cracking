package andrew_volostnykh.security_analytics.application.incident.ports.inbound;

import andrew_volostnykh.security_analytics.application.incident.commands.ReportIncidentCommand;
import andrew_volostnykh.security_analytics.domain.incident.Incident;

public interface ReportIncidentInPort {

	Incident getById(String id);

	void reportIncident(ReportIncidentCommand command);
}
