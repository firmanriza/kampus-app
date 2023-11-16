import java.util.Scanner;

import model.Mahasiswa;
import repository.Database;
import model.Prodi;

public class KampusApp {
    public static void main(String[] arg) {
        KampusApp kampusApp = new KampusApp();
        kampusApp.showMenu();
    }

    private Scanner scanner;
    private Database db;

    public KampusApp() {
        scanner = new Scanner(System.in);
        db = new Database();
    }

    public void showMenu() {
        System.out.println("---------------------------------");
        System.out.println("Selamat Datang di Aplikasi Kampus");
        System.out.println("Pilihan Menu:");
        System.out.println("1 -> Tambah Data Mahasiswa");
        System.out.println("2 -> Ubah Data Mahasiswa");
        System.out.println("3 -> Hapus Data Mahasiswa");
        System.out.println("4 -> Cari Data Mahasiswa");
        System.out.println("5 -> Tambah Data Prodi");
        System.out.println("6 -> Ubah Data Prodi");
        System.out.println("7 -> Hapus Data Prodi");
        System.out.println("8 -> Cari Data Prodi");
        System.out.println("0 -> Keluar Aplikasi");
        System.out.print("Silahkan masukan menu yang dipilih: ");
        int menuYangDipilih = scanner.nextInt();
        scanner.nextLine();
        switch(menuYangDipilih) {
            case 1:
            menuTambah();
            break;
            case 2:
            menuUbah();
            break;
            case 3:
            menuHapus();
            break;
            case 4:
            menuCari();
            break;
            case 5:
            menuTambahProdi();
            break;
            case 6:
            menuUbahProdi();
            break;
            case 7:
            menuHapusProdi();
            break;
            case 8:
            menuCariProdi();
            break;
            default: {
                System.out.print("* Terimakasih sudah menggunakan Aplikasi Kampus *");
                System.exit(0);
            }
            break;
        }
        scanner.close();
    }

    public void menuTambah() {
        System.out.println("----- Menu Tambah Mahasiswa -----");
        System.out.print("Masukan NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Masukan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Id Prodi: ");
        String idProdi = scanner.nextLine();
        Prodi prodi = db.prodiTbl.getProdiById(idProdi);

        if (prodi != null) {
            Mahasiswa mahasiswa = new Mahasiswa(nim, nama, idProdi);
            db.mahasiswaTbl.create(mahasiswa);
            System.out.println("* Data Mahasiswa berhasil ditambahkan *");
            System.out.println("Tekan Enter untuk melanjutkan...");
            scanner.nextLine();
            showMenu();
        } else {
            System.out.println("* Id Prodi tidak ditemukan *");
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
        }
    }

    public void menuUbah() {
        System.out.println("----- Menu Ubah Data Mahasiswa -----");
        System.out.print("Masukan NIM Sebelumnya: ");
        String nimLama = scanner.nextLine();
        System.out.print("Masukan NIM: ");
        String nimBaru = scanner.nextLine();
        System.out.print("Masukan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Id Prodi: ");
        String idProdi = scanner.nextLine();
        Mahasiswa mahasiswa = new Mahasiswa(nimBaru, nama, idProdi);
        db.mahasiswaTbl.update(nimLama, mahasiswa);
        System.out.println("*Data Mahasiswa berhasil diubah*");
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuCari() {
        System.out.println("----- Menu Cari Data Mahasiswa -----");
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        Mahasiswa mahasiswa = db.mahasiswaTbl.getMahasiswaByNim(nim);
    
        if (mahasiswa == null) {
            System.out.println("* NIM tidak ditemukan *");
        } else {
            Prodi prodi = db.prodiTbl.getProdiById(mahasiswa.getIdProdi());
            System.out.println("* NIM: " + mahasiswa.getNim() + " *");
            System.out.println("* Nama: " + mahasiswa.getNama() + " *");
            if (prodi != null) {
                System.out.println("* Prodi: " + prodi.getNamaProdi() + " *");
                System.out.println("* Akreditasi Prodi: " + prodi.getAkreditasiProdi() + " *");
            } else {
                System.out.println("* Prodi tidak ditemukan *");
            }
        }
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }
    
    public void menuHapus() {
        System.out.println("----- Menu Hapus Data Mahasiswa -----");
        System.out.print("Masukan NIM: ");
        String nim = scanner.nextLine();
        db.mahasiswaTbl.delete(nim);
        System.out.println("*Data Siswa berhasil dihapus*");
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }


    public void menuTambahProdi() {
        System.out.println("----- Menu Tambah Prodi -----");
        System.out.print("Masukan Id Prodi: ");
        String idProdi = scanner.nextLine();
        System.out.print("Masukan Nama Prodi: ");
        String namaProdi = scanner.nextLine();
        System.out.print("Masukan Akreditasi Prodi: ");
        String akreditasiProdi = scanner.nextLine();
        Prodi prodi = new Prodi(idProdi, namaProdi, akreditasiProdi);
        db.prodiTbl.create(prodi);
        System.out.println("* Prodi berhasil ditambahkan *");
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuUbahProdi() {
        System.out.println("----- Menu Ubah Data Prodi -----");
        System.out.print("Masukan Id Prodi Sebelumnya: ");
        String idProdiLama = scanner.nextLine();
        System.out.print("Masukan Id Prodi: ");
        String idProdiBaru = scanner.nextLine();
        System.out.print("Masukan Nama Prodi: ");
        String namaProdi = scanner.nextLine();
        System.out.print("Masukan Akreditas Prodi: ");
        String akreditasiProdi = scanner.nextLine();
        Prodi prodi = new Prodi(idProdiBaru, namaProdi, akreditasiProdi);
        db.prodiTbl.update(idProdiLama, prodi);
        System.out.println("*Prodi berhasil diubah*");
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuHapusProdi() {
        System.out.println("----- Menu Hapus Data Prodi -----");
        System.out.print("Masukan Id Prodi: ");
        String idProdi = scanner.nextLine();
        db.prodiTbl.delete(idProdi);
        System.out.println("*Prodi berhasil dihapus*");
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();

    }

    
    public void menuCariProdi() {
        System.out.println("----- Menu Cari Data Prodi -----");
        System.out.print("Masukkan ID Prodi: ");
        String idProdi = scanner.nextLine();
        Prodi prodi = db.prodiTbl.getProdiById(idProdi);
    
        if (prodi != null) {
            System.out.println("* ID Prodi: " + prodi.getIdProdi() + " *");
            System.out.println("* Nama Prodi: " + prodi.getNamaProdi() + " *");
            System.out.println("* Akreditasi Prodi: " + prodi.getAkreditasiProdi() + " *");
        } else {
            System.out.println("* Prodi dengan ID " + idProdi + " tidak ditemukan *");
        }
    
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }
    
}
