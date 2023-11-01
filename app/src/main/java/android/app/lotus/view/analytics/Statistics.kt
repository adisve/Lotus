package android.app.lotus.view.analytics

import android.app.lotus.domain.models.realm.user
import android.app.lotus.observables.StatisticsStatus
import android.app.lotus.observables.StatisticsViewModel
import android.app.lotus.observables.UserRole
import android.app.lotus.view.general.DotsPulsing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun Statistics(navController: NavHostController) {
    val statisticsViewModel: StatisticsViewModel = hiltViewModel()
    val status = statisticsViewModel.status.observeAsState(initial = StatisticsStatus.Loading)

    when (status.value) {
        StatisticsStatus.Loading -> {
            DotsPulsing()
        }

        StatisticsStatus.Empty -> {
            DotsPulsing()
        }

        StatisticsStatus.Populated -> {
            StatisticsStatus(userList = statisticsViewModel.userList)
        }
    }
}

@Composable
fun StatisticsStatus(userList: List<user>) {
    val scrollState = rememberScrollState()
    val evaluatedCount = userList.filter { it.evaluated == true }.size
    val evaluatedCountEmployees =
        userList.filter { it.role == UserRole.EMPLOYEE.displayName && it.evaluated == true }.size
    val evaluatedCountManagers =
        userList.filter { it.role == UserRole.MANAGER.displayName && it.evaluated == true }.size
    val evaluatedChartEntryModel =
        entryModelOf(
            userList.size - evaluatedCount,
            evaluatedCount,
            userList.size
        )

    val horizontalAxisValueFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
            when (value) {
                0f -> "Not evaluated"
                1f -> "Evaluated"
                else -> "Total"
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(enabled = true, state = scrollState),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp)
                .padding(horizontal = 25.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 25.dp),
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 30.sp,
                text = "Company members that finished the evaluation process:"
            )
            ProvideChartStyle(chartStyle = m3ChartStyle()) {
                Chart(
                    chart = columnChart(),
                    model = evaluatedChartEntryModel,
                    startAxis = rememberStartAxis(
                        valueFormatter = AxisValueFormatter { value, _ ->
                            value.toInt().toString()
                        },
                        itemPlacer = AxisItemPlacer.Vertical.default(
                            maxItemCount = userList.size
                        )
                    ),
                    bottomAxis = rememberBottomAxis(
                        title = "Not evaluated - Evaluated",
                        valueFormatter = horizontalAxisValueFormatter
                    ),
                )
            }

            Text(
                modifier = Modifier.padding(vertical = 25.dp),
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 30.sp,
                text = "Managers / Employees count:"
            )
            ProvideChartStyle(chartStyle = m3ChartStyle()) {
                Chart(
                    chart = columnChart(),
                    model = entryModelOf(
                        userList.filter { it.role == UserRole.MANAGER.displayName }.size,
                        userList.filter { it.role == UserRole.EMPLOYEE.displayName }.size,
                        userList.filter { it.role == UserRole.HR.displayName }.size,
                        userList.size
                    ),
                    startAxis = rememberStartAxis(
                        valueFormatter = AxisValueFormatter { value, _ ->
                            value.toInt().toString()
                        },
                        itemPlacer = AxisItemPlacer.Vertical.default(
                            maxItemCount = userList.size
                        )
                    ),
                    bottomAxis = rememberBottomAxis(
                        title = "Not evaluated - Evaluated",
                        valueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
                            when (value) {
                                0f -> "Managers"
                                1f -> "Employees"
                                2f -> "HR"
                                else -> "Total"
                            }
                        }
                    ),
                )
            }

            Text(
                modifier = Modifier.padding(vertical = 25.dp),
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 30.sp,
                text = "Employees Evaluated:"
            )
            ProvideChartStyle(chartStyle = m3ChartStyle()) {
                Chart(
                    chart = columnChart(),
                    model = entryModelOf(
                        userList.filter { it.role == UserRole.EMPLOYEE.displayName }.size - evaluatedCountEmployees,
                        evaluatedCountEmployees,
                        userList.filter { it.role == UserRole.EMPLOYEE.displayName }.size,
                    ),
                    startAxis = rememberStartAxis(
                        valueFormatter = AxisValueFormatter { value, _ ->
                            value.toInt().toString()
                        },
                        itemPlacer = AxisItemPlacer.Vertical.default(
                            maxItemCount = userList.size
                        )
                    ),
                    bottomAxis = rememberBottomAxis(
                        title = "Not evaluated - Evaluated",
                        valueFormatter = horizontalAxisValueFormatter
                    ),
                )
            }

            Text(
                modifier = Modifier.padding(vertical = 25.dp),
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 30.sp,
                text = "Managers Evaluated:"
            )
            ProvideChartStyle(chartStyle = m3ChartStyle()) {
                Chart(
                    chart = columnChart(),
                    model = entryModelOf(
                        userList.filter { it.role == UserRole.MANAGER.displayName }.size - evaluatedCountManagers,
                        evaluatedCountManagers,
                        userList.filter { it.role == UserRole.MANAGER.displayName }.size,
                    ),
                    startAxis = rememberStartAxis(
                        valueFormatter = AxisValueFormatter { value, _ ->
                            value.toInt().toString()
                        },
                        itemPlacer = AxisItemPlacer.Vertical.default(
                            maxItemCount = userList.size
                        )
                    ),
                    bottomAxis = rememberBottomAxis(
                        title = "Not evaluated - Evaluated",
                        valueFormatter = horizontalAxisValueFormatter
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}