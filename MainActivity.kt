package com.example.animationstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF101522)
private val Panel = Color(0xFF1B2435)
private val Accent = Color(0xFF8792FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { StudioScreen() }
    }
}

@Composable
fun StudioScreen() {
    var story by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("5 minutes") }
    var language by remember { mutableStateOf("English + Urdu captions") }
    var status by remember { mutableStateOf("Enter a story to create your scene plan.") }
    var plan by remember { mutableStateOf<List<String>>(emptyList()) }

    MaterialTheme(colorScheme = darkColorScheme(
        background = Navy, surface = Panel, primary = Accent,
        onBackground = Color.White, onSurface = Color.White
    )) {
        Column(
            Modifier.fillMaxSize().background(Navy).verticalScroll(rememberScrollState()).padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("AI ANIMATION STUDIO", color = Accent, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text("Your story. Your animated movie.", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text("Mobile-first 3D movie planning studio", color = Color.LightGray)

            Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("STORY INPUT", color = Accent, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = story, onValueChange = { story = it },
                        modifier = Modifier.fillMaxWidth().height(170.dp),
                        label = { Text("Write your story") },
                        placeholder = { Text("A young inventor discovers a hidden city in the clouds...") },
                        minLines = 5
                    )
                    Text("MOVIE LENGTH", color = Accent, fontWeight = FontWeight.Bold)
                    ChoiceDropdown(duration, listOf("5 minutes", "10 minutes", "20 minutes", "30 minutes", "60 minutes")) { duration = it }
                    Text("LANGUAGE", color = Accent, fontWeight = FontWeight.Bold)
                    ChoiceDropdown(language, listOf("English + Urdu captions", "Urdu", "Hindi", "English")) { language = it }
                    Button(
                        onClick = {
                            if (story.isBlank()) {
                                status = "Please write a story first."
                                plan = emptyList()
                            } else {
                                val total = when (duration) {
                                    "5 minutes" -> 30
                                    "10 minutes" -> 60
                                    "20 minutes" -> 120
                                    "30 minutes" -> 180
                                    else -> 360
                                }
                                val excerpt = story.trim().take(500)
                                plan = listOf(
                                    "Project: $duration • $total scenes • about 10 seconds per scene",
                                    "Visual direction: cinematic 3D animated feature-film look",
                                    "Language: $language",
                                    "Story brief: $excerpt",
                                    "Scene 1 prompt starter: Establish the main character, location, time of day, cinematic lighting, wide establishing shot.",
                                    "Scene 2 prompt starter: Show the character's first important action, medium camera shot, consistent character design.",
                                    "Scene 3 prompt starter: Introduce the story's main challenge, dramatic camera movement and expressive animation.",
                                    "Production note: Connect a secure AI backend for real image/video generation, voice, and MP4 rendering."
                                )
                                status = "Scene-plan draft created. This prototype does not yet render video."
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Accent)
                    ) { Text("CREATE SCENE PLAN", color = Color.White, fontWeight = FontWeight.Bold) }
                }
            }

            Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    Text("PROJECT WORKSPACE", color = Accent, fontWeight = FontWeight.Bold)
                    Text(status)
                    plan.forEach { Text("• $it", color = Color(0xFFD0D7E5), fontSize = 13.sp) }
                }
            }
            Text("Version 0.2 • Scene planning prototype • AI video rendering requires backend integration.",
                color = Color(0xFF8994A8), fontSize = 11.sp)
        }
    }
}

@Composable
fun ChoiceDropdown(selected: String, options: List<String>, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Text(selected, modifier = Modifier.weight(1f))
            Text("⌄")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { item ->
                DropdownMenuItem(text = { Text(item) }, onClick = {
                    onSelect(item); expanded = false
                })
            }
        }
    }
}
