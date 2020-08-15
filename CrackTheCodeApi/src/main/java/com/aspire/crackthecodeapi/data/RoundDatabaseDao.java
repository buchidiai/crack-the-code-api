/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.data;

import com.aspire.crackthecodeapi.models.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author louie
 */
@Repository
@Profile("database")
public class RoundDatabaseDao implements RoundDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public boolean createRound(int roundNumber, int gameId) {
        final String INSERT_ROUND = "INSERT INTO round (roundNumber, game_gameId) VALUES(?,?)";

        return jdbc.update(INSERT_ROUND,
                roundNumber,
                gameId) > 0;
    }

    @Override
    public int getRoundNumber(int gameId) {

        final String SELECT_FROM_ROUND_TABLE = "SELECT roundNumber FROM round WHERE game_gameId = ?";

        return jdbc.queryForObject(SELECT_FROM_ROUND_TABLE, new Object[]{gameId}, Integer.class);
    }

    @Override
    public List<Round> getAllRoundsByGame(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateRound(Round round, int roundNumber, int gameId) {

        final String UPDATE_ROUND = "UPDATE round SET roundNumber = ?,guessTime = ? , partial = ?, exact = ?   WHERE game_gameId = ?";

        return jdbc.update(UPDATE_ROUND, roundNumber, round.getTime(), round.getPartial(), round.getExact(), gameId) > 0;
    }

    @Override
    public Round getRound(int gameId) {

        final String SELECT_FROM_ROUND_TABLE = "SELECT * FROM round WHERE game_gameId = ?";

        return jdbc.queryForObject(SELECT_FROM_ROUND_TABLE, new RoundMapper(), gameId);

    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setRoundNumber(rs.getInt("roundNumber"));
            round.setTime(rs.getTimestamp("guessTime").toLocalDateTime());
            round.setPartial(rs.getInt("partial"));
            round.setExact(rs.getInt("exact"));
            round.setGameId(rs.getInt("game_gameId"));
            return round;
        }
    }

}