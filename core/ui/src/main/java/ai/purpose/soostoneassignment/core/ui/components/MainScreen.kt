package ai.purpose.soostoneassignment.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val CONTENT_HORIZONTAL_PADDING = 16

@Composable
fun MainScreen(
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit = {},
    contentHorizontalPadding: Int = CONTENT_HORIZONTAL_PADDING,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = topBar,
        bottomBar = bottomBar,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            content()
        }
    }
}

@Composable
@Preview
private fun MainScreen_Preview() {
        MainScreen(
            topBar = {
                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f)
                )
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f)
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxSize()
                    .padding(top = 0.2.dp)
            )
        }

}
