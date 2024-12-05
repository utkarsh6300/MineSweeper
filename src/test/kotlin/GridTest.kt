import Grid as Grid

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GridTest {
    private lateinit var minesweeper:Grid
    @BeforeEach
    fun setup(){
        minesweeper=Grid()
    }
    @Test
    fun `check setting default values`(){
        assertAll("minesweeper",
            { assertEquals(8, minesweeper.rows) },
            { assertEquals(8, minesweeper.columns) },
            { assertEquals(10, minesweeper.numberOfMines) },
            { assertEquals(0, minesweeper.numberOfExploredCells) }
        )
    }
    @Test
    fun `check setting custom values`(){
        minesweeper=Grid(10,10,25,0)
        assertAll("grid",
            { assertEquals(10, minesweeper.rows) },
            { assertEquals(10, minesweeper.columns) },
            { assertEquals(25, minesweeper.numberOfMines) },
            { assertEquals(0, minesweeper.numberOfExploredCells) }
        )
    }
    @Test
    fun `check assign mines marks correct number of mines`(){
     // call assign mines and calculate number of mines in grid using traversal and compare with numberOfMines
        minesweeper.assignMines()
        val grid = minesweeper.grid
        var numberOfActualMinesInGrid =0
        for (rowIndex in 0 until grid.size) {
            for (columnIndex in 0 until grid[rowIndex].size) {
                if(grid[rowIndex][columnIndex].mine) numberOfActualMinesInGrid++
            }
        }
        assertEquals(minesweeper.numberOfMines,numberOfActualMinesInGrid,"number of mines in grid should be same as initialised")
    }
    @Test
    fun `check calculateMinesUtil to correctly calculate nearby number of mines`(){
        // assign two nearby cells as mines and call calculate
        // skip assignMines as it randomly assigns mines
        val grid = minesweeper.grid
        grid[0][0].mine=true
        grid[0][2].mine=true
        minesweeper.calculateNearbyMines()
        assertEquals(grid[0][1].neighbourMines,2,"neighbourMines should be 2")
    }

}