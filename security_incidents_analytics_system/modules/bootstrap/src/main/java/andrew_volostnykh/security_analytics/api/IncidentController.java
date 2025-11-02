package andrew_volostnykh.security_analytics.api;

import andrew_volostnykh.security_analytics.application.incident.ports.inbound.ReportIncidentInPort;
import andrew_volostnykh.security_analytics.dto.ReportIncidentRequest;
import andrew_volostnykh.security_analytics.application.incident.commands.ReportIncidentCommand;
import andrew_volostnykh.security_analytics.application.incident.usecases.ReportIncidentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: move to adapters
@RestController
@RequestMapping("/api/v1/incidents")
@RequiredArgsConstructor
public class IncidentController {

	private final ReportIncidentInPort reportIncidentInPort;

	@GetMapping("/{id}")
	public Object getById(
		@PathVariable String id
	) {
		return
			reportIncidentInPort.getById(id);
	}

	@PostMapping("/report")
	public void report(
		@RequestBody ReportIncidentRequest request
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
