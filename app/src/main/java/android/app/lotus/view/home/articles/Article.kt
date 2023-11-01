package android.app.lotus.view.home.articles

import android.app.lotus.domain.models.realm.article
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ArticleViewModel
import android.app.lotus.view.buttons.ActionButton
import android.app.lotus.view.theme.fonts
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText
import com.halilibo.richtext.ui.RichTextThemeIntegration


@Composable
fun ArticleDetail(navController: NavHostController, articleViewModel: ArticleViewModel, title: String) {
    val article = articleViewModel.articleList.find { it.title == title }
    if (article != null) {
        ArticleComponent(navController, article, onClick = { articleName ->
            articleViewModel.markArticleAsFinished(articleName)
        })
    }
}

@Composable
fun ArticleComponent(navController: NavHostController, article: article, onClick: (String) -> Unit) {
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
                .padding(bottom = 20.dp)
                .padding(horizontal = 25.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ActionButton(
                text = "Finish",
                onClick = {
                    onClick(article.title)
                    navController.popBackStack()
                },
            )
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
