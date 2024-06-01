import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.example.fridgital.core.presentation.ClearFocusOnTapOutside
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
    ClearFocusOnTapOutside {
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
}