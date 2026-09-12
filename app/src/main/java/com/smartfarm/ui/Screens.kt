package com.smartfarm.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.smartfarm.ui.theme.*

@Composable
fun LoginScreen(onLogin:()->Unit, onRegister:()->Unit, onForgot:()->Unit) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    Box(Modifier.fillMaxSize().background(FarmGreen), contentAlignment=Alignment.Center) {
        Card(Modifier.fillMaxWidth().padding(24.dp), shape=RoundedCornerShape(26.dp)) {
            Column(Modifier.padding(24.dp), horizontalAlignment=Alignment.CenterHorizontally) {
                Text("🌱", style=MaterialTheme.typography.displayMedium)
                Text("SmartFarm", style=MaterialTheme.typography.headlineLarge, fontWeight=FontWeight.Bold, color=FarmGreen)
                Text("Kelola pertanian lebih cerdas", color=Color.Gray)
                Spacer(Modifier.height(28.dp))
                OutlinedTextField(email,{email=it},label={Text("Email")},modifier=Modifier.fillMaxWidth(),singleLine=true)
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(pass,{pass=it},label={Text("Password")},modifier=Modifier.fillMaxWidth(),singleLine=true)
                Row(Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.End) {
                    TextButton(onClick=onForgot){Text("Lupa password?")}
                }
                Button(onClick=onLogin,modifier=Modifier.fillMaxWidth(),shape=RoundedCornerShape(12.dp)) {Text("Masuk",fontWeight=FontWeight.Bold)}
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment=Alignment.CenterVertically) {
                    Text("Belum punya akun? ")
                    TextButton(onClick=onRegister){Text("Daftar",fontWeight=FontWeight.Bold)}
                }
                Text("Demo: admin@smartfarm.test / password", style=MaterialTheme.typography.labelSmall, color=Color.Gray)
            }
        }
    }
}

@Composable fun RegisterDialog(onDismiss:()->Unit,onSuccess:()->Unit) {
    var name by remember{mutableStateOf("")}; var email by remember{mutableStateOf("")}; var p1 by remember{mutableStateOf("")}; var p2 by remember{mutableStateOf("")}
    AlertDialog(onDismissRequest=onDismiss,title={Text("Buat Akun SmartFarm")},text={
        Column(verticalArrangement=Arrangement.spacedBy(8.dp)){
            OutlinedTextField(name,{name=it},label={Text("Nama")},singleLine=true)
            OutlinedTextField(email,{email=it},label={Text("Email")},singleLine=true)
            OutlinedTextField(p1,{p1=it},label={Text("Password")},singleLine=true)
            OutlinedTextField(p2,{p2=it},label={Text("Konfirmasi Password")},singleLine=true)
        }
    },confirmButton={Button(onClick=onSuccess,enabled=name.isNotBlank()&&email.isNotBlank()&&p1.isNotBlank()&&p1==p2){Text("Daftar")}},dismissButton={TextButton(onClick=onDismiss){Text("Batal")}})
}
@Composable fun ForgotPasswordDialog(onDismiss:()->Unit) {
    var email by remember{mutableStateOf("")}; var sent by remember{mutableStateOf(false)}
    AlertDialog(onDismissRequest=onDismiss,title={Text("Lupa Password")},text={
        Column{Text(if(sent) "Instruksi reset password dikirim untuk $email." else "Masukkan email akun SmartFarm Anda.")
            if(!sent) OutlinedTextField(email,{email=it},label={Text("Email")},singleLine=true)}
    },confirmButton={Button(onClick={sent=true},enabled=email.isNotBlank()){Text(if(sent)"Terkirim" else "Kirim")}},dismissButton={TextButton(onClick=onDismiss){Text("Tutup")}})
}

@Composable
fun PageHeader(title:String, subtitle:String?=null) {
    Column(Modifier.fillMaxWidth().padding(horizontal=16.dp,vertical=14.dp)){
        Text("SMARTFARM", style=MaterialTheme.typography.labelSmall, color=FarmGreen, fontWeight=FontWeight.Bold)
        Text(title, style=MaterialTheme.typography.headlineSmall, fontWeight=FontWeight.Bold)
        subtitle?.let{Text(it,color=Color.Gray)}
    }
}
@Composable fun StatCard(label:String,value:String,caption:String,modifier:Modifier=Modifier) {
    Card(modifier,shape=RoundedCornerShape(16.dp)){Column(Modifier.padding(15.dp)){Text(label,color=Color.Gray,style=MaterialTheme.typography.labelMedium);Text(value,style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.Bold);Text(caption,color=FarmGreen,style=MaterialTheme.typography.labelSmall)}}
}
@Composable fun SectionCard(title:String,content:@Composable ColumnScope.()->Unit){
    Card(Modifier.fillMaxWidth().padding(horizontal=16.dp,vertical=6.dp),shape=RoundedCornerShape(16.dp)){Column(Modifier.padding(16.dp)){Text(title,fontWeight=FontWeight.Bold,style=MaterialTheme.typography.titleMedium);Spacer(Modifier.height(10.dp));content()}}
}
@Composable fun Chip(text:String, positive:Boolean=true){Surface(shape=RoundedCornerShape(50),color=if(positive)FarmGreenSoft else Color(0xFFFFE7E7)){Text(text,Modifier.padding(horizontal=10.dp,vertical=5.dp),color=if(positive)FarmGreen else FarmRed,style=MaterialTheme.typography.labelSmall,fontWeight=FontWeight.Bold)}}

