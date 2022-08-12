package edu.itsco.rickmortyclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import edu.itsco.rickmortyclient.ui.home.PersonajesScreen
import edu.itsco.rickmortyclient.ui.theme.RickMortyClientTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyClientTheme {
                Scaffold(
                    topBar = {
                        TopAppBar {
                            Text("Rick and Morty")
                        }
                    }
                ) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.onSurface.copy(alpha = .2f)
                    ) {
                        PersonajesScreen()
                    }
                }
            }
        }
    }
}

