var redis = require('redis');
//this assumes the we are on localhost on port 6379
var client = redis.createClient();

function getRedisClient() {
    return client;
}

function saveNewGame(uuid, playerCount) {
    var connection = getRedisClient();
    connection.hmset(uuid, uuid,{
        'players': playerCount
    });
}

exports.getRedisClient=getRedisClient;
