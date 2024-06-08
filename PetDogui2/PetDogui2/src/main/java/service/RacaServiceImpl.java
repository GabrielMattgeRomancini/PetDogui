package service;

import models.Raca;
import repository.RacaDAO;
import repository.RacaDAOImpl;

import java.util.List;

public class RacaServiceImpl implements RacaService {

    private final RacaDAO racaDAO;

    public RacaServiceImpl() {
        this.racaDAO = new RacaDAOImpl();
    }

    @Override
    public void saveRaca(Raca raca) {
        racaDAO.save(raca);
    }

    @Override
    public Raca getRacaById(Long id) {
        return racaDAO.getById(id);
    }

    @Override
    public List<Raca> getAllRacas() {
        return racaDAO.getAll();
    }

    @Override
    public void updateRaca(Raca raca) {
        racaDAO.update(raca);
    }

    @Override
    public void deleteRaca(Long id) {
        racaDAO.delete(id);
    }
}
