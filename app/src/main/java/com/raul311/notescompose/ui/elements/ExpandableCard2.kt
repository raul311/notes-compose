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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ExpandableCard2(
    title: String,
    description: String,
) {
    var expanded by remember { mutableStateOf(false) }
    val modifier = if (expanded) {
        Modifier
            .fillMaxSize()
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
                title,
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )
            Text(
                description
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
fun ExpandableCardPreview2(title : String, description: String) {

    ExpandableCard2(
        title = title,
        description = description
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

