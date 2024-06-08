package repository;

import models.Pelagem;
import infra.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PelagemDAOImpl implements PelagemDAO {

    private static final String INSERT_PELAGEM_SQL = "INSERT INTO pelagem (ds_pelagem) VALUES (?);";
    private static final String SELECT_PELAGEM_BY_ID = "SELECT * FROM pelagem WHERE id = ?;";
    private static final String SELECT_ALL_PELAGENS = "SELECT * FROM pelagem;";
    private static final String DELETE_PELAGEM_SQL = "DELETE FROM pelagem WHERE id = ?;";
    private static final String UPDATE_PELAGEM_SQL = "UPDATE pelagem SET ds_pelagem = ? WHERE id = ?;";

    @Override
    public void save(Pelagem pelagem) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PELAGEM_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pelagem.getTipoPelagem());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pelagem.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pelagem getById(Long id) {
        Pelagem pelagem = null;
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PELAGEM_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String tipo = rs.getString("ds_pelagem");
                pelagem = new Pelagem(id, tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pelagem;
    }

    @Override
    public List<Pelagem> getAll() {
        List<Pelagem> pelagens = new ArrayList<>();
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PELAGENS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String tipo = rs.getString("ds_pelagem");
                pelagens.add(new Pelagem(id, tipo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pelagens;
    }

    @Override
    public void update(Pelagem pelagem) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PELAGEM_SQL)) {
            preparedStatement.setString(1, pelagem.getTipoPelagem());
            preparedStatement.setLong(2, pelagem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PELAGEM_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
