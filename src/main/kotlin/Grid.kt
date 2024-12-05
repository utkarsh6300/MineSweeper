import Cell as Cell
import util.RandomNumberGenerator as RandomNumberGenerator
class Grid (val rows:Int = 8,val columns:Int=8,val numberOfMines:Int=10,var numberOfExploredCells:Int=0){
    val grid= Array(rows){ _ ->
        Array(columns){ _ ->
            Cell()
        }
    }

    // assigns mine to random cells in grid
    fun assignMines(){
        val numberGenerator = RandomNumberGenerator()
        for( i in 0 until numberOfMines){
            val randomRowNumber = numberGenerator.randomInteger(0,rows-1);
            val randomColumnNumber = numberGenerator.randomInteger(0,columns-1);
            grid[randomRowNumber][randomColumnNumber].mine=true
        }
    }
    // Utility function to check if a cell is within bounds
    fun isInBounds(rowIndex: Int, columnIndex: Int, rows: Int, columns: Int): Boolean {
        return rowIndex in 0 until rows && columnIndex in 0 until columns
    }
    // utility function to calculate numbers of mines near a particular cell
    fun calculateMinesUtil(rowIndex:Int,columnIndex:Int):Int{
        var neighbourMines:Int=0
        val rowTraversal = arrayOf(-1,0,1)
        val columnTraversal = arrayOf(-1,0,1)
        for (rowTraversalElement in rowTraversal) {
            for (columnTraversalElement in columnTraversal) {
                // Check if the current cell is within bounds using the utility function
                if (!isInBounds(rowIndex + rowTraversalElement, columnIndex + columnTraversalElement, rows, columns)) {
                    continue
                }
                // If it's within bounds and is a mine, increment the neighbourMines count
                if (grid[rowIndex + rowTraversalElement][columnIndex + columnTraversalElement].mine) {
                    neighbourMines++
                }
            }
        }
        return neighbourMines
    }
    // sets number of neighbouring mines to all cells in grid
    fun calculateNearbyMines(){
        for(rowIndex in 0..rows-1){
            for(columnIndex in 0..columns-1){
                grid[rowIndex][columnIndex].neighbourMines= calculateMinesUtil(rowIndex,columnIndex)
            }
        }
    }
}