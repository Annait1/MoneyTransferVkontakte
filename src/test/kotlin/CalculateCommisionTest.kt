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
        assertEquals(98, result) /*намеренно ломаю для проверки, подмена с 35  на 98*/
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

}
