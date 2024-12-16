
package JadwalPerawatanPasienn;

/**
 *
 * @author GeordySiphoSamuelDamanik
 * 2311103112
 * S1SI-07-C
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class pasien {
    private String id;
    private String nama;
    private int umur;
    private String diagnosis;
    
    public pasien(String id, String nama, int umur, String diagnosis) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.diagnosis = diagnosis;
    }
    
    // Getter dan setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public int getUmur() { return umur; }
    public void setUmur(int umur) { this.umur = umur; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Umur: " + umur + ", Diagnosis: " + diagnosis;
    }
}
