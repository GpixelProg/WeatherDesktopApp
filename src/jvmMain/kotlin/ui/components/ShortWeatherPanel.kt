package ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.api.model.CurrentWeather

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShortWeatherPanel(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather,
    selected: Boolean = false,
    onDelete: () -> Unit,
    onClick: (CurrentWeather) -> Unit,
) {
    ContextMenuArea(
        items = {
            listOf(
                ContextMenuItem(
                    label = "Delete",
                    onClick = onDelete,
                ),
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .onClick { onClick(currentWeather) },
            shape = RoundedCornerShape(5.dp),
            color = if (selected) Color.LightGray.copy(alpha = 0.5f) else Color.Transparent,
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = currentWeather.city,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(top = 3.dp)
                        )
                    }

                    Text(
                        text = currentWeather.temperature!!.toInt().toString() + "°",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White.copy(alpha = 0.5f),
                    )
                }

                Text(
                    text = currentWeather.weatherDescription,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                HorizontalLine(modifier = Modifier)
            }
        }
    }
}