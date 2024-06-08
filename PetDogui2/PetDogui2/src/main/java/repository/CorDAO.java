package repository;

import models.Cor;
import java.util.List;

public interface CorDAO {
    void save(Cor cor);
    Cor getById(Long id);
    List<Cor> getAll();
    void update(Cor cor);
    void delete(Long id);
}
