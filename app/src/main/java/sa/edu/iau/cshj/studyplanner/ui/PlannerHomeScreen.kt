package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class PlannerUiState(
    val greeting: String = "Welcome back, Layan",
    val completedTasks: Int = 0,
    val totalTasks: Int = 5
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onCompleteTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = uiState.greeting,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "${uiState.completedTasks} of ${uiState.totalTasks} done",
            style = MaterialTheme.typography.bodyLarge
        )
        Button(
            onClick = onCompleteTask,
            enabled = uiState.completedTasks < uiState.totalTasks
        ) {
            Text(text = "Mark Next Task Done")
        }
    }
}

@Composable
fun PlannerHomeRoute() {
    var uiState by remember {
        mutableStateOf(
            PlannerUiState(
                greeting = "Welcome back, Layan",
                completedTasks = 0,
                totalTasks = 5
            )
        )
    }

    PlannerHomeScreen(
        uiState = uiState,
        onCompleteTask = {
            if (uiState.completedTasks < uiState.totalTasks) {
                uiState = uiState.copy(
                    completedTasks = uiState.completedTasks + 1
                )
            }
        }
    )
}

@Preview(showBackground = true, name = "Nothing done yet")
@Composable
private fun PlannerHomeEmptyPreview() {
    MaterialTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                greeting = "Welcome back, Layan",
                completedTasks = 0,
                totalTasks = 5
            ),
            onCompleteTask = {}
        )
    }
}

@Preview(showBackground = true, name = "Everything done")
@Composable
private fun PlannerHomeCompletedPreview() {
    MaterialTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                greeting = "Welcome back, Layan",
                completedTasks = 5,
                totalTasks = 5
            ),
            onCompleteTask = {}
        )
    }
}