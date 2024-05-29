import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import org.example.fridgital.core.presentation.ImagePickerFactory
import org.example.fridgital.di.AppModule

fun MainViewController() = ComposeUIViewController {
    App(
        appModule = AppModule(),
        imagePicker = ImagePickerFactory(LocalUIViewController.current).createPicker()
    )
}