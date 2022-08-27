package com.example.pagcom.util

import android.widget.TextView

fun TextView.toMoney() {
    val text = this.text.toString()
    val newText = text.replace(".", ",")
    this.text = "R$ $newText"
}

fun TextView.toCpf(){

}