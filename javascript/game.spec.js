"use strict"

var Game = require('./game.js');
var gameRunner = require('./game-runner')

var expect = require('chai').expect;
var approvals = require('approvals')
approvals.mocha()
let _ = require('lodash')

const diceRolls = [1,5,4,3,6,5,5,2,3,5,4,4,5,4,5,5,3,4,1,2,4,2,4,4,5,5,5,4,2,1,1,5,4,1,6,6,2,3,3,4,5,2,3,1,6,4,1,4,2,3,3,1,6,6,2,5,5,5,3,1,3,5,3,2,2,6,4,6,3,4,2,4,2,2,2,6,3,3,2,4,2,5,5,3,3,5,6,6,5,2,3,5,6,4,4,3,3,5,3,2,2,5,5,5,2,6,1,3,3,5,1,4,4,3,3,6,5,6,5,3,2,4,3,4,5,3,4,6,6,4,2,4,3,4,3,1,6,4,5,4,4,1,2,2,4,4,2,1,1,3,4,5,3,5,6,5,5,5,3,2,4,5,1,6,2,2,5,4,1,5,4,4,2,5,5,3,2,3,4,4,4,1,3,5,2,3,3,3,1,3,5,4,4,2,4,5,2,1,5,5]
const winRolls = [1,4,8,5,10,9,7,6,7,6,7,2,8,8,2,1,5,5,3,9,6,9,8,9,5,5,8,2,2,7,10,2,10,7,4,8,5,10,5,7,2,2,4,5,8,6,7,8,9,10,10,5,2,5,1,1,2,7,9,2,5,3,4,8,1,3,10,8,3,8,10,5,4,5,4,6,4,8,5,5,3,7,10,6,9,3,2,1,4,1,8,2,3,6,7,6,6,8,3,4,6,9,7,2,8,4,1,4,9,4,5,4,7,4,3,4,6,4,5,3,3,6,2,6,2,7,8,4,7,3,5,9,3,4,3,3,8,5,5,2,3,9,1,6,10,3,8,9,2,4,2,9,3,3,7,4,9,6,5,5,10,6,6,10,4,7,10,7,7,4,8,5,5,8,3,4,3,3,4,4,3,7,9,7,6,6,7,10,1,10,7,7,2,8,2,5,8,2,6,8]
let diceIndex = -1
function randomInt(maxInt) {
    diceIndex++
    if (maxInt == 6) {
        return diceRolls[diceIndex % 200];
    } else
        return winRolls[diceIndex % 200];

}


// console.log("[" + _.range(200).map(i => randomInt(10)).join([","]) + "]")
// _.range(200).map(i => randomInt(10)).join([","])
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
