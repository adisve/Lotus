package android.app.lotus.view

import android.app.lotus.R
import android.app.lotus.types.Article
import android.app.lotus.types.exampleArticle
import android.app.lotus.ui.theme.LotusTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText


@Composable
fun ArticleView(article: Article) {
    Column {
        Image(
            painter = painterResource(R.drawable.lotusmodellen_logo), contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, bottom = 20.dp)
        )
        ArticleComponent(article)
    }
}

@Composable
fun ArticleComponent(article: Article) {
    val scrollState = rememberScrollState()


    // Header section for article title
    Column(
        modifier = Modifier.verticalScroll(enabled = true, state = scrollState)
    ) {
        Row {
            Text(
                text = article.title.uppercase(),
                modifier = Modifier.padding(30.dp, bottom = 0.dp, top = 0.dp),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold

            )
        }
        Row {
            RichText(
                modifier = Modifier
                    .padding(30.dp)
            ) {
                Markdown(
                    article.markdownContent.trimIndent()
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        ) {
            // Circular button for submitting article with checkmark icon inside
            Button(
                onClick = { },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(Color(148, 0, 124)),
                modifier = Modifier
                    .height(65.dp)
                    .width(180.dp)
            ) {
                Text(
                    text = "Finish",
                    fontSize = 20.sp
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleViewPreview() {
    LotusTheme {
        ArticleView(exampleArticle)
    }
}
