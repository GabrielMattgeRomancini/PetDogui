package repository;

import models.Cor;
import infra.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CorDAOImpl implements CorDAO {

    private static final String INSERT_COR_SQL = "INSERT INTO cor (ds_cor) VALUES (?);";
    private static final String SELECT_COR_BY_ID = "SELECT * FROM cor WHERE id = ?;";
    private static final String SELECT_ALL_CORES = "SELECT * FROM cor;";
    private static final String DELETE_COR_SQL = "DELETE FROM cor WHERE id = ?;";
    private static final String UPDATE_COR_SQL = "UPDATE cor SET ds_cor = ? WHERE id = ?;";

    @Override
    public void save(Cor cor) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COR_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cor.getNomeCor());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cor.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cor getById(Long id) {
        Cor cor = null;
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COR_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("ds_cor");
                cor = new Cor(id, nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cor;
    }

    @Override
    public List<Cor> getAll() {
        List<Cor> cores = new ArrayList<>();
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CORES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("ds_cor");
                cores.add(new Cor(id, nome));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cores;
    }

    @Override
    public void update(Cor cor) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COR_SQL)) {
            preparedStatement.setString(1, cor.getNomeCor());
            preparedStatement.setLong(2, cor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COR_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
