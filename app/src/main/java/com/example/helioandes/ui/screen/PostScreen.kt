package com.example.helioandes.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helioandes.viewmodel.PostViewModel

@Composable
fun PostScreen(){
    val viewModel: PostViewModel = viewModel()
    val posts by viewModel.posts.collectAsState()

    LaunchedEffect(Unit){
        viewModel.cargarPosts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ){
        Text(
            text = "Listado de Posts",
            fontSize = 20.sp,
            color = Color(0xFF009688)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(posts) {
                posts ->
                    Column (
                        modifier = Modifier.padding(10.dp)
                    ){
                        Text(
                            text = posts.title,
                            color = Color(0xFF00BCD4),
                        )
                        Text(
                            text = posts.body.take(50) + "....",
                            color = Color(0xFF00BCD4),
                        )
                    }
            }
        }



    }

}