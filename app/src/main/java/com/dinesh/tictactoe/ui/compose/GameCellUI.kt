package com.dinesh.tictactoe.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dinesh.tictactoe.model.GameCell
import com.dinesh.tictactoe.ui.CellType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameCellUI(
    gridCell: CellType,
    gridItems: List<GameCell>,
    containerModifier: Modifier = Modifier
        .padding(20.dp)
        .background(Color.Magenta),
    onSelectComposable: @Composable (gameCell: GameCell) -> Unit,
    onClick: (position: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = gridCell.cell,
        contentPadding = gridCell.cellPadding,
        modifier = containerModifier
    ) {
        itemsIndexed(gridItems) { index, item ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(gridCell.bgColor, shape = gridCell.shape)
                    .padding(gridCell.cellPadding)
                    .clickable {
                        onClick(index)
                    }
            ) {
                onSelectComposable(item)
            }
        }
    }
}