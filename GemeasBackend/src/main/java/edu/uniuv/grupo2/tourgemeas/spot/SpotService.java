package edu.uniuv.grupo2.tourgemeas.spot;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SpotService {
	private final SpotRepository spotRepository;
}
