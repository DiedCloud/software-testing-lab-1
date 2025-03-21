import org.example.cos
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.test.assertEquals

class CosTest {
    @ParameterizedTest
    @CsvFileSource(resources = ["/data.csv"], numLinesToSkip = 1)
    fun coverageTableFile(
        testName: String,
        input: Double,
        expected: Double,
        tolerance: Double
    ) {
        assertEquals(
            expected,
            cos(input),
            tolerance,
            "$testName failed, actual: ${cos(input)}"
        )
    }

    @Test
    fun `cos should return 1 when x is 0`() {
        assertEquals(1.0, cos(0.0), 1e-2)
    }

    @Test
    fun `cos should return 0,866 when x is 0,52`() {
        assertEquals(sqrt(3.0)/2, cos(PI/6), 1e-2)
    }

    @Test
    fun `cos should return 0,7 when x is 0,785`() {
        assertEquals(sqrt(2.0)/2, cos(PI/4), 1e-2)
    }

    @Test
    fun `cos should return 0,5 when x is 1,047`() {
        assertEquals(0.5, cos(PI/3), 1e-2)
    }

    @Test
    fun `cos should return 0 when x is 1,57`() {
        assertEquals(0.0, cos(PI/2), 1e-2)
    }

    @Test
    fun `cos should return -0,5 when x is 2,09`() {
        assertEquals(-0.5, cos(2*PI/3), 1e-2)
    }

    @Test
    fun `cos should return -0,7 when x is 2,356`() {
        assertEquals(-sqrt(2.0)/2, cos(3*PI/4), 1e-2)
    }

    @Test
    fun `cos should return -0,866 when x is 2,618`() {
        assertEquals(-sqrt(3.0)/2, cos(5*PI/6), 1e-2)
    }


    @Test
    fun `cos should return -1 when x is PI`() {
        assertEquals(-1.0, cos(PI), 1e-2)
    }
}