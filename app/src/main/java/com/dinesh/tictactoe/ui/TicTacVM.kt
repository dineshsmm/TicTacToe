package com.dinesh.tictactoe.ui

import androidx.annotation.IdRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dinesh.tictactoe.model.GameCell

class TicTacVM : ViewModel() {

    var boardSize: Int by mutableStateOf(3)
        private set

    var gridCell: CellType by mutableStateOf(CellType.FixedCell(3))
        private set

    var playerIcon = hashMapOf<Player, Int>()
        private set

    var boxList: MutableList<GameCell> by mutableStateOf(mutableListOf())
        private set

    var activePlayer: Player = Player.FirstPlayer

    var message: String by mutableStateOf("")

    var clickCounter = 0

    fun updateBoardSize(size: Int) {
        boardSize = size * 2
        createBoxItem()
    }

    private fun createBoxItem() {
        boxList.clear()
        for (i in 0..boardSize)
            boxList.add(GameCell())
    }

    fun updateGridCellType(cellType: CellType) {
        gridCell = cellType
    }

    fun playerShape(playerType: Player, @IdRes icon: Int) {
        playerIcon[playerType] = icon
    }

    private fun changePlayer() {
        activePlayer = if (activePlayer == Player.FirstPlayer)
            Player.SecondPlayer else Player.FirstPlayer
    }

    private fun getPlayerIcon(): Int {
        return playerIcon[activePlayer] ?: -1
    }

    fun handleClick(index: Int) {
        if (boxList[index].player != Player.NoPlayer)
            message = "Invalid Selection"
        else {
            val item = boxList[index]
            boxList[index] = item.copy(player = activePlayer, icon = getPlayerIcon())
        }

        clickCounter.inc()
        changePlayer()
        if (clickCounter > 4) {
            //TODO validate winner
        }
    }
}