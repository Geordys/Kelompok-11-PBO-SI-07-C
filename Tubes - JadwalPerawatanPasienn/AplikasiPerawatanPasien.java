
package JadwalPerawatanPasienn;

/**
 *
 * @author GeordySiphoSamuelDamanik
 * 2311103112
 * S1SI-07-C
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AplikasiPerawatanPasien extends javax.swing.JFrame {
    private SistemJadwalPerawatan sistem;
    
    // Komponen GUI
    private JTextField tfIdPasien, tfNamaPasien, tfUmurPasien, tfDiagnosis, tfWaktuPerawatan, tfJenisPerawatan, tfNamaPerawat;
    private JButton btnTambahPasien, btnTambahJadwal, btnTampilkanJadwal, btnSelesaikanPerawatan;
    private JTable tableJadwalPerawatan;
    
    public AplikasiPerawatanPasien() {
        sistem = new SistemJadwalPerawatan();
        initComponents();
    }
    
    private void initUI() {
        setTitle("Sistem Jadwal Perawatan Pasien");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        
        // Komponen untuk tambah pasien
        panel.add(new JLabel("ID Pasien:"));
        tfIdPasien = new JTextField();
        panel.add(tfIdPasien);
        
        panel.add(new JLabel("Nama Pasien:"));
        tfNamaPasien = new JTextField();
        panel.add(tfNamaPasien);
        
        panel.add(new JLabel("Umur Pasien:"));
        tfUmurPasien = new JTextField();
        panel.add(tfUmurPasien);
        
        panel.add(new JLabel("Diagnosis:"));
        tfDiagnosis = new JTextField();
        panel.add(tfDiagnosis);
        
        btnTambahPasien = new JButton("Tambah Pasien");
        panel.add(btnTambahPasien);
        
        // Komponen untuk tambah jadwal perawatan
        panel.add(new JLabel("Waktu Perawatan (yyyy-mm-dd hh:mm):"));
        tfWaktuPerawatan = new JTextField();
        panel.add(tfWaktuPerawatan);
        
        panel.add(new JLabel("Jenis Perawatan:"));
        tfJenisPerawatan = new JTextField();
        panel.add(tfJenisPerawatan);
        
        panel.add(new JLabel("Nama Perawat:"));
        tfNamaPerawat = new JTextField();
        panel.add(tfNamaPerawat);
        
        btnTambahJadwal = new JButton("Tambah Jadwal");
        panel.add(btnTambahJadwal);
        
        // Tampilkan jadwal perawatan
        btnTampilkanJadwal = new JButton("Tampilkan Jadwal");
        panel.add(btnTampilkanJadwal);
        
        // Selesaikan perawatan
        btnSelesaikanPerawatan = new JButton("Selesaikan Perawatan");
        panel.add(btnSelesaikanPerawatan);
        
        // Menambahkan panel ke frame
        add(panel, BorderLayout.NORTH);
        
        // Table untuk menampilkan jadwal perawatan
        tableJadwalPerawatan = new JTable();
        add(new JScrollPane(tableJadwalPerawatan), BorderLayout.CENTER);
        
        // ActionListener untuk tombol
        btnTambahPasien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfIdPasien.getText();
                String nama = tfNamaPasien.getText();
                int umur = Integer.parseInt(tfUmurPasien.getText());
                String diagnosis = tfDiagnosis.getText();
                pasien pasienBaru = new pasien(id, nama, umur, diagnosis);
                sistem.tambahPasien(pasienBaru);
                JOptionPane.showMessageDialog(null, "Pasien berhasil ditambahkan.");
            }
        });
        
        btnTambahJadwal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idPasien = tfIdPasien.getText();
                pasien pasienTerpilih = sistem.cariPasien(idPasien);
                if (pasienTerpilih != null) {
                    String waktuString = tfWaktuPerawatan.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime waktuPerawatan = LocalDateTime.parse(waktuString, formatter);
                    String jenisPerawatan = tfJenisPerawatan.getText();
                    String namaPerawat = tfNamaPerawat.getText();
                    JadwalPerawatan jadwalBaru = new JadwalPerawatan(
                        pasienTerpilih,
                        waktuPerawatan,
                        jenisPerawatan,
                        namaPerawat
                    );
                    sistem.tambahJadwalPerawatan(jadwalBaru);
                    JOptionPane.showMessageDialog(null, "Jadwal perawatan berhasil ditambahkan.");
                } else {
                    JOptionPane.showMessageDialog(null, "Pasien tidak ditemukan.");
                }
            }
        });
        
        btnTampilkanJadwal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<JadwalPerawatan> jadwalList = sistem.getJadwalPerawatan();
                String[] columnNames = {"Pasien", "Waktu", "Jenis Perawatan", "Perawat", "Status"};
                String[][] data = new String[jadwalList.size()][5];
                for (int i = 0; i < jadwalList.size(); i++) {
                    JadwalPerawatan jadwal = jadwalList.get(i);
                    data[i][0] = jadwal.getPasien().getNama();
                    data[i][1] = jadwal.getWaktuPerawatan().toString();
                    data[i][2] = jadwal.getJenisPerawatan();
                    data[i][3] = jadwal.getPerawat();
                    data[i][4] = jadwal.isSelesai() ? "Selesai" : "Belum Selesai";
                }
                tableJadwalPerawatan.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            }
        });
        
        btnSelesaikanPerawatan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idPasien = tfIdPasien.getText();
                pasien pasienSelesai = sistem.cariPasien(idPasien);
                if (pasienSelesai != null) {
                    String waktuSelesaiString = tfWaktuPerawatan.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime waktuSelesai = LocalDateTime.parse(waktuSelesaiString, formatter);
                    sistem.selesaikanPerawatan(pasienSelesai, waktuSelesai);
                } else {
                    JOptionPane.showMessageDialog(null, "Pasien tidak ditemukan.");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplikasiPerawatanPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiPerawatanPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiPerawatanPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiPerawatanPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiPerawatanPasien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
