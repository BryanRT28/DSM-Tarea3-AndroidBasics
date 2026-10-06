package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

data class ArtItem(
    @DrawableRes val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val collection = listOf(
        ArtItem(R.drawable.artwork_1, "La noche estrellada", "Vincent van Gogh", "1889"),
        ArtItem(R.drawable.artwork_2, "La Gioconda", "Leonardo da Vinci", "1503"),
        ArtItem(R.drawable.artwork_3, "El grito", "Edvard Munch", "1893")
    )

    var activeIndex by remember { mutableIntStateOf(0) }
    val currentItem = collection[activeIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // 1. Marco de la obra (Muro)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(4.dp)),
            color = Color.White
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = currentItem.imageRes),
                    contentDescription = currentItem.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Ficha descriptiva
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFECEFF1), shape = RoundedCornerShape(4.dp))
                .padding(16.dp)
        ) {
            Text(
                text = currentItem.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )
            Row(modifier = Modifier.padding(top = 4.dp)) {
                Text(
                    text = currentItem.artist,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = " (${currentItem.year})",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // 3. Botones de navegación (Anterior / Siguiente)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    activeIndex = if (activeIndex == 0) collection.lastIndex else activeIndex - 1
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.padding(horizontal = 12.dp))

            Button(
                onClick = {
                    activeIndex = if (activeIndex == collection.lastIndex) 0 else activeIndex + 1
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}