import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class BenhNhan {
    String maBN;
    String tenBN;
    String diaChi;
    String dienThoai;
    String gioiTinh;
    LocalDate ngaySinh;
    String baoHiemYTe;

    public BenhNhan(String maBN, String tenBN, String diaChi, String dienThoai, String gioiTinh, LocalDate ngaySinh, String baoHiemYTe) {
        this.maBN = maBN;
        this.tenBN = tenBN;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.baoHiemYTe = baoHiemYTe;
    }
}

class BenhAn {
    String maBA;
    String tenBA;
    String khoa;
    String phong;
    LocalDate ngayVao;
    LocalDate ngayRa;
    double vienPhi;
    BenhNhan benhNhan;

    public BenhAn(String maBA, String tenBA, String khoa, String phong, LocalDate ngayVao, LocalDate ngayRa, double vienPhi, BenhNhan benhNhan) {
        this.maBA = maBA;
        this.tenBA = tenBA;
        this.khoa = khoa;
        this.phong = phong;
        this.ngayVao = ngayVao;
        this.ngayRa = ngayRa;
        this.vienPhi = vienPhi;
        this.benhNhan = benhNhan;
    }
}

public class QuanLyBenhNhan extends JFrame {
    private List<BenhNhan> danhSachBenhNhan = new ArrayList<>();
    private List<BenhAn> danhSachBenhAn = new ArrayList<>();
    private JTable tableBenhNhan, tableBenhAn;
    private DefaultTableModel tableModelBenhNhan, tableModelBenhAn;

