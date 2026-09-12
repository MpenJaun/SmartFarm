# SmartFarm — Kotlin Android

Aplikasi Android native untuk manajemen pertanian, dibuat dengan Kotlin + Jetpack Compose.

## Basis desain
UI mengikuti referensi desain pada PDF yang diberikan: login, dashboard, tanaman/varietas, deteksi penyakit, katalog hama & penyakit, monitoring, panen, aktivitas, jadwal, laporan, serta profil/pengaturan.

## Fitur yang sudah tersedia
- Login demo
- Register
- Lupa password (alur UI lokal/demo)
- Dashboard statistik
- Tanaman & varietas
- Lahan
- Jadwal & agenda
- Monitoring & timeline
- Deteksi penyakit AI (simulasi UI; belum terhubung model ML/backend)
- Katalog hama & penyakit
- Panen
- Riwayat aktivitas
- Laporan
- Profil & pengaturan
- Navigasi mobile

## Login demo
Email: `admin@smartfarm.test`
Password: `password`

## Membuka di GitHub
1. Buat repository baru bernama `SmartFarm`.
2. Upload seluruh isi folder ini.
3. Buka repository di Android Studio dengan **Get from VCS**.
4. Tunggu Gradle Sync.
5. Run pada emulator/HP Android.

## Catatan
PDF adalah acuan visual. PDF tidak menyediakan spesifikasi backend, API, model AI, atau database; karena itu bagian tersebut belum dianggap sebagai fakta dari desain dan saat ini dibuat sebagai UI lokal/demo.
