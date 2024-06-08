package repository;

import models.Cachorro;
import java.util.List;

public interface CachorroDAO {
    void save(Cachorro cachorro);
    Cachorro getById(Long id);
    List<Cachorro> getAll();
    void update(Cachorro cachorro);
    void delete(Long id);
}
