package service;

import models.Pelagem;
import java.util.List;

public interface PelagemService {
    void savePelagem(Pelagem pelagem);
    Pelagem getPelagemById(Long id);
    List<Pelagem> getAllPelagens();
    void updatePelagem(Pelagem pelagem);
    void deletePelagem(Long id);
}
