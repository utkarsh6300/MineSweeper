import java.util.Queue
import java.util.LinkedList
import Cell as Cell
import Grid as Grid

class Game {
    // ends game when required
    fun endGame(){
        println("Game Finished")
    }
    // checks whether player is winner or not
    fun checkWinner(minesweeper:Grid):Boolean{
        if(minesweeper.numberOfExploredCells+minesweeper.numberOfMines==minesweeper.rows*minesweeper.columns){
            println("You Won")
            return true
        }
        return false
    }
    // eplores all connected cells that are safe untill has atleast one mine in neighbour
    fun exploreSafeCells(row:Int,column:Int,minesweeper:Grid){
        val grid:Array<Array<Cell>> =minesweeper.grid
        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        queue.add(Pair(row,column))
        while(queue.isNotEmpty()){
            val (rowIndex, columnIndex) = queue.poll()
            val currentCell:Cell=grid[rowIndex][columnIndex]
            if (currentCell.explored) continue
            currentCell.explored=true
            minesweeper.numberOfExploredCells += 1
            if(currentCell.neighbourMines>0) {
                continue
            }
            // this can be optimised
            //explore 4 nearby cells
            if(rowIndex+1<grid.size)
                queue.add(Pair(rowIndex+1,columnIndex))
            if(rowIndex-1>=0)
                queue.add(Pair(rowIndex-1,columnIndex))
            if(columnIndex+1<grid[rowIndex].size)
                queue.add(Pair(rowIndex,columnIndex+1))
            if(columnIndex-1>=0)
                queue.add(Pair(rowIndex,columnIndex-1))

        }
    }
    fun showGrid(grid:Array<Array<Cell>>){
        for(rowIndex in 0..grid.size-1){
            for(columnIndex in 0..grid[rowIndex].size-1){
                val currentCell = grid[rowIndex][columnIndex]
                // print("cell is $rowIndex $columnIndex ")
                // print("mine is ${currentCell.mine} ")
                // print("neighbourMines is ${currentCell.neighbourMines} ")
                // print("explored is ${currentCell.explored} ")
                print("$rowIndex $columnIndex ")
                print("${currentCell.mine} ")
                print("${currentCell.neighbourMines} ")
                print("${currentCell.explored} ")
            }
            println()
        }
    }
    // actual game logic
    fun play(){
        val minesweeper=Grid()
        minesweeper.assignMines()
        minesweeper.calculateNearbyMines()
        while(true){
            println("Enter row and column number")
            val row = readln().toInt()
            val column = readln().toInt()
            // check input is in bound.
            // if(row<minesweeper.rows)
            val currentCell:Cell=minesweeper.grid[row][column]
            // if already explored notify and continue
            if(currentCell.explored){
                println("already explored cell")
                continue
            }
            // if its a mine just end game
            if(currentCell.mine) {
                endGame()
                break
            }
            // as game is continuing explore all neighboring cells that are safe and has less than 1 mine nearby
            exploreSafeCells(row,column,minesweeper)
            // at each step check whether user is winner or not
            if(checkWinner(minesweeper)) {
                break
            }
            showGrid(minesweeper.grid)
        }
    }
}

// refactoring needed in this
// all utility function must be transferred to other class just like random number generator  //single responsibility principle