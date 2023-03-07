
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.challenge.pixabay.R

val Cabin = FontFamily(
    Font(R.font.cabin_regular, FontWeight.Normal),
    Font(R.font.cabin_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(

    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Cabin,
        fontSize = 30.sp
    ),

    displayMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = Cabin,
        fontSize = 20.sp
    ),

    displaySmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = Cabin,
        fontSize = 20.sp
    ),

    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Cabin,
        fontSize = 16.sp
    )
)