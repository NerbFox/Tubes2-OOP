# Tugas Besar 2 IF - 2022/2023

Tugas Besar 2: Manajemen Usaha BNMO
IF2210 Pemrograman Berorientasi Objek


## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
* [How to Run](#how-to-run)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Authors](#authors)


## General Information
### Manajemen Usaha BNMO

Setelah gagal pada bisnis mereka sebelumnya, Indra dan Doni ingin membuka bisnis baru 
yang mencakup banyak product seperti restoran makanan, kafe, toko DIY, apotik dan 
sebagainya. Untuk menopang bisnis mereka ini, mereka ingin menggunakan BNMO sebagai 
sistem POS (Point of Sales). Akan tetapi, BNMO belum memiliki fitur POS yang mereka 
inginkan. Oleh karena itu, Indra dan Doni ingin meminta kalian, mahasiswa IF’ 21, 
untuk menambahkan fitur baru pada BNMO agar mereka dapat menjalankan bisnis baru 
mereka dengan lancar.


Program pada Tugas Besar 2 ini adalah program POS (Point of Sales), yaitu program yang 
membantu sebuah toko untuk melakukan dan mencatat transaksi yang berhubungan dengan usaha 
mereka. Program POS ini memiliki fitur dasar manajemen inventaris dan manajemen transaksi. 
Selain itu, program memiliki fitur membership agar toko dapat memberikan reward kepada 
pelanggan yang setia, dan juga fitur pembuatan laporan untuk mendukung toko dalam melakukan 
evaluasi. Program juga bersifat extensible dengan menyediakan dukungan plugin, sehingga 
pengguna dapat menambahkan fungsionalitas program dengan mudah.


## Technologies Used
- Java 1.8_202u 
- Maven 3.8.1 (untuk build program)

## Features
List the ready features here:
- Menu Kasir
- Manajemen Barang
- Detail Pelanggan
- Settings
- History transaksi
- Plugins (Charts, currency, discounts)


## How to Run 

Untuk build program, gunakan command berikut.
```
mvn clean install
```

Untuk menjalankan program, execute file jar yang ada pada folder target. Di bawah ini merupakan command yang diexecute dari folder root.
```
java -jar target/Tubes2-OOP-1.0-SNAPSHOT-jar-with-dependencies.jar
```


## Authors
1. 13521041 / Muhammad Hanan
2. 13521043 / Nigel Sahl
3. 13521109 / Rizky Abdillah Rasyid
4. 13521121 / Saddam Annais Shaquille
5. 13521157 / Hanif Muhammad Zhafran
