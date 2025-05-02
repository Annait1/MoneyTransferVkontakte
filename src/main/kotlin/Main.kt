fun calculateCommission(cardType: String, amount: Int, monthlyTotal: Int = 0): Int {
    return when (cardType) {
        "Mastercard", "Maestro" -> {
            if (amount > 300 && monthlyTotal + amount <= 75000) {
                0
            } else {
                (0.006 * amount).toInt() + 20
            }
        }

        "Visa", "Мир" -> {
            val commission = (0.0075 * amount).toInt()
            if (commission < 35) 35
            else commission
        }

        "VK Pay" -> 0
        /*Выдаём ошибку, если неизвестная карта*/
        else -> throw IllegalArgumentException("Неизвестный тип карты: $cardType") /*Обернула в исключение*/
        }
    }
