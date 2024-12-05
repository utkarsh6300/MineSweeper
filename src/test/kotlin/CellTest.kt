import Cell as Cell

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test


class CellTest {
    private lateinit var cell:Cell
    @BeforeEach
    fun setup(){
        cell = Cell()
    }
    @Test
    fun `test setting correct default values`() {
        assertAll("cell",
            { assertEquals(false, cell.mine) },
            { assertEquals(0, cell.neighbourMines) },
            { assertEquals(false, cell.explored) }
        )
    }
    @Test
    fun `test setting correct custom values`() {
        cell = Cell(true,6,true)
        assertAll("cell",
            { assertEquals(true, cell.mine) },
            { assertEquals(6, cell.neighbourMines) },
            { assertEquals(true, cell.explored) }
        )
    }
}