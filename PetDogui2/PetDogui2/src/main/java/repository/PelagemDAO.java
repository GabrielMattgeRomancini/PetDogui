package repository;

import models.Pelagem;
import java.util.List;

public interface PelagemDAO {
    void save(Pelagem pelagem);
    Pelagem getById(Long id);
    List<Pelagem> getAll();
    void update(Pelagem pelagem);
    void delete(Long id);
}
