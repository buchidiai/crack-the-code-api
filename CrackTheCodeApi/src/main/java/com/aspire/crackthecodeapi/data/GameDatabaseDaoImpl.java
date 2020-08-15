/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.models.Game;
import com.aspire.crackthecodeapi.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author louie
 */
@Repository
@Profile("testing")
public class GameDatabaseDaoImpl implements GameDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    @Transactional
    public Game createGame(Game game) {

        final String INSERT_GAME = "INSERT INTO game(answer, status) VALUES(?,?)";

        GeneratedKeyHolder newId = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(INSERT_GAME, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setString(2, game.getStatus());
            return statement;
        }, newId);

        game.setGameId(newId.getKey().intValue());

        return game;
    }

    @Override
    public Game findGamebyId(int id) {

        final String GET_GAME_BY_ID = "SELECT gameId,guess,answer,status "
                + "FROM game WHERE gameId = ?;";

        Game foundGame = jdbc.queryForObject(GET_GAME_BY_ID, new GameMapper(), id);

        return foundGame;

    }

    @Override
    public List<Game> getAllGames() {

        final String GET_ALL_GAMES = "SELECT g.gameId, g.guess, g.answer, g.status FROM game g;";

        return jdbc.query(GET_ALL_GAMES, new GameMapper());

    }

    @Override
    public void updateGame(Game game, Round round) {
        final String UPDATE_GAME_TABLE = "UPDATE game SET guess = ?, status = ? WHERE gameId = ?";

        jdbc.update(UPDATE_GAME_TABLE, game.getGuess(), game.getStatus(), game.getGameId());

    }

    @Override
    public void deleteGame(int id) {
        final String DELETE_GAME = "DELETE FROM game "
                + "WHERE gameId = ?";
        jdbc.update(DELETE_GAME, id);
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setGuess(rs.getString("guess"));
            game.setAnswer(rs.getString("answer"));
            game.setStatus(rs.getString("status"));
            return game;
        }
    }

}