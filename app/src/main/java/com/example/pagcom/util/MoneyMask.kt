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

fun maskPhone(phone: String):String{
    val dd = phone.substring(0, 2)
    val firstnumber = phone.substring(2, 7)
    val secondnumber = phone.substring(7, 11)
    return "($dd) $firstnumber-$secondnumber"
}

fun maskCpf(cpf: String):String{
    val first = cpf.substring(0, 3)
    val second = cpf.substring(3, 6)
    val third = cpf.substring(6, 9)
    val verify = cpf.substring(9, 11)
    return "$first.$second.$third-$verify"
}