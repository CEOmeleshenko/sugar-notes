package com.ceomeleshenko.sugarnotes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ceomeleshenko.sugarnotes.data.entity.Note
import com.ceomeleshenko.sugarnotes.ui.note.NoteViewModel
import com.ceomeleshenko.sugarnotes.ui.note.NotesScreen
import com.ceomeleshenko.sugarnotes.ui.theme.GreenPrimary
import com.ceomeleshenko.sugarnotes.ui.theme.Typography

@Composable
fun HomeScreen(
    viewModel: NoteViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenPrimary)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable {
                        navController.navigate("Profile")
                    }
            )
        }

        Text(
            text = "Месяц",
            style = Typography.titleLarge,
            modifier = Modifier
                .padding(16.dp, 0.dp)
        )

        Text(
            text = "Календарь",
            style = Typography.titleLarge,
            modifier = Modifier
                .padding(16.dp, 0.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .width(260.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {

            }

            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Profile",
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .align(Alignment.Bottom)
                    .clickable {
                        navController.navigate("AddNote")
                    }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NotesScreen(viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenPrimary)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clickable { }
            )
        }

        Text(
            text = "Месяц",
            style = Typography.titleLarge,
            modifier = Modifier
                .padding(16.dp, 0.dp)
        )

        Text(
            text = "Календарь",
            style = Typography.titleLarge,
            modifier = Modifier
                .padding(16.dp, 0.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .width(260.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {

            }

            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Profile",
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .align(Alignment.Bottom)
                    .clickable { }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(334.dp)
                    .height(168.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.White)
            ) {

            }
        }
    }
}