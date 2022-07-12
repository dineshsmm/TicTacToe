package com.dinesh.tictactoe.model

import com.dinesh.tictactoe.ui.Player

data class GameCell(val player: Player = Player.NoPlayer, val icon: Int = -1)