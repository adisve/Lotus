package android.app.lotus.view.statistics

import android.app.lotus.observables.StatisticsStatus
import android.app.lotus.observables.StatisticsViewModel
import android.app.lotus.view.general.DotsPulsing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun Statistics(navController: NavHostController) {
    val statisticsViewModel: StatisticsViewModel = hiltViewModel()
    val status = statisticsViewModel.status.observeAsState(initial = StatisticsStatus.Loading)
    val chartEntryModel = entryModelOf(4f, 12f, 8f, 16f)

    when (status.value) {
        StatisticsStatus.Loading -> {
            DotsPulsing()
        }

        StatisticsStatus.Empty -> {
            DotsPulsing()
        }

        StatisticsStatus.Populated -> {
            // Todo: use actual data...
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 75.dp)
                        .padding(horizontal = 25.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 25.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 30.sp,
                        text = "Company members that finished evaluation process:"
                    )
                    ProvideChartStyle(chartStyle = m3ChartStyle()) {
                        Chart(
                            chart = lineChart(),
                            model = chartEntryModel,
                            startAxis = rememberStartAxis(),
                            bottomAxis = rememberBottomAxis(),
                        )
                    }
                }
            }
        }
    }
}