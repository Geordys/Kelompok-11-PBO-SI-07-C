
package TubesPBO;

/**
 *
 * @author Geordy Sipho Samuel Damanik
 * 2311103112
 * S1SI-07-C
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasienDAO {
     public void simpanPasien(Pasien pasien) {
        String sql = "INSERT INTO pasien (kode_pasien, nama_pasien, alamat_pasien, tanggal_lahir, kepala_keluarga, jenis_kelamin, no_telepon) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pasien.getKodePasien());
            stmt.setString(2, pasien.getNamaPasien());
            stmt.setString(3, pasien.getAlamatPasien());
            stmt.setString(4, pasien.getTanggalLahir());
            stmt.setString(5, pasien.getKepalaKeluarga());
            stmt.setString(6, pasien.getJenisKelamin());
            stmt.setString(7, pasien.getNoTelepon());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error menyimpan data: " + e.getMessage());
        }
    }
    
    public void updatePasien(Pasien pasien) {
        String sql = "UPDATE pasien SET nama_pasien=?, alamat_pasien=?, tanggal_lahir=?, " +
                    "kepala_keluarga=?, jenis_kelamin=?, no_telepon=? WHERE kode_pasien=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pasien.getNamaPasien());
            stmt.setString(2, pasien.getAlamatPasien());
            stmt.setString(3, pasien.getTanggalLahir());
            stmt.setString(4, pasien.getKepalaKeluarga());
            stmt.setString(5, pasien.getJenisKelamin());
            stmt.setString(6, pasien.getNoTelepon());
            stmt.setString(7, pasien.getKodePasien());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error mengupdate data: " + e.getMessage());
        }
    }
    
    public void hapusPasien(String kodePasien) {
        String sql = "DELETE FROM pasien WHERE kode_pasien=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, kodePasien);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error menghapus data: " + e.getMessage());
        }
    }
    
    public List<Pasien> getAllPasien() {
        List<Pasien> listPasien = new ArrayList<>();
        String sql = "SELECT * FROM pasien";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pasien pasien = new Pasien();
                pasien.setKodePasien(rs.getString("kode_pasien"));
                pasien.setNamaPasien(rs.getString("nama_pasien"));
                pasien.setAlamatPasien(rs.getString("alamat_pasien"));
                pasien.setTanggalLahir(rs.getString("tanggal_lahir"));
                pasien.setKepalaKeluarga(rs.getString("kepala_keluarga"));
                pasien.setJenisKelamin(rs.getString("jenis_kelamin"));
                pasien.setNoTelepon(rs.getString("no_telepon"));
                listPasien.add(pasien);
            }
        } catch (SQLException e) {
            System.out.println("Error mengambil data: " + e.getMessage());
        }
        return listPasien;
    }
}

