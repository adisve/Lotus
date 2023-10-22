package android.app.lotus.view.home.articles

import android.app.lotus.domain.models.article
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ArticleListStatus
import android.app.lotus.observables.ArticleViewModel
import android.app.lotus.view.buttons.NavButton
import android.app.lotus.view.general.DotsPulsing
import android.app.lotus.view.theme.fonts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText
import com.halilibo.richtext.ui.RichTextThemeIntegration


@Composable
fun ArticleDetail(navController: NavHostController, articleTitle: String) {
    val articleViewModel: ArticleViewModel = hiltViewModel()
    val status = articleViewModel.status.observeAsState(initial = ArticleListStatus.Loading)


    when (status.value) {
        ArticleListStatus.Loading -> {
            DotsPulsing()
        }

        ArticleListStatus.Empty -> {
            Text("Empty")
        }

        ArticleListStatus.Populated -> {
            val article = articleViewModel.articleList.find { it.title == articleTitle }
            if (article != null) {
                ArticleComponent(navController, article)
            }
        }
    }
}

@Composable
fun ArticleComponent(navController: NavHostController, article: article) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(enabled = true, state = scrollState)
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Row {
            Text(
                text = article.title.uppercase(),
                modifier = Modifier.padding(30.dp, bottom = 0.dp, top = 0.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Row {

            RichTextThemeIntegration(
                textStyle = provideTextStyle(),
                contentColor = provideContentColor(),
            ) {
                RichText(
                    modifier = Modifier
                        .padding(30.dp)
                ) {
                    Markdown(
                        article.content.trimIndent()
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        ) {
            NavButton(text = "Finish", navController = navController, route = Routes.articles)

        }
        Spacer(modifier = Modifier.height(60.dp))
    }
}

fun provideTextStyle(): @Composable () -> TextStyle {
    return {
        TextStyle(
            fontFamily = fonts,
            fontSize = 20.sp,
        )
    }
}

fun provideContentColor(): @Composable () -> Color {
    return {
        MaterialTheme.colorScheme.onSurface
    }
}
