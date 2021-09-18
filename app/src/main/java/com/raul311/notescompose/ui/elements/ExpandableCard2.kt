package com.raul311.notescompose.ui.elements

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.raul311.notescompose.models.Note

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ExpandableCard2(
    note : Note,
    onNoteClicked : OnNoteClicked
) {
    var expanded by remember { mutableStateOf(false) }
    if (expanded) {
//        FullScreenNote(note)
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .clickable {
                onNoteClicked(note)
            },
        elevation = 10.dp,

        ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(
                note.title,
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )
            Text(
                note.data
            )
        }
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(10000, 10000, LinearOutSlowInEasing)) with
                        fadeOut(animationSpec = tween(1000)) using
                        SizeTransform() { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 1000
                                    durationMillis = 10000
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 1000
                                    durationMillis = 10000
                                }
                            }
                        }
            }
        ) {}
    }
}


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ExpandableCardPreview2(
    note : Note,
    onNoteClicked : OnNoteClicked
) {

    ExpandableCard2(
        note,
        onNoteClicked
    )

//    ExpandableCard2(
//        title = "Title to first note",
//        description = "This is the body of the note section"
//    )
//
//    ExpandableCard2(
//        title = "My Title",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
//                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
//                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//                "ullamco laboris nisi ut aliquip ex ea commodo consequat."
//    )
//
//    ExpandableCard2(
//        title = "Title extra note",
//        description = "This is the body of the note section"
//    )
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun OpenFullScreen(note : Note) {
    FullScreenNote(note)
}
