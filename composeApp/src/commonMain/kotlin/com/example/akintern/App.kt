package com.example.akintern

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    val breaches = remember { mutableStateListOf<Breach>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val fetchedBreaches = Api.getBreaches()
            breaches.addAll(fetchedBreaches)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Breaches List") })
        }
    ) {
        if (breaches.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(breaches) { breach ->
                    BreachCard(breach)
                }
            }
        }
    }
}

@Composable
fun BreachCard(breach: Breach) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = breach.Title, style = MaterialTheme.typography.h6)
            Text(text = breach.Domain, style = MaterialTheme.typography.subtitle1)
            Text(text = "Breach Date: ${breach.BreachDate}", style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = breach.Description, style = MaterialTheme.typography.body2)
        }
    }
}