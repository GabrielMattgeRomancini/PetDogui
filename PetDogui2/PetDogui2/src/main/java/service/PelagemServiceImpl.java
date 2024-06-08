package service;

import models.Pelagem;
import repository.PelagemDAO;
import repository.PelagemDAOImpl;

import java.util.List;

public class PelagemServiceImpl implements PelagemService {

    private final PelagemDAO pelagemDAO;

    public PelagemServiceImpl() {
        this.pelagemDAO = new PelagemDAOImpl();
    }

    @Override
    public void savePelagem(Pelagem pelagem) {
        pelagemDAO.save(pelagem);
    }

    @Override
    public Pelagem getPelagemById(Long id) {
        return pelagemDAO.getById(id);
    }

    @Override
    public List<Pelagem> getAllPelagens() {
        return pelagemDAO.getAll();
    }

    @Override
    public void updatePelagem(Pelagem pelagem) {
        pelagemDAO.update(pelagem);
    }

    @Override
    public void deletePelagem(Long id) {
        pelagemDAO.delete(id);
    }
}
