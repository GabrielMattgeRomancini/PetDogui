package service;

import models.Cor;
import java.util.List;

public interface CorService {
    void saveCor(Cor cor);
    Cor getCorById(Long id);
    List<Cor> getAllCores();
    void updateCor(Cor cor);
    void deleteCor(Long id);
}
