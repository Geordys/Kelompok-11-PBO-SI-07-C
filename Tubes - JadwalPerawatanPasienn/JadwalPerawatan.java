
package JadwalPerawatanPasienn;

import java.time.LocalDateTime;

/**
 *
 * @author GeordySiphoSamuelDamanik
 * 2311103112
 * S1SI-07-C
 */

public class JadwalPerawatan {
    private pasien pasien;
    private LocalDateTime waktuPerawatan;
    private String jenisPerawatan;
    private String perawat;
    private boolean selesai;
    
     public JadwalPerawatan(pasien pasien, LocalDateTime waktuPerawatan, String jenisPerawatan, String perawat) {
        this.pasien = pasien;
        this.waktuPerawatan = waktuPerawatan;
        this.jenisPerawatan = jenisPerawatan;
        this.perawat = perawat;
        this.selesai = false;
    }
     
    // Getter dan setter
    public pasien getPasien() { return pasien; }
    public LocalDateTime getWaktuPerawatan() { return waktuPerawatan; }
    public void setWaktuPerawatan(LocalDateTime waktuPerawatan) { this.waktuPerawatan = waktuPerawatan; }
    public String getJenisPerawatan() { return jenisPerawatan; }
    public void setJenisPerawatan(String jenisPerawatan) { this.jenisPerawatan = jenisPerawatan; }
    public String getPerawat() { return perawat; }
    public void setPerawat(String perawat) { this.perawat = perawat; }
    public boolean isSelesai() { return selesai; }
    public void setSelesai(boolean selesai) { this.selesai = selesai; }

    @Override
    public String toString() {
        return "Pasien: " + pasien.getNama() + 
               ", Waktu: " + waktuPerawatan + 
               ", Jenis Perawatan: " + jenisPerawatan + 
               ", Perawat: " + perawat + 
               ", Status: " + (selesai ? "Selesai" : "Belum Selesai");
    }
}