@Composable
fun DashboardScreen(nav:NavHostController) {
    LazyColumn {
        item { PageHeader("Dashboard Utama","Ringkasan operasional dan kesehatan pertanian hari ini.") }
        item { Row(Modifier.padding(horizontal=12.dp),horizontalArrangement=Arrangement.spacedBy(8.dp)){StatCard("Total Tanaman","12.450","▲ 8,4%",Modifier.weight(1f));StatCard("Total Lahan","24 Ha","2 sektor aktif",Modifier.weight(1f))} }
        item { Row(Modifier.padding(horizontal=12.dp),horizontalArrangement=Arrangement.spacedBy(8.dp)){StatCard("Tanaman Perlu Perhatian","142","12% dari total",Modifier.weight(1f));StatCard("Estimasi Panen","8,5 T","▲ 6,4%",Modifier.weight(1f))} }
        item { SectionCard("Perkembangan Pertumbuhan"){Text("Tren tinggi tanaman",color=Color.Gray);Spacer(Modifier.height(10.dp));GrowthChart()}}
        item { SectionCard("Prediksi & Distribusi Hasil Panen"){Row(horizontalArrangement=Arrangement.spacedBy(12.dp)){Column(Modifier.weight(1f)){Text("Sektor A — Tomat");Text("9,2 T",fontWeight=FontWeight.Bold);Text("Prediksi panen")};Column(Modifier.weight(1f)){Text("Mutu Grade A");Text("94,2%",fontWeight=FontWeight.Bold,color=FarmGreen)}}}}
        item { SectionCard("Akses Cepat"){val list=listOf(Screen.Tanaman,Screen.Lahan,Screen.Jadwal,Screen.Monitoring,Screen.Deteksi,Screen.Penyakit,Screen.Panen,Screen.Aktivitas,Screen.Laporan); list.chunked(3).forEach{row->Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){row.forEach{s->OutlinedButton(onClick={nav.navigate(s.route)},modifier=Modifier.weight(1f)){Text(s.title,style=MaterialTheme.typography.labelSmall)}}};Spacer(Modifier.height(6.dp))}}}
        item { SectionCard("Telemetri Lingkungan & Sensor IoT"){Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceEvenly){MiniMetric("Kelembapan","68%");MiniMetric("Suhu","26,4°C");MiniMetric("Cahaya","850");MiniMetric("pH","6,5")}}}
    }
}
@Composable fun MiniMetric(a:String,b:String){Column(horizontalAlignment=Alignment.CenterHorizontally){Text(a,style=MaterialTheme.typography.labelSmall,color=Color.Gray);Text(b,fontWeight=FontWeight.Bold)}}
@Composable fun GrowthChart(){Row(Modifier.fillMaxWidth().height(130.dp),verticalAlignment=Alignment.Bottom,horizontalArrangement=Arrangement.SpaceEvenly){listOf(25,42,56,70,83,95).forEachIndexed{i,h->Column(horizontalAlignment=Alignment.CenterHorizontally){Box(Modifier.width(26.dp).height(h.dp).background(FarmGreen, RoundedCornerShape(topStart=5.dp,topEnd=5.dp)));Text("B${i+1}",style=MaterialTheme.typography.labelSmall)}}}}

