package com.mkk.ru.extension

import java.util.regex.Pattern

private const val PASSWORD_PATTERN = "^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*\\d)(?=\\S*[^\\w\\s])\\S{8,64}\$"
private const val EMAIL_PATTERN = "^[a-z0-9._-]{1,63}@[a-z0-9.-]+\\.[a-z]{2,5}$"
private const val NAME_CASHIER_PATTERN = "^[A-Za-zА-Яа-я ]+\$"

fun String.isValidPassword(): Boolean =
    Pattern.compile(PASSWORD_PATTERN).matcher(this).matches()

fun String.isValidEmail(): Boolean =
    Pattern.compile(EMAIL_PATTERN).matcher(this).matches()

fun String.isValidFullName(): Boolean =
    Pattern.compile(NAME_CASHIER_PATTERN).matcher(this).matches()
