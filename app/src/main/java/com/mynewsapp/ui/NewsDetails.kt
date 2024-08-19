package com.mynewsapp.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mynewsapp.R
import com.mynewsapp.data.newsresponse.Article
import com.mynewsapp.navigation.Screens
import com.mynewsapp.ui.components.Share
import com.mynewsapp.viewmodels.NewsViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetails(navController: NavHostController, viewModel: NewsViewModel, newsId: String?) {

    fun shareLink(text: String){
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
    }



    val article = newsId?.let { viewModel.getArticleByTitle(it) }

    fun linkToCat() {
        if (article != null) {
            navController.navigate("newsCategories/${article.source.name}")
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            if (article != null) {
                val date = OffsetDateTime.parse(article.publishedAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                val formatDate = date.format(DateTimeFormatter.ofPattern("EEEE dd, MMMM yyyy HH:mm", Locale.ENGLISH)).toString()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Arrow back",
                            modifier = Modifier.size(30.dp).clickable { navController.navigateUp() }
                        )
                        Share(text = article.url, context = LocalContext.current)
                    }
                    Divider(thickness = 1.dp)
                    Column(modifier = Modifier.padding(20.dp, 10.dp)) {
                        Text(
                            text = article.title, lineHeight = 28.sp,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
                        )
                        Text("By ${article.author}", fontSize = 15.sp)
                        Text(text = formatDate, fontSize = 15.sp, color = Color.Gray)
                    }

                    Column {
                        GlideImage(
                            model = article.urlToImage, contentDescription = article.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                                .height(300.dp), contentScale = ContentScale.FillBounds
                        )
                        Column(
                            modifier = Modifier
                                .padding(16.dp, 12.dp, 16.dp, 0.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color.LightGray)
                                .clickable { linkToCat() }
                        ) {
                            Text(text = article.source.name, modifier = Modifier.padding(20.dp, 10.dp))
                        }
                        Column(modifier = Modifier.padding(16.dp, 10.dp)) {
                            Text(
                                text = article.content.trimIndent(),
                                maxLines = Int.MAX_VALUE, // Effectively removes line limit
                                overflow = TextOverflow.Clip,
                                modifier = Modifier
                                    .fillMaxWidth()

                            )
                        }


                    }
                }
            }
        }
    }


}