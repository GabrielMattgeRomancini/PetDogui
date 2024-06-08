package repository;

import models.Cachorro;
import models.Raca;
import models.Cor;
import models.Pelagem;
import infra.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CachorroDAOImpl implements CachorroDAO {

    private static final String INSERT_CACHORRO_SQL = "INSERT INTO cachorro (nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_cor, id_pelagem) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_CACHORRO_BY_ID = "SELECT * FROM cachorro WHERE id = ?;";
    private static final String SELECT_ALL_CACHORROS = "SELECT * FROM cachorro;";
    private static final String DELETE_CACHORRO_SQL = "DELETE FROM cachorro WHERE id = ?;";
    private static final String UPDATE_CACHORRO_SQL = "UPDATE cachorro SET nome = ?, vl_tamanho = ?, st_perfume = ?, dt_nascimento = ?, id_raca = ?, id_cor = ?, id_pelagem = ? WHERE id = ?;";

    @Override
    public void save(Cachorro cachorro) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CACHORRO_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cachorro.getNomeCachorro());
            preparedStatement.setInt(2, cachorro.getTamanho());
            preparedStatement.setBoolean(3, cachorro.isTemPerfume());
            preparedStatement.setDate(4, new java.sql.Date(cachorro.getDataNascimento().getTime()));
            preparedStatement.setLong(5, cachorro.getRaca().getId());
            preparedStatement.setLong(6, cachorro.getCor().getId());
            preparedStatement.setLong(7, cachorro.getPelagem().getId());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cachorro.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cachorro getById(Long id) {
        Cachorro cachorro = null;
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CACHORRO_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nomeCachorro = rs.getString("nome");
                int tamanho = rs.getInt("vl_tamanho");
                boolean temPerfume = rs.getBoolean("st_perfume");
                Date dataNascimento = rs.getDate("dt_nascimento");
                Long idRaca = rs.getLong("id_raca");
                Long idCor = rs.getLong("id_cor");
                Long idPelagem = rs.getLong("id_pelagem");
                Raca raca = new Raca(idRaca, ""); // Supondo que você tenha um DAO para Raca
                Cor cor = new Cor(idCor, ""); // Supondo que você tenha um DAO para Cor
                Pelagem pelagem = new Pelagem(idPelagem, ""); // Supondo que você tenha um DAO para Pelagem
                cachorro = new Cachorro(id, nomeCachorro, tamanho, temPerfume, dataNascimento, raca, cor, pelagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cachorro;
    }

    @Override
    public List<Cachorro> getAll() {
        List<Cachorro> cachorros = new ArrayList<>();
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CACHORROS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nomeCachorro = rs.getString("nome");
                int tamanho = rs.getInt("vl_tamanho");
                boolean temPerfume = rs.getBoolean("st_perfume");
                Date dataNascimento = rs.getDate("dt_nascimento");
                Long idRaca = rs.getLong("id_raca");
                Long idCor = rs.getLong("id_cor");
                Long idPelagem = rs.getLong("id_pelagem");
                Raca raca = new Raca(idRaca, "");
                Cor cor = new Cor(idCor, "");
                Pelagem pelagem = new Pelagem(idPelagem, "");
                Cachorro cachorro = new Cachorro(id, nomeCachorro, tamanho, temPerfume, dataNascimento, raca, cor, pelagem);
                cachorros.add(cachorro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cachorros;
    }

    @Override
    public void update(Cachorro cachorro) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CACHORRO_SQL)) {
            preparedStatement.setString(1, cachorro.getNomeCachorro());
            preparedStatement.setInt(2, cachorro.getTamanho());
            preparedStatement.setBoolean(3, cachorro.isTemPerfume());
            preparedStatement.setDate(4, new java.sql.Date(cachorro.getDataNascimento().getTime()));
            preparedStatement.setLong(5, cachorro.getRaca().getId());
            preparedStatement.setLong(6, cachorro.getCor().getId());
            preparedStatement.setLong(7, cachorro.getPelagem().getId());
            preparedStatement.setLong(8, cachorro.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionSQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CACHORRO_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
