package com.smartfarm.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.smartfarm.ui.theme.FarmBg

enum class Screen(val route: String, val title: String) {
    Dashboard("dashboard","Dashboard"),
    Tanaman("tanaman","Tanaman"),
    Lahan("lahan","Lahan"),
    Jadwal("jadwal","Jadwal"),
    Monitoring("monitoring","Monitoring"),
    Deteksi("deteksi","Deteksi Penyakit AI"),
    Penyakit("penyakit","Hama & Penyakit"),
    Panen("panen","Panen"),
    Aktivitas("aktivitas","Riwayat Aktivitas"),
    Laporan("laporan","Laporan"),
    Profil("profil","Profil & Pengaturan")
}

@Composable
fun SmartFarmApp() {
    var loggedIn by remember { mutableStateOf(false) }
    var showRegister by remember { mutableStateOf(false) }
    var showForgot by remember { mutableStateOf(false) }

    if (!loggedIn) {
        LoginScreen(
            onLogin = { loggedIn = true },
            onRegister = { showRegister = true },
            onForgot = { showForgot = true }
        )
        if (showRegister) RegisterDialog(onDismiss={showRegister=false}, onSuccess={showRegister=false; loggedIn=true})
        if (showForgot) ForgotPasswordDialog(onDismiss={showForgot=false})
        return
    }

    val nav = rememberNavController()
    val backStack by nav.currentBackStackEntryAsState()
    val current = Screen.entries.firstOrNull { it.route == backStack?.destination?.route } ?: Screen.Dashboard

    Scaffold(
        containerColor = FarmBg,
        bottomBar = { BottomNav(current) { nav.navigate(it.route) { launchSingleTop=true; restoreState=true } } }
    ) { pad ->
        NavHost(navController=nav, startDestination=Screen.Dashboard.route, modifier=Modifier.padding(pad)) {
            composable(Screen.Dashboard.route) { DashboardScreen(nav) }
            composable(Screen.Tanaman.route) { TanamanScreen() }
            composable(Screen.Lahan.route) { LahanScreen() }
            composable(Screen.Jadwal.route) { JadwalScreen() }
            composable(Screen.Monitoring.route) { MonitoringScreen() }
            composable(Screen.Deteksi.route) { DeteksiScreen() }
            composable(Screen.Penyakit.route) { PenyakitScreen() }
            composable(Screen.Panen.route) { PanenScreen() }
            composable(Screen.Aktivitas.route) { AktivitasScreen() }
            composable(Screen.Laporan.route) { LaporanScreen() }
            composable(Screen.Profil.route) { ProfilScreen(onLogout={loggedIn=false}) }
        }
    }
}

@Composable
private fun BottomNav(current: Screen, onClick:(Screen)->Unit) {
    val items = listOf(Screen.Dashboard, Screen.Tanaman, Screen.Monitoring, Screen.Panen, Screen.Profil)
    NavigationBar {
        items.forEach { s ->
            val icon = when(s) {
                Screen.Dashboard -> Icons.Default.Home
                Screen.Tanaman -> Icons.Default.Spa
                Screen.Monitoring -> Icons.Default.Timeline
                Screen.Panen -> Icons.Default.Agriculture
                else -> Icons.Default.Person
            }
            NavigationBarItem(selected=current==s, onClick={onClick(s)}, icon={Icon(icon,null)}, label={Text(s.title)})
        }
    }
}
