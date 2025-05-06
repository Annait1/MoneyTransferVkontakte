import org.junit.Assert.*
import org.junit.Test

class CalculateCommissionTest {

    @Test
    fun testVisaNormalCommission() {
        val result = calculateCommission("Visa", 100_000)
        assertEquals(750, result)
    }

    @Test
    fun testVisaMinimumCommission() {
        val result = calculateCommission("Visa", 1000)
        assertEquals(35, result) /*Для второго задания. Код вернула в корректный вид*/
    }

    @Test
    fun testMastercardNoCommission() {
        val result = calculateCommission("Mastercard", 1000, 1000)
        assertEquals(0, result)
    }

    @Test
    fun testMastercardWithCommission() {
        val result = calculateCommission("Mastercard", 1000, 75000)
        assertEquals(26, result)
    }

    @Test
    fun testMaestroWithCommission() {
        val result = calculateCommission("Maestro", 2000, 80000)
        assertEquals(32, result)
    }

    @Test
    fun testVKPayCommission() {
        val result = calculateCommission("VK Pay", 30_000)
        assertEquals(0, result)
    }


    @Test
    fun testMirMinimumCommission() {
        val result = calculateCommission("Мир", 1000)
        assertEquals(35, result)
    }


    @Test
    fun testMaestroNoCommission() {
        val result = calculateCommission("Maestro", 500, 5000)
        assertEquals(0, result)
    }

    @Test
    fun testMastercardAmountJustBelowLimit() {
        val result = calculateCommission("Mastercard", 299, 0)
        assertEquals((299 * 0.006).toInt() + 20, result)
    }

    @Test
    fun testVKPayZeroAmount() {
        val result = calculateCommission("VK Pay", 0)
        assertEquals(0, result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUnknownCardTypeThrows() {
        calculateCommission("UnionPay", 5000)
    }


    @Test
    fun testVisaEdgeCommissionBelowMinimum() {
        val result = calculateCommission("Visa", 4666)
        val rawCommission = (4666 * 0.0075).toInt()
        assertTrue(rawCommission < 35)
        assertEquals(35, result)  /*35 минимум*/
    }

    @Test
    fun testMastercardCrossesMonthlyLimit() {
        val result = calculateCommission("Mastercard", 301, 75000)
        val expected = (301 * 0.006).toInt() + 20
        assertEquals(expected, result)
    }

    @Test
    fun testMastercardOverLimitSecondConditionFalse() {
        val result = calculateCommission("Mastercard", 1000, 75001)
        val expected = (1000 * 0.006).toInt() + 20
        assertEquals(expected, result)
    }

    @Test
    fun testMastercardMonthlyLimitExceededExplicitly() {
        val amount = 5000
        val monthlyTotal = 80000
        val result = calculateCommission("Mastercard", amount, monthlyTotal)
        val expected = (amount * 0.006).toInt() + 20
        assertEquals(expected, result)
    }

    @Test
    fun testMastercardLowAmountAndOverLimit() {
        val result = calculateCommission("Mastercard", 100, 80000)
        val expected = (100 * 0.006).toInt() + 20
        assertEquals(expected, result)
    }

    @Test
    fun testMastercardFalseTruePath() {
        val result = calculateCommission("Mastercard", 100, 500)
        val expected = (100 * 0.006).toInt() + 20
        assertEquals(expected, result)
    }
}

