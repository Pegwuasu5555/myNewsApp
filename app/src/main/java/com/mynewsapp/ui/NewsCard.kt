package com.mynewsapp.ui

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.sp
import com.mynewsapp.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(title: String, dateTime: String, url: String, category: String = "news", onClick: () -> Unit, onCatPress: () -> Unit) {
    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        ElevatedCard(
            onClick = { onClick() },
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),

            ) {
            GlideImage(
                model = url, contentDescription = "House image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .height(280.dp), contentScale = ContentScale.FillBounds
            )

            Column(modifier = Modifier.padding(20.dp, 20.dp)) {
                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Filled.DateRange, contentDescription = "Time icon"
                    )
                    Text(
                        text = dateTime,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = Color.Gray
                    )
                }
                Text(text = title, lineHeight = 26.sp, fontSize = 20.sp)
                Text(text = category, fontSize = 16.sp, color = Color.Red,
                    modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp).clickable { onCatPress() })

            }

        }

    }
}