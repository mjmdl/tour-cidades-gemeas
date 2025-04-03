package edu.uniuv.grupo2.tourgemeas.spot;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SpotService {
    private final SpotRepository spotRepository;

    
    public Spot createSpot(Spot spot) {
        return spotRepository.save(spot);
    }

    
    public List<Spot> getAllSpots() {
        return spotRepository.findAll();
    }

   
    public Spot getSpotById(Long id) {
        return spotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ponto turístico não encontrado"));
    }

    public Spot updateSpot(Long id, Spot updatedSpot) {
        Spot existingSpot = getSpotById(id);
        existingSpot.setName(updatedSpot.getName());
        existingSpot.setScore(updatedSpot.getScore());
        return spotRepository.save(existingSpot);
    }

    public void deleteSpot(Long id) {
        spotRepository.deleteById(id);
    }
}
