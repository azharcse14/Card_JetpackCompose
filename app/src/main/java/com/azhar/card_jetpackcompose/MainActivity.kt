package com.azhar.card_jetpackcompose

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azhar.card_jetpackcompose.ui.theme.CardJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildCard()
                }
            }
        }
    }
}

@Composable
fun BuildCard() {
    Column {
        SimpleCard()
        CardWithElevation()
        CardWithRectangleShape()
        CardWithCircleShape()
        CardWithRoundedCornerShape()
        CardWithCutCornerShape()
        CardWithBorder()
        CardWithMultipleViews()
        ExpandableCard("Expandable Card", "ASD SASD ASDDFFD fszcf bhkjkbhj jhv", Color.Black)
    }
}

@Composable
fun SimpleCard() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(modifier = paddingModifier) {
        Text(
            text = "Simple Card",
            modifier = paddingModifier
        )
    }
}

@Composable
fun CardWithElevation() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ), modifier = paddingModifier
    ) {
        Text(
            text = "Card With Elevation",
            modifier = paddingModifier
        )
    }
}

@Composable
fun CardWithRectangleShape() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(shape = RectangleShape, modifier = paddingModifier) {
        Text(text = "Rectangle Shape", modifier = paddingModifier)
    }
}


@Composable
fun CardWithCircleShape() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),

        ) {
        Text(text = "Circle shape", modifier = paddingModifier, textAlign = TextAlign.Center)
    }
}

@Composable
fun CardWithRoundedCornerShape() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(shape = RoundedCornerShape(20.dp), modifier = paddingModifier) {
        Text(text = "Round corner shape", modifier = paddingModifier)
    }
}

@Composable
fun CardWithCutCornerShape() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(shape = CutCornerShape(20.dp), modifier = paddingModifier) {
        Text(text = "Cut Corner Shape", modifier = paddingModifier)
    }
}

@Composable
fun CardWithBorder() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        border = BorderStroke(1.dp, Color.Red),
        modifier = paddingModifier
    ) {
        Text(text = "Card with border", modifier = paddingModifier)
    }
}

@Composable
fun CardWithMultipleViews() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        modifier = paddingModifier
    ) {
        Column(modifier = paddingModifier) {
            Text(text = "First Text")
            Text(text = "Second Text")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    header: String, // header
    description: String, // description
    color: Color, // color
) {
    var expanded by remember { mutableStateOf(false) } // Expand State
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotation state of expand icon button",
    )
    val strokeState by animateDpAsState(
        targetValue = if (expanded) 2.dp else 1.dp,
        label = "Stroke width",
    )

    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(8.dp), // shape
        border = BorderStroke(strokeState, color), // stroke Width and Color
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize() // edit animation here
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // control the header alignment over here.
            ) {
                Text(
                    text = header,
                    color = color, // header color
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                )
                IconButton(
                    modifier = Modifier.rotate(rotationState),
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        tint = color, // Icon Color
                        contentDescription = "Drop Down Arrow"
                    )
                }
            }
            if (expanded) {
                Text(
                    text = description,
                    color = color, // description color
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardJetpackComposeTheme {
        BuildCard()
    }
}