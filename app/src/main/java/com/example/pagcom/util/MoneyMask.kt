package com.example.pagcom.util

import android.widget.TextView

fun TextView.toMoney() {
    val value = this.text.toString()

    val firstNumber = value.substringBefore(".")
    var secondNumber = value.substringAfter(".")
    secondNumber = if (secondNumber.length >= 3)
        secondNumber.substring(0, 3)
    else
        secondNumber.substring(0, 2)
    this.text = "R$ $firstNumber,$secondNumber"
}

fun TextView.toCpf() {

}