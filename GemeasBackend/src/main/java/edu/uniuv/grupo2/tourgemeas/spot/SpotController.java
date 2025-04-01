package edu.uniuv.grupo2.tourgemeas.spot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class SpotController {
	private final SpotService spotService;

	@PostMapping("/spot")
	public ResponseEntity<CreateSpotDto.Res> create(@RequestBody @Valid CreateSpotDto.Req body)
	{
		return null;
	}
}
