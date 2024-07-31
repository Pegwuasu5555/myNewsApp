package com.mynewsapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mynewsapp.R
import com.mynewsapp.data.newsresponse.Article
import com.mynewsapp.navigation.Screens
import com.mynewsapp.viewmodels.NewsViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun Categories(navController: NavHostController, viewModel: NewsViewModel, sourceName: String?) {
    val articles = sourceName?.let { viewModel.getSpecificArticleCategory(it) }
    fun onNewsClick(article: Article){
        navController.navigate("newsDetails/${article.title}")
    }
    fun linkToCat(article: Article) {
        navController.navigate("newsCategories/${article.source.name}")
    }
    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth()) {

        Column(modifier = Modifier.padding(bottom = 20.dp, top = 20.dp)) {
            if (sourceName != null) {
                Text(text = sourceName, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.titleLarge.fontSize)
            }

            Divider(thickness = 1.dp, modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp))
        }


        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (articles != null) {
                if(articles.isNotEmpty()) {
                    items(articles) { article ->
                        if(article.urlToImage != null) {

                            val date = OffsetDateTime.parse(article.publishedAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                            val formatDate = date.format(DateTimeFormatter.ofPattern("dd LLLL yyyy")).toString()
                            NewsCard(title = article.title, dateTime = formatDate, url = article.urlToImage,
                                onClick = { onNewsClick(article) }, category = article.source.name, onCatPress = { linkToCat(article)} )
                        }
                    }
                }
            }

        }

    }
}