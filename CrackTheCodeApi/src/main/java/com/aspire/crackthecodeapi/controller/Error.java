/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.controller;

import java.time.LocalDateTime;

/**
 *
 * @author louie
 */
public class Error {

    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private int gameId;

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
