import java.util.Scanner;

public class ManajemenKeuanganMahasiswa {
    static Scanner input = new Scanner(System.in);
    static double saldo = 0;
    static double totalPemasukan = 0;
    static double totalPengeluaran = 0;
    static String[] riwayat = new String[100];
    static int jumlahRiwayat = 0;

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("           MANAJEMEN KEUANGAN MAHASISWA");
        System.out.println("                  KELOMPOK 10");
        System.out.println("==================================================");
        System.out.println("ANGGOTA KELOMPOK:");
        System.out.println("1. Robi Mulia Santoso - 21120125120040");
        System.out.println("2. Nikita Tesalonika Pandjaitan - 21120125120038");
        System.out.println("3. Annida Fauziatunn Isa - 21120125120037");
        System.out.println("4. Haifa Dhiyanata Aqila - 21120125120039");
        System.out.println("==================================================");
        System.out.println();

        jalankanProgram();
    }

    static void jalankanProgram() {
        int pilihan;

        do {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Tambah Pemasukan");
            System.out.println("2. Tambah Pengeluaran");
            System.out.println("3. Lihat Saldo");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Lihat Laporan Keuangan");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");

            pilihan = input.nextInt();
            input.nextLine();

            if (pilihan == 1) {
                tambahPemasukan();
            } else if (pilihan == 2) {
                tambahPengeluaran();
            } else if (pilihan == 3) {
                tampilkanSaldo();
            } else if (pilihan == 4) {
                tampilkanRiwayat();
            } else if (pilihan == 5) {
                tampilkanLaporan();
            } else if (pilihan == 6) {
                System.out.println("Terima kasih telah menggunakan aplikasi!");
            } else {
                System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 6);
    }

    static String tentukanKategori(int kode) {
        if (kode == 1) {
            return "Makanan & Minuman";
        } else if (kode == 2) {
            return "Transportasi";
        } else if (kode == 3) {
            return "Akademik";
        } else if (kode == 4) {
            return "Hiburan";
        } else {
            return "Lainnya";
        }
    }

    static double hitungRasioKeuangan() {
        if (totalPemasukan > 0) {
            return (totalPengeluaran / totalPemasukan) * 100;
        }
        return 0;
    }

    static boolean cekSaldoCukup(double jumlah) {
        return jumlah <= saldo;
    }

    static String formatTransaksi(String jenis, double jumlah, String kategori, String keterangan) {
        return jenis + " | Rp " + jumlah + " | " + kategori + " | " + keterangan;
    }

    static void tambahPemasukan() {
        System.out.print("Jumlah pemasukan: Rp ");
        double jumlah = input.nextDouble();
        input.nextLine();

        System.out.print("Sumber pemasukan: ");
        String sumber = input.nextLine();

        System.out.print("Keterangan: ");
        String keterangan = input.nextLine();

        saldo += jumlah;
        totalPemasukan += jumlah;

        String transaksi = formatTransaksi("PEMASUKAN", jumlah, sumber, keterangan);
        riwayat[jumlahRiwayat] = transaksi;
        jumlahRiwayat++;

        System.out.println("✓ Pemasukan berhasil ditambahkan!");
    }

    static void tambahPengeluaran() {
        System.out.print("Jumlah pengeluaran: Rp ");
        double jumlah = input.nextDouble();
        input.nextLine();

        boolean cukup = cekSaldoCukup(jumlah);
        if (!cukup) {
            System.out.println("✗ Saldo tidak cukup! Saldo saat ini: Rp " + saldo);
            return;
        }

        System.out.println("Kategori pengeluaran:");
        System.out.println("1. Makanan & Minuman");
        System.out.println("2. Transportasi");
        System.out.println("3. Akademik");
        System.out.println("4. Hiburan");
        System.out.println("5. Lainnya");
        System.out.print("Pilih kategori (1-5): ");
        int kategori = input.nextInt();
        input.nextLine();

        String namaKategori = tentukanKategori(kategori);

        System.out.print("Keterangan: ");
        String keterangan = input.nextLine();

        saldo -= jumlah;
        totalPengeluaran += jumlah;

        String transaksi = formatTransaksi("PENGELUARAN", jumlah, namaKategori, keterangan);
        riwayat[jumlahRiwayat] = transaksi;
        jumlahRiwayat++;

        System.out.println("✓ Pengeluaran berhasil dicatat!");
    }

    static void tampilkanSaldo() {
        System.out.println("\n=== SALDO SAAT INI ===");
        System.out.println("Saldo: Rp " + saldo);

        if (saldo < 50000) {
            System.out.println("⚠  Peringatan: Saldo rendah!");
        }
    }

    static void tampilkanRiwayat() {
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");

        if (jumlahRiwayat == 0) {
            System.out.println("Belum ada transaksi");
            return;
        }

        for (int i = 0; i < jumlahRiwayat; i++) {
            System.out.println((i+1) + ". " + riwayat[i]);
        }
    }

    static void tampilkanLaporan() {
        System.out.println("\n=== LAPORAN KEUANGAN ===");
        System.out.println("Total Pemasukan: Rp " + totalPemasukan);
        System.out.println("Total Pengeluaran: Rp " + totalPengeluaran);
        System.out.println("Saldo Akhir: Rp " + saldo);

        double rasio = hitungRasioKeuangan();
        System.out.println("Rasio Pengeluaran: " + String.format("%.1f", rasio) + "%");

        if (rasio > 80) {
            System.out.println("💡 Tips: Pengeluaran sudah melebihi 80%, perbanyak menabung!");
        } else if (rasio < 50) {
            System.out.println("💡 Tips: Pengelolaan keuangan baik, pertahankan!");
        }
    }
}
