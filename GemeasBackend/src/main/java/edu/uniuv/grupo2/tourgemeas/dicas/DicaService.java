package edu.uniuv.grupo2.tourgemeas.dicas;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class DicaService {
    private final DicaRepository dicaRepository;

    public List<Dica> listarDicas() {
        return dicaRepository.findAll();
    }

    public Dica salvarDica(Dica dica) {
        return dicaRepository.save(dica);
    }

    public void deletarDica(Long id) {
        dicaRepository.deleteById(id);
    }
}
