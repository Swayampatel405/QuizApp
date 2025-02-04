package com.appvantage.quizapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appvantage.quizapp.R
import com.appvantage.quizapp.presentation.util.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropDownMenu(
    menuName:String,
    text:String,
    menuList:List<String>,
    onDropDownClick:(String) -> Unit
){
    var isExpanded by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier.fillMaxWidth().padding(Dimens.SmallPadding)
    ){

        Text(
            text = menuName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.blue_grey)
        )

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = !isExpanded}
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                value = text,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTrailingIconColor = colorResource(R.color.orange),
                    focusedTrailingIconColor = colorResource(R.color.orange),
                    focusedBorderColor = colorResource(R.color.dark_slate_blue),
                    unfocusedBorderColor = colorResource(R.color.dark_slate_blue),
                    focusedContainerColor = colorResource(R.color.dark_slate_blue),
                    unfocusedContainerColor = colorResource(R.color.dark_slate_blue)
                ),
                shape = RoundedCornerShape(15.dp)
            )

            DropdownMenu(
                modifier = Modifier.background(
                    color = colorResource(R.color.mid_night_blue)
                ),
                expanded = isExpanded,
                onDismissRequest = {isExpanded = false}
            ) {
                menuList.forEachIndexed { index:Int, text:String ->
                    DropdownMenuItem(
                        text = { Text(text = text, color = colorResource(R.color.blue_grey)) },
                        onClick = {
                            onDropDownClick(menuList[index])
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }

}







@Preview
@Composable
fun preD(){
    val list = listOf("Item1","Item2")
    AppDropDownMenu(
        menuName = "DropDown Menu",
        menuList = list,
        onDropDownClick = {},
        text = "Item1"
    )
}