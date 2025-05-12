package br.com.challengecriptovenicio.presentation.extensions

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun Long.toInstantAndFormat(): String {
    val instant = Instant.fromEpochSeconds(this)
    val localDateTime = instant.toLocalDateTime(TimeZone.of("America/Sao_Paulo"))

    val day = "%02d".format(localDateTime.dayOfMonth)
    val month = "%02d".format(localDateTime.monthNumber)
    val year = localDateTime.year
    val hour = "%02d".format(localDateTime.hour)
    val minute = "%02d".format(localDateTime.minute)

    return "$day/$month/$year, $hour:$minute"
}
