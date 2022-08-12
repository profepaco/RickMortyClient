package edu.itsco.rickmortyclient.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
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
            CardPersonaje(character = character)
        }
    }
}

@Composable
fun PersonajeTarjeta(character: Character, modifier:Modifier = Modifier){

    //val imagePainter = rememberAsyncImagePainter(model = character.image)

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(all = 16.dp)
    ){
        Box{
            AsyncImage(
                //model = imagePainter
                model = character.image,
                contentDescription = "imagen RickMorty",
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )
            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = .2f),
                modifier = modifier.align(Alignment.BottomCenter)
            ){
                Row {
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
}

@Composable
fun CardPersonaje(
    character: Character,
    modifier: Modifier = Modifier
){

    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column() {
            Row(modifier.background(MaterialTheme.colors.secondary)) {
                Surface(
                    modifier.size(120.dp),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = "IMG RickMorty",
                        contentScale = ContentScale.FillBounds
                    )
                }
                Column(
                    modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = "${character.status} - ${character.species}",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = "Icono"
                    )
                }
            }
            if(expanded){
                Row(modifier.padding(16.dp)) {
                    Column() {
                        Text("Ultima aparición conocida:",
                            style = MaterialTheme.typography.body1
                        )
                        Text(character.location.name,
                            style = MaterialTheme.typography.body2
                        )
                        MasInformacionCard(item = "Visto por primera vez",
                            detalle = character.origin.name)
                    }
                }
            }
        }
    }
}

@Composable
fun MasInformacionCard(item: String, detalle: String){
    Column() {
        Text(item,
            style = MaterialTheme.typography.body1
        )
        Text(detalle,
            style = MaterialTheme.typography.body2
        )
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
    CardPersonaje(
        character = personaje
    )
}
