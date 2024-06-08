package service;

import models.Cachorro;
import repository.CachorroDAO;
import repository.CachorroDAOImpl;

import java.util.List;

public class CachorroServiceImpl implements CachorroService {

    private final CachorroDAO cachorroDAO;

    public CachorroServiceImpl() {
        this.cachorroDAO = new CachorroDAOImpl();
    }

    @Override
    public void saveCachorro(Cachorro cachorro) {
        cachorroDAO.save(cachorro);
    }

    @Override
    public Cachorro getCachorroById(Long id) {
        return cachorroDAO.getById(id);
    }

    @Override
    public List<Cachorro> getAllCachorros() {
        return cachorroDAO.getAll();
    }

    @Override
    public void updateCachorro(Cachorro cachorro) {
        cachorroDAO.update(cachorro);
    }

    @Override
    public void deleteCachorro(Long id) {
        cachorroDAO.delete(id);
    }
}