    public QuanLyBenhNhan() {
        setTitle("Hệ Thống Quản Lý Bệnh Viện");
        setSize(1500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        add(panel);

        
        JPanel panelBenhNhan = new JPanel(new BorderLayout());
        panel.add(panelBenhNhan);

        String[] columnNamesBenhNhan = {"Mã BN", "Tên BN", "Địa Chỉ", "Điện Thoại", "Giới Tính", "Ngày Sinh", "Bảo Hiểm Y Tế"};
        tableModelBenhNhan = new DefaultTableModel(columnNamesBenhNhan, 0);
        tableBenhNhan = new JTable(tableModelBenhNhan);
        JScrollPane scrollPaneBenhNhan = new JScrollPane(tableBenhNhan);
        panelBenhNhan.add(scrollPaneBenhNhan, BorderLayout.CENTER);

        
        JPanel panelBenhAn = new JPanel(new BorderLayout());
        panel.add(panelBenhAn);

        String[] columnNamesBenhAn = {"Mã BA", "Tên BA", "Khoa", "Phòng", "Ngày Vào", "Ngày Ra", "Viện Phí"};
        tableModelBenhAn = new DefaultTableModel(columnNamesBenhAn, 0);
        tableBenhAn = new JTable(tableModelBenhAn);
        JScrollPane scrollPaneBenhAn = new JScrollPane(tableBenhAn);
        panelBenhAn.add(scrollPaneBenhAn, BorderLayout.CENTER);

        // Panel cho các nút thao tác
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        panel.add(buttonPanel);

        JButton benhNhanButton = new JButton("Nhập Bệnh Nhân");
        benhNhanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBenhNhan();
            }
        });
        buttonPanel.add(benhNhanButton);

        JButton benhAnButton = new JButton("Nhập Bệnh Án");
        benhAnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBenhAn();
            }
        });
        buttonPanel.add(benhAnButton);

        JButton tinhVienPhiButton = new JButton("Tính Viện Phí");
        tinhVienPhiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tinhVienPhi();
            }
        });
        buttonPanel.add(tinhVienPhiButton);

        JButton benhNhanDuoi30TuoiButton = new JButton("Danh Sách Bệnh Nhân Dưới 30 Tuổi Trái Tuyến Bảo Hiểm");
        benhNhanDuoi30TuoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                danhSachBenhNhanDuoi30TuoiTraiTuyenBaoHiem();
            }
        });
        buttonPanel.add(benhNhanDuoi30TuoiButton);

        JButton quyChiTraNhieuNhatButton = new JButton("Quý Chi Trả Nhiều Nhất Trong Năm 2021");
        quyChiTraNhieuNhatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quyChiTraNhieuNhatTrongNam2021();
            }
        });
        buttonPanel.add(quyChiTraNhieuNhatButton);

        JButton benhNhanMaxNgayVaoVienVaTienButton = new JButton("Bệnh Nhân Có Số Ngày Vào Viện Nhiều Nhất Và Tiền Viện Phí Cao Nhất Trong Năm 2022");
        benhNhanMaxNgayVaoVienVaTienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                benhNhanMaxNgayVaoVienVaTienTrongNam2022();
            }
        });
        buttonPanel.add(benhNhanMaxNgayVaoVienVaTienButton);
    }

    private void addBenhNhan() {
        JTextField maBNField = new JTextField();
        JTextField tenBNField = new JTextField();
        JTextField diaChiField = new JTextField();
        JTextField dienThoaiField = new JTextField();
        JTextField gioiTinhField = new JTextField();
        JTextField ngaySinhField = new JTextField();

        String[] baoHiemOptions = {"Đúng Tuyến", "Trái Tuyến", "Dưới 5 Tuổi"};
        JComboBox<String> baoHiemComboBox = new JComboBox<>(baoHiemOptions);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Mã BN:"));
        panel.add(maBNField);
        panel.add(new JLabel("Tên BN:"));
        panel.add(tenBNField);
        panel.add(new JLabel("Địa Chỉ:"));
        panel.add(diaChiField);
        panel.add(new JLabel("Điện Thoại:"));
        panel.add(dienThoaiField);
        panel.add(new JLabel("Giới Tính:"));
        panel.add(gioiTinhField);
        panel.add(new JLabel("Ngày Sinh (dd/MM/yyyy):"));
        panel.add(ngaySinhField);
        panel.add(new JLabel("Bảo Hiểm Y Tế:"));
        panel.add(baoHiemComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Nhập Thông Tin Bệnh Nhân", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate ngaySinh = LocalDate.parse(ngaySinhField.getText(), formatter);

                BenhNhan benhNhan = new BenhNhan(
                        maBNField.getText(),
                        tenBNField.getText(),
                        diaChiField.getText(),
                        dienThoaiField.getText(),
                        gioiTinhField.getText(),
                        ngaySinh,
                        (String) baoHiemComboBox.getSelectedItem()
                );
                danhSachBenhNhan.add(benhNhan);
                updateTableBenhNhan();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ngày không hợp lệ. Vui lòng nhập lại.");
            }
        }
    }

    private void addBenhAn() {
        int selectedRow = tableBenhNhan.getSelectedRow();
        if (selectedRow != -1) { 
            JTextField maBAField = new JTextField();
            JTextField tenBAField = new JTextField();
            JTextField khoaField = new JTextField();
            JTextField phongField = new JTextField();
            JTextField ngayVaoField = new JTextField();
            JTextField ngayRaField = new JTextField();
    
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                BenhNhan selectedBenhNhan = danhSachBenhNhan.get(selectedRow);
    
                int result = JOptionPane.showConfirmDialog(null, new Object[]{"Mã BA:", maBAField, "Tên BA:", tenBAField, "Khoa:", khoaField, "Phòng:", phongField, "Ngày Vào (dd/MM/yyyy):", ngayVaoField, "Ngày Ra (dd/MM/yyyy):", ngayRaField}, "Nhập Thông Tin Bệnh Án", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    LocalDate ngayVao = LocalDate.parse(ngayVaoField.getText(), formatter);
                    LocalDate ngayRa = LocalDate.parse(ngayRaField.getText(), formatter);
    
                    BenhAn benhAn = new BenhAn(
                            maBAField.getText(),
                            tenBAField.getText(),
                            khoaField.getText(),
                            phongField.getText(),
                            ngayVao,
                            ngayRa,
                            0,
                            selectedBenhNhan
                    );
                    danhSachBenhAn.add(benhAn);
                    updateTableBenhAn();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ngày không hợp lệ. Vui lòng nhập lại.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân trước khi thêm bệnh án.");
        }
    }

    private void tinhVienPhi() {
        for (BenhAn benhAn : danhSachBenhAn) {
            if (benhAn.ngayRa != null && benhAn.ngayVao != null) {
                long soNgay = benhAn.ngayRa.toEpochDay() - benhAn.ngayVao.toEpochDay();
                double vienPhi = soNgay * 200000;
                if (benhAn.benhNhan.baoHiemYTe.equals("Đúng Tuyến")) {
                    vienPhi *= 0.2; // Bảo hiểm đúng tuyến chi trả 80%
                } else if (benhAn.benhNhan.baoHiemYTe.equals("Trái Tuyến")) {
                    vienPhi *= 0.5; // Bảo hiểm trái tuyến chi trả 50%
                } else if (benhAn.benhNhan.baoHiemYTe.equals("Dưới 5 Tuổi")) {
                    vienPhi = 0; // Bệnh nhân dưới 5 tuổi được miễn phí 100%
                }
                benhAn.vienPhi = vienPhi;
            }
        }
        updateTableBenhAn();
    }

    private void danhSachBenhNhanDuoi30TuoiTraiTuyenBaoHiem() {
        List<BenhNhan> danhSachDuoi30TuoiTraiTuyenBaoHiem = new ArrayList<>();
        for (BenhNhan benhNhan : danhSachBenhNhan) {
            if (benhNhan.ngaySinh != null) {
                int age = tinhnam(benhNhan.ngaySinh);
                if (age < 30 && benhNhan.baoHiemYTe.equals("Trái Tuyến")) {
                    danhSachDuoi30TuoiTraiTuyenBaoHiem.add(benhNhan);
                }
            }
        }
        displayBenhNhanList("Danh Sách Bệnh Nhân Dưới 30 Tuổi Trái Tuyến Bảo Hiểm", danhSachDuoi30TuoiTraiTuyenBaoHiem);
    }

    private int tinhnam(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.minusYears(birthDate.getYear()).getYear();
    }

    private void quyChiTraNhieuNhatTrongNam2021() {
        double maxAmount = 0;
        double tienDuocMienGiam = 0; // Số tiền được miễn giảm
        String maxQuy = "";
        boolean found = false; // Biến này để kiểm tra xem có bệnh nhân nào trong năm 2021 không
    
        for (BenhAn benhAn : danhSachBenhAn) {
            if (benhAn.ngayRa != null && benhAn.ngayRa.getYear() == 2021) {
                found = true; // Đánh dấu đã tìm thấy bệnh nhân trong năm 2021
                double vienPhi = benhAn.vienPhi; // Lưu lại viện phí ban đầu
                // Tính số tiền miễn giảm
                tienDuocMienGiam = benhAn.ngayVao.until(benhAn.ngayRa).getDays() * 200000 - benhAn.vienPhi ;
                if (vienPhi > maxAmount) {
                    maxAmount = vienPhi;
                    maxQuy = benhAn.maBA;
                }
            }
        }
    
        if (found) {
            JOptionPane.showMessageDialog(null, "Quý chi trả nhiều nhất trong năm 2021 với số tiền được miễn giảm là " + tienDuocMienGiam);
        } else {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu về năm 2021");
        }
    }
    
    
    
    
    private void benhNhanMaxNgayVaoVienVaTienTrongNam2022() {
        int maxDays = 0;
        double maxMoney = 0;
        String maxPatient = "";
        boolean found = false; // Biến này để kiểm tra xem có bệnh nhân nào trong năm 2022 không
    
        for (BenhAn benhAn : danhSachBenhAn) {
            if (benhAn.ngayVao != null && benhAn.ngayVao.getYear() == 2022) {
                found = true; // Đánh dấu đã tìm thấy bệnh nhân trong năm 2022
                long soNgay = benhAn.ngayRa.toEpochDay() - benhAn.ngayVao.toEpochDay();
                if (soNgay > maxDays || (soNgay == maxDays && benhAn.vienPhi > maxMoney)) {
                    maxDays = (int) soNgay;
                    maxMoney = benhAn.vienPhi;
                    maxPatient = benhAn.benhNhan.tenBN;
                }
            }
        }
    
        if (found) {
            JOptionPane.showMessageDialog(null, "Bệnh nhân có số ngày vào viện nhiều nhất và tiền viện phí cao nhất trong năm 2022 là " + maxPatient + " với " + maxDays + " ngày và viện phí là " + maxMoney);
        } else {
            JOptionPane.showMessageDialog(null, "Không có bệnh nhân nào nằm viện năm 2022");
        }
    }
    

    private void updateTableBenhNhan() {
        tableModelBenhNhan.setRowCount(0);
        for (BenhNhan benhNhan : danhSachBenhNhan) {
            Object[] row = {benhNhan.maBN, benhNhan.tenBN, benhNhan.diaChi, benhNhan.dienThoai, benhNhan.gioiTinh, benhNhan.ngaySinh.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), benhNhan.baoHiemYTe};
            tableModelBenhNhan.addRow(row);
        }
    }

    private void updateTableBenhAn() {
        tableModelBenhAn.setRowCount(0);
        for (BenhAn benhAn : danhSachBenhAn) {
            Object[] row = {benhAn.maBA, benhAn.tenBA, benhAn.khoa, benhAn.phong, benhAn.ngayVao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), (benhAn.ngayRa != null ? benhAn.ngayRa.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : ""), benhAn.vienPhi};
            tableModelBenhAn.addRow(row);
        }
    }

    private void displayBenhNhanList(String title, List<BenhNhan> danhSach) {
        StringBuilder message = new StringBuilder();
        for (BenhNhan benhNhan : danhSach) {
            message.append("Mã BN: ").append(benhNhan.maBN).append(", Tên BN: ").append(benhNhan.tenBN).append(", Ngày Sinh: ").append(benhNhan.ngaySinh.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        QuanLyBenhNhan quanlybenhnhan = new QuanLyBenhNhan();
        quanlybenhnhan.setVisible(true);
    }
}
