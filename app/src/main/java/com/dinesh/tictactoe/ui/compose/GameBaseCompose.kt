package com.dinesh.tictactoe.ui.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.dinesh.tictactoe.model.GameCell
import com.dinesh.tictactoe.ui.CellType

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameBaseContainerCompose(
    modifier: Modifier,
    contentBoard: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            GameTopBar(title = "TicTacToe")
        }
    ) {
        contentBoard.invoke()
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun GameTopBar(
    title: String,
    toolBarBackground: Color = MaterialTheme.colorScheme.primary,
    titleColor: Color = MaterialTheme.colorScheme.onPrimary,
    alignment: TextAlign = TextAlign.Center,
    fontSize: TextUnit = TextUnit(28f, TextUnitType.Sp)
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(toolBarBackground)
            .fillMaxWidth()
            .fillMaxHeight(0.08f)
    ) {
        Text(
            text = title,
            color = titleColor,
            textAlign = alignment,
            fontSize = fontSize
        )
    }
}

@Preview
@Composable
fun PreviewCompose() {
    var boardSize by remember { mutableStateOf(10) }
    var isError by remember { mutableStateOf(false) }
    GameBaseContainerCompose(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Spacer(modifier = Modifier.padding(50.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
            )
            InputTextField(
                defaultValue = boardSize.toString(),
                labelText = "Board Size",
                isErrorValue = isError
            ) {
                boardSize = if (it.toInt() in 3..6)
                    it.toInt()
                else {
                    isError = true
                    3
                }
            }
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                GameCellUI(
                    gridCell = CellType.FixedCell(
                        boardSize
                    ),
                    gridItems = getItems(boardSize * boardSize),
                    onSelectComposable = { item: GameCell ->
                        item.icon.takeIf { it != -1 }?.let { iconId ->
                            Image(
                                painter = painterResource(id = iconId),
                                contentDescription = "Player Icon $iconId",
                                Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } ?: Box(modifier = Modifier.fillMaxSize())
                    }
                ) {

                }
            }
        }
    }
}

@Composable
fun InputTextField(
    defaultValue: String,
    labelText: String,
    isErrorValue: Boolean = false,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onValueChange: (value: String) -> Unit
) {
    var value = defaultValue
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = {
            Text(text = labelText)
        },
        onValueChange = {
            value = it
            onValueChange(it)
        },
        placeholder = {
            Text(text = labelText)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        isError = isErrorValue
    )
}

fun getItems(boardSize: Int): List<GameCell> {
    val items by mutableStateOf(mutableListOf<GameCell>())
    for (item in 0 until boardSize)
        items.add(GameCell())
    return items
}


