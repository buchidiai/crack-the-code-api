/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.service;

import com.aspire.crackthecodeapi.controller.GameResponse;
import com.aspire.crackthecodeapi.controller.RoundResponse;
import com.aspire.crackthecodeapi.models.Game;
import java.util.List;

/**
 *
 * @author louie
 */
public interface ServiceLayer {

    String generateAnswer();

    GameResponse calculatedResult(Game game);

    Game createGame(Game game);

    Game findGameByGameId(int gameId);

    List<Game> getAllGames();

    List<RoundResponse> getAllRoundsByGameId(int id);

}
