package com.dinesh.tictactoe.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DropDownSelector(
    isExpanded: Boolean,
    modifier: Modifier,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    DropdownMenu(expanded = isExpanded,
        modifier = modifier,
        onDismissRequest = {
            onDismiss()
        }) {
        content()
    }
}

@Composable
fun DropDownItem(
    containerModifier: Modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(30.dp),
    contentModifier: Modifier = Modifier
        .fillMaxSize(),
    content: String,
    leadingIcon: Int = -1,
    trailingIcon: Int = -1,
    onClick: (item: String) -> Unit
) {
    val leading = getIcon(leadingIcon, "Leading Icon")
    val trailing = getIcon(trailingIcon, "Trailing Icon")
    DropdownMenuItem(text = {
        Text(
            text = content,
            modifier = contentModifier
                .wrapContentHeight(Alignment.CenterVertically),
            textAlign = TextAlign.Start
        )
    }, onClick = {
        onClick(content)
    }, modifier = containerModifier.padding(bottom = 2.dp),
        leadingIcon = leading,
        trailingIcon = trailing
    )
}

fun getIcon(iconId: Int, description: String): @Composable (() -> Unit)? {
    val icon: @Composable (() -> Unit)? = if (iconId > 0) {
        @Composable {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = description
            )
        }
    } else null
    return icon
}

@Preview
@Composable
fun DropDownSelectorPreview() {
    var isExpanded = true
    val contents = getDropDownDummy()
    DropDownSelector(
        isExpanded = isExpanded,
        modifier = Modifier.fillMaxSize(),
        onDismiss = {
            //isExpanded = false
        }) {
        contents.onEach { item ->
            DropDownItem(content = item.first, trailingIcon = item.second) {

            }
        }
    }
}

fun getDropDownDummy(): List<Pair<String, Int>> {
    val items = mutableListOf<Pair<String, Int>>()
    items.add(Pair("Plus", -1))
    items.add(Pair("Green Box", -1))
    items.add(Pair("Notification Bg", -1))
    return items.toList()
}
