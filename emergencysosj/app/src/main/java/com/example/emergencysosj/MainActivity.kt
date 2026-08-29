package com.example.emergencysosj
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.width
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emergencysosj.ui.theme.EmergencySosJTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            EmergencySosJTheme {
                EmergencyResponderApp()
            }
        }
    }
}

enum class AppScreen {
    HOME,
    CONTACTS,
    LOCATION,
    HISTORY
}

@Composable
fun EmergencyResponderApp() {

    var currentScreen by rememberSaveable {
        mutableStateOf(AppScreen.HOME)
    }

    var showSosDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = currentScreen == AppScreen.HOME,
                    onClick = {
                        currentScreen = AppScreen.HOME
                    },
                    icon = {
                        Text("🏠")
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.CONTACTS,
                    onClick = {
                        currentScreen = AppScreen.CONTACTS
                    },
                    icon = {
                        Text("👥")
                    },
                    label = {
                        Text("Contacts")
                    }
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.LOCATION,
                    onClick = {
                        currentScreen = AppScreen.LOCATION
                    },
                    icon = {
                        Text("📍")
                    },
                    label = {
                        Text("Location")
                    }
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.HISTORY,
                    onClick = {
                        currentScreen = AppScreen.HISTORY
                    },
                    icon = {
                        Text("📜")
                    },
                    label = {
                        Text("History")
                    }
                )
            }
        }
    ) { paddingValues ->

        when (currentScreen) {

            AppScreen.HOME -> {
                HomeScreen(
                    modifier = Modifier.padding(paddingValues),
                    onSosClick = {
                        showSosDialog = true
                    }
                )
            }

            AppScreen.CONTACTS -> {
                ContactsScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            AppScreen.LOCATION -> {
                LocationScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            AppScreen.HISTORY -> {
                HistoryScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }

    if (showSosDialog) {

        AlertDialog(

            onDismissRequest = {
                showSosDialog = false
            },

            title = {
                Text(
                    text = "🚨 Emergency SOS",
                    fontWeight = FontWeight.Bold
                )
            },

            text = {
                Text(
                    text = "Are you sure you want to activate an emergency SOS alert?"
                )
            },

            confirmButton = {

                Button(
                    onClick = {
                        showSosDialog = false
                    }
                ) {
                    Text("SEND SOS")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showSosDialog = false
                    }
                ) {
                    Text("CANCEL")
                }
            }
        )
    }
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSosClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "🛡️",
            fontSize = 45.sp
        )

        Text(
            text = "EMERGENCY RESPONDER",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Your Safety, Our Priority",
            fontSize = 14.sp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "🛡️ YOU ARE PROTECTED",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Emergency help is one tap away."
                )
            }
        }

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        Box(
            modifier = Modifier
                .size(170.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = CircleShape
                )
                .background(
                    color = Color.Red,
                    shape = CircleShape
                )
                .padding(12.dp),

            contentAlignment = Alignment.Center
        ) {

            Button(
                onClick = onSosClick,

                modifier = Modifier.size(140.dp),

                shape = CircleShape
            ) {

                Text(
                    text = "SOS",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = "TAP FOR EMERGENCY",
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "📍 Current Location",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Location will be available here"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SmallCard(
                emoji = "👥",
                title = "Contacts",
                modifier = Modifier.weight(1f)
            )

            SmallCard(
                emoji = "📍",
                title = "Location",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                text = "📜  Alert History",
                modifier = Modifier.padding(18.dp),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun SmallCard(
    emoji: String,
    title: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.height(90.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = emoji,
                fontSize = 25.sp
            )

            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Emergency Contacts",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(5.dp)
        )

        Text(
            text = "People who will receive your emergency alerts."
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        ContactCard(
            name = "Mom",
            number = "+91 XXXXX XXXXX"
        )

        ContactCard(
            name = "Dad",
            number = "+91 XXXXX XXXXX"
        )

        ContactCard(
            name = "Best Friend",
            number = "+91 XXXXX XXXXX"
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("+ ADD CONTACT")
        }
    }
}


@Composable
fun ContactCard(
    name: String,
    number: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),

        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier.padding(18.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "👤",
                fontSize = 30.sp
            )

            Spacer(
                modifier = Modifier.width(15.dp)
            )

            Column {

                Text(
                    text = name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = number
                )
            }
        }
    }
}


@Composable
fun LocationScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "My Location",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Your current location"
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),

            shape = RoundedCornerShape(18.dp)
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),

                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "📍",
                        fontSize = 60.sp
                    )

                    Text(
                        text = "MAP AREA",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Map will be connected later"
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Latitude: --"
        )

        Text(
            text = "Longitude: --"
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("REFRESH LOCATION")
        }
    }
}


@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Alert History",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        HistoryCard(
            date = "29 Aug 2026 • 10:30 PM",
            status = "Sent"
        )

        HistoryCard(
            date = "27 Aug 2026 • 08:15 PM",
            status = "Cancelled"
        )

        HistoryCard(
            date = "24 Aug 2026 • 06:42 PM",
            status = "Sent"
        )
    }
}


@Composable
fun HistoryCard(
    date: String,
    status: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),

        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "🚨 SOS Alert",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = date
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Status: $status",
                fontWeight = FontWeight.Bold
            )
        }
    }
}