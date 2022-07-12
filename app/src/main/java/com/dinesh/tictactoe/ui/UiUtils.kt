package com.dinesh.tictactoe.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
sealed class CellType constructor(
    val cell: GridCells,
    val cellPadding: PaddingValues,
    val shape: Shape,
    val bgColor: Color,
    val strokeColor: Color
) {
    class FixedCell(
        size: Int,
        cellPadding: PaddingValues = PaddingValues(1.dp),
        shape: Shape = RoundedCornerShape(4.dp),
        bgColor: Color = Color(0xddffccaa),
        strokeColor: Color = Color.Red
    ) :
        CellType(
            cell = GridCells.Fixed(size),
            cellPadding = cellPadding,
            shape = shape,
            bgColor = bgColor,
            strokeColor = strokeColor
        )

    class GridCell(
        size: Dp,
        cellPadding: PaddingValues = PaddingValues(1.dp),
        shape: Shape = RoundedCornerShape(4.dp),
        bgColor: Color = Color.White,
        strokeColor: Color = Color.Red
    ) :
        CellType(
            cell = GridCells.Adaptive(size),
            cellPadding = cellPadding,
            shape = shape,
            bgColor = bgColor,
            strokeColor = strokeColor
        )
}

sealed class Player {
    object FirstPlayer : Player()
    object SecondPlayer: Player()
    object NoPlayer: Player()
}