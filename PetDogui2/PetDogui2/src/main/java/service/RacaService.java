package service;

import models.Raca;
import java.util.List;

public interface RacaService {
    void saveRaca(Raca raca);
    Raca getRacaById(Long id);
    List<Raca> getAllRacas();
    void updateRaca(Raca raca);
    void deleteRaca(Long id);
}
