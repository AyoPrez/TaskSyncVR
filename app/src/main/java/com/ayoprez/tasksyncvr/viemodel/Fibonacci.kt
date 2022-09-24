package com.ayoprez.tasksyncvr.viemodel

fun fibonacci (n: Int): Int {
    var firstNumber = 0
    var secondNumber = 1
    var result = 0

    for (i in 2..n) {
        result = firstNumber + secondNumber
        firstNumber = secondNumber
        secondNumber = result
    }
    return result
}