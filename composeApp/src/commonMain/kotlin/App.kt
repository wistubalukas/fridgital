import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import fridgital.composeapp.generated.resources.Res
import fridgital.composeapp.generated.resources.compose_multiplatform
import org.example.fridgital.core.presentation.GroceriesTheme
import org.example.fridgital.core.presentation.ImagePicker
import org.example.fridgital.di.AppModule
import org.example.fridgital.groceries.presentation.GroceryListScreen
import org.example.fridgital.groceries.presentation.GroceryListViewModel

@Composable
fun App(
    appModule: AppModule,
    imagePicker: ImagePicker
) {
    GroceriesTheme {
        val viewModel = getViewModel(
            key = "grocery-list-screen",
            factory = viewModelFactory {
                GroceryListViewModel(appModule.groceryDataSource)
            }
        )
        val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray
        ) {
            GroceryListScreen(
                state = state,
                newGrocery = viewModel.newGrocery,
                onEvent = viewModel::onEvent,
                imagePicker = imagePicker
            )
        }
    }
}