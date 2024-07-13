package com.mynewsapp.ui

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mynewsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(title: String, dateTime: String, image: Painter ) {
    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        ElevatedCard(
            onClick = { /*TODO*/ },
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),

        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                        painter = image,
                        contentDescription = "News one",
                        contentScale = ContentScale.FillBounds
                    )
            Column(modifier = Modifier.padding(20.dp, 20.dp)) {
                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Filled.DateRange, contentDescription = "Time icon"
                    )
                    Text(text = dateTime,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = Color.Gray
                    )
                }
                Text(text = title, fontSize = MaterialTheme.typography.titleLarge.fontSize)

            }

        }

    }
}