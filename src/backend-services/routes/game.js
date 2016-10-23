var User  = require('./users');
var express = require('express');
var game = express();
var uuid = require('node-uuid');

function startNewGame(req,res) {
    var userCount = req.params.userCount;
    if (userCount < 5) {
        res.json(500, {
            'message': 'Error Occurred - You must have least 5 players to start a game',
            'status': 500
        });
    } else {
        var gameUUID = uuid.v4();
        res.json(200, {
            'message': 'Game Created Successfully',
            'gameUUID': gameUUID
        });
    }
}

exports.startNewGame=startNewGame;