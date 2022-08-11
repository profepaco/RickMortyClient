package edu.itsco.rickmortyclient.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import edu.itsco.rickmortyclient.data.api.model.Character
import edu.itsco.rickmortyclient.data.api.model.Location
import edu.itsco.rickmortyclient.data.api.model.Origin

@Composable
fun PersonajesScreen(){

    val personajesViewModel = viewModel(modelClass = PersonajesViewModel::class.java)
    val state by personajesViewModel.state.collectAsState()

    //Es el equivalente a RecyclerView vertical
    LazyColumn {
        items(items = state) { character: Character ->
            PersonajeTarjeta(character = character)
        }
    }
}

@Composable
fun PersonajeTarjeta(character: Character, modifier:Modifier = Modifier){

    val imagePainter = rememberAsyncImagePainter(model = character.image)

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(all = 16.dp)
    ){
        Box{
            AsyncImage(
                //model = character.image,
                model = imagePainter,
                contentDescription = "imagen RickMorty",
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )
            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = .1f),
                modifier = modifier.align(Alignment.BottomCenter)
            ){
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                ) {
                    Text(character.name)
                    Text("${character.status} - ${character.species}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TarjetaPersonajePreview(){
    val personaje = Character(
        name="Rick",
        status = "Alive",
        species = "Human",
        created = "",
        episode = emptyList<String>(),
        gender = "",
        id = 1,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        location = Location("",""),
        origin = Origin("",""),
        type = "",
        url = ""
    )
    PersonajeTarjeta(
        character = personaje
    )
}
