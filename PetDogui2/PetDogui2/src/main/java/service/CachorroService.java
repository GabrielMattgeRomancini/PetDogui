package service;

import models.Cachorro;
import java.util.List;

public interface CachorroService {
    void saveCachorro(Cachorro cachorro);
    Cachorro getCachorroById(Long id);
    List<Cachorro> getAllCachorros();
    void updateCachorro(Cachorro cachorro);
    void deleteCachorro(Long id);
}
