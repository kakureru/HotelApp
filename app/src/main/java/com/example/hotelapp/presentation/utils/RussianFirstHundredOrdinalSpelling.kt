package com.example.hotelapp.presentation.utils

class RussianFirstHundredOrdinalSpelling {

    companion object {

        fun getSpelling(number: Int): String {
            if (number !in 0..100) return NUMBER_NOT_SUPPORTED_ERROR
            if (number == 100) return "сотый"
            if (number == 0) return "нулевой"
            if (number in 1..9) return getNumeralSpelling(number)
            if (number in 11..19) return getTeenSpelling(number)
            val numeral = number % 10
            val decimal = number / 10
            if (numeral == 0) return getRoundDecimalSpelling(decimal)
            return "${getDecimalSpelling(decimal)} ${getNumeralSpelling(numeral)}"
        }

        private fun getTeenSpelling(number: Int) = when (number) {
            11 -> "одиннадцатый"
            12 -> "двенадцатый"
            13 -> "тринадцатый"
            14 -> "четырнадцатый"
            15 -> "пятнадцатый"
            16 -> "шестнадцатый"
            17 -> "семнадцатый"
            18 -> "восемнадцатый"
            19 -> "девятнадцатый"
            else -> ""
        }

        private fun getRoundDecimalSpelling(number: Int) = when (number) {
            1 -> "десятый"
            2 -> "двадцатый"
            3 -> "тридцатый"
            4 -> "сороковой"
            5 -> "пятидесятый"
            6 -> "шестидесятый"
            7 -> "семидесятый"
            8 -> "восьмидесятый"
            9 -> "девяностый"
            else -> ""
        }

        private fun getNumeralSpelling(number: Int) = when (number) {
            1 -> "первый"
            2 -> "второй"
            3 -> "третий"
            4 -> "четвёртый"
            5 -> "пятый"
            6 -> "шестой"
            7 -> "седьмой"
            8 -> "восьмой"
            9 -> "девятый"
            else -> ""
        }

        private fun getDecimalSpelling(number: Int) = when (number) {
            2 -> "двадцать"
            3 -> "тридцать"
            4 -> "сорок"
            5 -> "пятьдесят"
            6 -> "шестьдесят"
            7 -> "семьдесят"
            8 -> "восемьдесят"
            9 -> "девяносто"
            else -> ""
        }

        private const val NUMBER_NOT_SUPPORTED_ERROR = "Числа больше 100 не поддерживаются"
    }
}