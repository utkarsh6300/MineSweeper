import Game as Game
import Grid as Grid

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GameTest {
    private lateinit var game:Game
    @BeforeEach
    fun setup(){
        game=Game()
    }
    @Test
    fun `verify checkWinner logic`(){
      // manually set numberOfExploredCell
        val minesweeper=Grid()
        assertFalse(game.checkWinner(minesweeper),"initially winner must be false")
        minesweeper.numberOfExploredCells=54;
        assertTrue(game.checkWinner(minesweeper),"winner should be true as manually changed explored item")
    }
    @Test
    fun `verify exploreSafeCells logic`(){
        // call exploreSafeCells just after initialising it should explore all cells
        val minesweeper=Grid()
        game.exploreSafeCells(0,0,minesweeper)
        val grid = minesweeper.grid
        assertAll("Check that all cells are explored",{
            for (rowIndex in 0 until grid.size) {
                for (columnIndex in 0 until grid[rowIndex].size) {
                    assertTrue(grid[rowIndex][columnIndex].explored,"Cell at ($rowIndex, $columnIndex) is not explored")
                }
            }
        })
    }
    // test cases for winning game and losing games
    //losing game check is redundant for now can be removed
    @Test
    fun `verify losing game scenario`() {
        val minesweeper = Grid()
        val grid = minesweeper.grid
        grid[0][0].mine=true
        val currentCell =grid[0][0].mine
        assertFalse(currentCell, "Game should be lost if a mine is triggered.")
    }
}