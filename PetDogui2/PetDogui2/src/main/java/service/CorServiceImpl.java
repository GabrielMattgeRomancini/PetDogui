package service;

import models.Cor;
import repository.CorDAO;
import repository.CorDAOImpl;

import java.util.List;

public class CorServiceImpl implements CorService {

    private final CorDAO corDAO;

    public CorServiceImpl() {
        this.corDAO = new CorDAOImpl();
    }

    @Override
    public void saveCor(Cor cor) {
        corDAO.save(cor);
    }

    @Override
    public Cor getCorById(Long id) {
        return corDAO.getById(id);
    }

    @Override
    public List<Cor> getAllCores() {
        return corDAO.getAll();
    }

    @Override
    public void updateCor(Cor cor) {
        corDAO.update(cor);
    }

    @Override
    public void deleteCor(Long id) {
        corDAO.delete(id);
    }
}
