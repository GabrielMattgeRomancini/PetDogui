package repository;

import models.Raca;
import java.util.List;

public interface RacaDAO {
    void save(Raca raca);
    Raca getById(Long id);
    List<Raca> getAll();
    void update(Raca raca);
    void delete(Long id);
}
