package edu.uniuv.grupo2.tourgemeas.parceiros;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParceiroService {

    private final ParceiroRepository parceiroRepository;

    public List<Parceiros> findAll() {
        return parceiroRepository.findAll();
    }

    public Optional<Parceiros> findById(Long id) {
        return parceiroRepository.findById(id);
    }

    public Parceiros save(Parceiros parceiro) {
        return parceiroRepository.save(parceiro);
    }

    public void deleteById(Long id) {
        parceiroRepository.deleteById(id);
    }
}
