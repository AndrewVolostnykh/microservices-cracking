package andrew_volostnykh.security_analytics.incidents;

import andrew_volostnykh.security_analytics.application.incident.ports.inbound.ReportIncidentInPort;
import andrew_volostnykh.security_analytics.application.incident.commands.ReportIncidentCommand;
import andrew_volostnykh.security_analytics.domain.incident.Incident;
import andrew_volostnykh.security_analytics.incidents.dto.ReportIncidentRestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/incidents")
@RequiredArgsConstructor
public class IncidentController {

	private final ReportIncidentInPort reportIncidentInPort;

	// FIXME: cannot return aggregate. Need to be mapped to rest response
	@GetMapping("/{id}")
	public Incident getById(
		@PathVariable String id
	) {
		return
			reportIncidentInPort.getById(id);
	}

	@PostMapping("/report")
	public void report(
		@RequestBody ReportIncidentRestRequest request
	) {
		reportIncidentInPort.reportIncident(
			new ReportIncidentCommand(
				request.reporterId(),
				request.severity(),
				request.latitude(),
				request.longitude(),
				request.occurredAt()
			)
		);
	}
}
