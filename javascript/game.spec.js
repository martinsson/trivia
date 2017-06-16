"use strict"

var Game = require('./game.js');
var gameRunner = require('./game-runner')

var expect = require('chai').expect;
var approvals = require('approvals')
approvals.mocha()
let _ = require('lodash')
function randomInt(maxInt) {
    return Math.round(Math.random() * (maxInt - 1)) + 1;
}
// _.range(200).forEach(i => console.log(randomInt(6)))
// _.range(200).forEach(i => console.log(randomInt(10)))

describe("The test environment", function () {
    it("should pass", function () {

        var loggedLines = []

        var oldLog = console.log
        console.log = function (arg) {
            loggedLines.push(arg);
        }



        _.range(10).forEach((runIndex) => {
            gameRunner(randomInt)

        })

        console.log = oldLog

        this.verifyAsJSON(loggedLines)

    });

});
