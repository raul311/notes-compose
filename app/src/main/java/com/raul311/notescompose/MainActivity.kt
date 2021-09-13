package com.raul311.notescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raul311.notescompose.ui.theme.NotesComposeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntSize
import com.raul311.notescompose.models.NotesRepo
import com.raul311.notescompose.ui.elements.ExpandableCardPreview2

class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val notes = NotesRepo.getNotes()

                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2)
                    ) {
                        items(notes.size) {
                            val note = notes[it]
                            ExpandableCardPreview2(
                                note.title,
                                note.data
                            )
//                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                                val note = notes[it]
////                                Text(text = "Number")
////                                Text(text = "  $it",)
//                                ExpandableCardPreview2(
//                                    note.title,
//                                    note.data
//                                )
//                            }
                        }
                    }
                }
            }
        }
    }

}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun Greeting() {
    var expanded by remember { mutableStateOf(false) }
    val modifier = if (expanded) {
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(15.dp)
            .clickable {
                expanded = !expanded
            }
    } else {
        Modifier
            .width(150.dp)
            .height(300.dp)
            .padding(15.dp)
            .clickable { expanded = !expanded }
    }
    Card(
        modifier,
        elevation = 10.dp,

    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("Title to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                    ) {
                        append("First Note")
                    }
                },
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )
            Text(
                buildAnnotatedString {
                    append("This is the body of the ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                        append("Note")
                    }
                    append(" section")
                }
            )
        }
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform() { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
//            Greeting(targetExpanded)
//            if (targetExpanded) {
//                Greeting()
//            } else {
//                Greeting()
//            }
        }
    }
}

@Preview(showBackground = true)
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun DefaultPreview() {
    NotesComposeTheme {
//        Greeting()
        DefaultAnimation()
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun DefaultAnimation() {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colors.primary,
        onClick = { expanded = !expanded }
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform() { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            Greeting()
//            if (targetExpanded) {
//                Greeting()
//            } else {
//                Greeting()
//            }
        }
    }

}