@Composable fun TanamanScreen(){val data=listOf("Tomat Beefsteak","Selada Romaine","Paprika Kuning","Cabai Rawit","Mentimun","Stroberi");GenericListPage("Daftar Tanaman & Varietas","Kelola data varietas tanaman dan parameter pertumbuhannya.",data)}
@Composable fun LahanScreen(){val data=listOf("Sektor A — 8 Ha","Sektor B — 6 Ha","Sektor C — 5 Ha","Sektor D — 5 Ha");GenericListPage("Lahan","Daftar sektor lahan dan status operasional.",data)}
@Composable fun JadwalScreen(){val data=listOf("Penyiraman — Sektor A","Pemupukan NPK — Cabai","Monitoring pH — Sektor B","Pengendalian hama — Tomat");GenericListPage("Jadwal & Agenda Kebun","Jadwal operasional dan agenda pekerjaan hari ini.",data)}
@Composable fun MonitoringScreen(){GenericListPage("Monitoring & Timeline Agronomi","Pantau pertumbuhan, kondisi lahan, dan catatan inspeksi.",listOf("Inspeksi Sektor A — Tinggi 45 cm","Inspeksi Sektor B — Tinggi 40 cm","Catatan sensor — Kelembapan 68%","Evaluasi kesehatan tanaman"))}
@Composable fun PenyakitScreen(){GenericListPage("Katalog Hama & Penyakit Tanaman","Identifikasi gejala, penyebab, tindakan kuratif, dan pencegahan.",listOf("Hawar Daun Dini","Kutu Kebul","Busuk Lunak Bakteri","Antraknosa (Patek)","Virus Kuning Gemini","Busuk Pantat Buah (BER)"))}
@Composable fun PanenScreen(){GenericListPage("Rekapitulasi & Hasil Panen","Produktivitas, mutu, realisasi target, dan riwayat panen.",listOf("Tomat — 1.450 kg — Grade A","Selada — 850 kg — Grade A","Paprika — 1.120 kg — Grade B","Cabai — 990 kg — Grade A"))}
@Composable fun AktivitasScreen(){GenericListPage("Riwayat Aktivitas & Log Kebun","Catat dan telusuri kegiatan operasional pertanian.",listOf("Penyemprotan pestisida","Pemupukan tanaman","Monitoring kelembapan","Panen Sektor A","Penjadwalan ulang irigasi"))}
@Composable fun LaporanScreen(){GenericListPage("Laporan Agrikultur Terpadu","Ringkasan produksi, mutu, dan rekomendasi evaluasi.",listOf("Laporan produksi bulanan","Evaluasi mutu hasil panen","Efisiensi penggunaan air","Kepatuhan SOP agrikultur"))}

@Composable fun GenericListPage(title:String,subtitle:String,items:List<String>){
    LazyColumn{item{PageHeader(title,subtitle)};item{Row(Modifier.padding(horizontal=16.dp),horizontalArrangement=Arrangement.spacedBy(8.dp)){StatCard("Total Data",items.size.toString(),"Aktif",Modifier.weight(1f));StatCard("Status","Aktif","Terpantau",Modifier.weight(1f))}};items.forEachIndexed{i,x->item{SectionCard(x){Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween,verticalAlignment=Alignment.CenterVertically){Column(Modifier.weight(1f)){Text(if(i%3==0)"Status operasional" else "Data terpantau",color=Color.Gray);Text("Pembaruan terbaru hari ini",style=MaterialTheme.typography.bodySmall)};Chip(if(i%3==0)"Baik" else "Normal")}}}}}
}

@Composable fun DeteksiScreen(){
    var analyzed by remember{mutableStateOf(false)}
    LazyColumn{item{PageHeader("Deteksi Penyakit Tanaman Berbasis AI","Unggah sampel daun untuk mendapatkan hasil diagnosis dan rekomendasi.")};item{SectionCard("Input Sampel"){Box(Modifier.fillMaxWidth().height(180.dp).background(FarmGreenSoft,RoundedCornerShape(14.dp)),contentAlignment=Alignment.Center){Column(horizontalAlignment=Alignment.CenterHorizontally){Icon(Icons.Default.AddPhotoAlternate,null,tint=FarmGreen,modifier=Modifier.size(48.dp));Text("Ambil / pilih foto daun",fontWeight=FontWeight.Bold);Text("JPG, PNG hingga 10 MB",color=Color.Gray)}};Spacer(Modifier.height(12.dp));Button(onClick={analyzed=true},Modifier.fillMaxWidth()){Text("Mulai Analisis AI")}}};if(analyzed)item{SectionCard("Hasil Diagnosis AI"){Text("Hawar Daun Dini",style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.Bold);Chip("94,5% Akurasi Prediksi");Spacer(Modifier.height(10.dp));Text("Rekomendasi tindakan: lakukan sanitasi daun terinfeksi dan perbaiki sirkulasi udara. Gunakan perlakuan sesuai SOP kebun.");Spacer(Modifier.height(10.dp));Button(onClick={}){Text("Simpan ke Riwayat Penyakit")}}}}
}

@Composable fun ProfilScreen(onLogout:()->Unit){
    LazyColumn{item{PageHeader("Profil & Pengaturan Akun","Kelola informasi profil, keamanan, dan preferensi operasional.")};item{SectionCard("Informasi Profil"){Text("John Doe Pratama",fontWeight=FontWeight.Bold,style=MaterialTheme.typography.titleLarge);Text("Farm Manager",color=FarmGreen);Text("john@smartfarm.test",color=Color.Gray)}};item{SectionCard("Keamanan Akun"){Text("Password terakhir diperbarui: 30 hari lalu");Text("Autentikasi dua faktor: Aktif",color=FarmGreen);Spacer(Modifier.height(10.dp));OutlinedButton(onClick={}){Text("Ubah Password")}}};item{SectionCard("Preferensi Operasional"){Text("Notifikasi jadwal: Aktif");Text("Peringatan sensor: Aktif");Text("Laporan mingguan: Aktif")}};item{Button(onClick=onLogout,modifier=Modifier.fillMaxWidth().padding(16.dp),colors=ButtonDefaults.buttonColors(containerColor=FarmRed)){Text("Keluar")}}}
}
