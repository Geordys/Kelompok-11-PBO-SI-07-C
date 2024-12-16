
package JadwalPerawatanPasienn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GeordySiphoSamuelDamanik
 * 2311103112
 * S1SI-07-C
 */

public class SistemJadwalPerawatan {
    private List<pasien> daftarPasien;
    private List<JadwalPerawatan> jadwalPerawatan;

    public SistemJadwalPerawatan() {
        daftarPasien = new ArrayList<>();
        jadwalPerawatan = new ArrayList<>();
    }
    
    // Metode untuk menambah pasien baru
    public void tambahPasien(pasien pasien) {
        daftarPasien.add(pasien);
    }

    // Metode untuk mencari pasien berdasarkan ID
    public pasien cariPasien(String id) {
        for (pasien pasien : daftarPasien) {
            if (pasien.getId().equals(id)) {
                return pasien;
            }
        }
        return null;
    }

    // Metode untuk menambah jadwal perawatan
    public void tambahJadwalPerawatan(JadwalPerawatan jadwal) {
        jadwalPerawatan.add(jadwal);
    }

    // Metode untuk menampilkan semua jadwal perawatan
    public void tampilkanJadwalPerawatan() {
        for (JadwalPerawatan jadwal : jadwalPerawatan) {
            System.out.println(jadwal);
        }
    }

    // Metode untuk menandai jadwal perawatan sebagai selesai
    public void selesaikanPerawatan(pasien pasien, LocalDateTime waktu) {
        for (JadwalPerawatan jadwal : jadwalPerawatan) {
            if (jadwal.getPasien().equals(pasien) && jadwal.getWaktuPerawatan().equals(waktu)) {
                jadwal.setSelesai(true);
                System.out.println("Perawatan untuk pasien " + pasien.getNama() + " telah diselesaikan.");
                return;
            }
        }
        System.out.println("Jadwal perawatan tidak ditemukan.");
    }

    List<JadwalPerawatan> getJadwalPerawatan() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
