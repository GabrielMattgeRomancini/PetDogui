package repository;

import models.Raca;
import infra.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RacaDAOImpl implements RacaDAO {

    private static final String INSERT_RACA_SQL = "INSERT INTO raca (ds_raca) VALUES (?);";
    private static final String SELECT_RACA_BY_ID = "SELECT * FROM raca WHERE id = ?;";
    private static final String SELECT_ALL_RACAS = "SELECT * FROM raca;";
    private static final String DELETE_RACA_SQL = "DELETE FROM raca WHERE id = ?;";
    private static final String UPDATE_RACA_SQL = "UPDATE raca SET ds_raca = ? WHERE id = ?;";

    @Override
    public void save(Raca raca) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RACA_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, raca.getNomeRaca());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    raca.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Raca getById(Long id) {
        Raca raca = null;
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RACA_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("ds_raca");
                raca = new Raca(id, nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raca;
    }

    @Override
    public List<Raca> getAll() {
        List<Raca> racas = new ArrayList<>();
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RACAS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("ds_raca");
                racas.add(new Raca(id, nome));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return racas;
    }

    @Override
    public void update(Raca raca) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RACA_SQL)) {
            preparedStatement.setString(1, raca.getNomeRaca());
            preparedStatement.setLong(2, raca.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RACA_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
