package com.concordia.canary.authmodule.util


fun String.containsNumber(): Boolean {
    val regEx = Regex(".*\\d+.*")
    return regEx.matches(this)
}

fun String.containsUpperCase(): Boolean {
    val regEx = Regex(".*[A-Z]+.*")
    return regEx.matches(this)
}

fun String.containsSpecialChar(): Boolean {
    val regEx = Regex(".*[^A-Za-z\\d]+.*")
    return regEx.matches(this)
}