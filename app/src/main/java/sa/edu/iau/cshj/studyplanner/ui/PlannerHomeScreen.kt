package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
    val totalTasks: Int = 3,
    val tasks: List<String> = emptyList()
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onCompleteTaskClick: () -> Unit,
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
            onClick = onCompleteTaskClick,
            enabled = uiState.completedTasks < uiState.totalTasks
        ) {
            Text(text = "Mark Next Task Done")
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = "Study Tasks",
            style = MaterialTheme.typography.titleMedium
        )
        uiState.tasks.forEach { task ->
            OutlinedButton(
                onClick = { onTaskClick(task) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = task)
            }
        }
    }
}

@Composable
fun PlannerHomeRoute(
    onTaskClick: (String) -> Unit
) {
    val initialTasks = listOf(
        "CSC 402 Lab 5 report",
        "Database schema review",
        "HCI prototype check"
    )
    var uiState by remember {
        mutableStateOf(
            PlannerUiState(
                greeting = "Welcome back, Layan",
                completedTasks = 0,
                totalTasks = initialTasks.size,
                tasks = initialTasks
            )
        )
    }

    PlannerHomeScreen(
        uiState = uiState,
        onCompleteTaskClick = {
            if (uiState.completedTasks < uiState.totalTasks) {
                uiState = uiState.copy(
                    completedTasks = uiState.completedTasks + 1
                )
            }
        },
        onTaskClick= onTaskClick
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
                totalTasks = 3,
                tasks =listOf("CSC 402 Lab 5 report","Database schema review","HCI prototype check")
            ),
            onCompleteTaskClick = {},
            onTaskClick  ={}
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
                completedTasks = 3,
                totalTasks = 3,
                tasks = listOf("CSC 402 Lab 5 report", "Database schema review", "HCI prototype check")
            ),
            onCompleteTaskClick = {},
            onTaskClick={}
        )
    }
}