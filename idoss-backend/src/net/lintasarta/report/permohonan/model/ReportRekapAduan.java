package net.lintasarta.report.permohonan.model;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 23, 2010
 * Time: 1:07:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportRekapAduan {
    private Integer urutan;
    private String bulan;
    private Integer num_major;
    private Integer num_minor;
    private Integer num_total;
    private Integer dur_major;
    private Integer dur_minor;
    private Integer dur_total;
    private Integer jumlah_hari;
    private Integer jumlah_jam;
    private String tingkat_gangguan;
    private String availabilitas;
    private String NamaPemohon;
    private String NikPemohon;
    private String NamaManager;
    private String NikManager;
    private String NamaGm;
    private String NikGm;

    public ReportRekapAduan() {
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Integer getNum_major() {
        return num_major;
    }

    public void setNum_major(Integer num_major) {
        this.num_major = num_major;
    }

    public Integer getNum_minor() {
        return num_minor;
    }

    public void setNum_minor(Integer num_minor) {
        this.num_minor = num_minor;
    }

    public Integer getNum_total() {
        return num_total;
    }

    public void setNum_total(Integer num_total) {
        this.num_total = num_total;
    }

    public Integer getDur_major() {
        return dur_major;
    }

    public void setDur_major(Integer dur_major) {
        this.dur_major = dur_major;
    }

    public Integer getDur_minor() {
        return dur_minor;
    }

    public void setDur_minor(Integer dur_minor) {
        this.dur_minor = dur_minor;
    }

    public Integer getDur_total() {
        return dur_total;
    }

    public void setDur_total(Integer dur_total) {
        this.dur_total = dur_total;
    }

    public Integer getJumlah_hari() {
        return jumlah_hari;
    }

    public void setJumlah_hari(Integer jumlah_hari) {
        this.jumlah_hari = jumlah_hari;
    }

    public Integer getJumlah_jam() {
        return jumlah_jam;
    }

    public void setJumlah_jam(Integer jumlah_jam) {
        this.jumlah_jam = jumlah_jam;
    }

    public String getTingkat_gangguan() {
        return tingkat_gangguan;
    }

    public void setTingkat_gangguan(String tingkat_gangguan) {
        this.tingkat_gangguan = tingkat_gangguan;
    }

    public String getAvailabilitas() {
        return availabilitas;
    }

    public void setAvailabilitas(String availabilitas) {
        this.availabilitas = availabilitas;
    }

    public String getNamaPemohon() {
        return NamaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        NamaPemohon = namaPemohon;
    }

    public String getNikPemohon() {
        return NikPemohon;
    }

    public void setNikPemohon(String nikPemohon) {
        NikPemohon = nikPemohon;
    }

    public String getNamaManager() {
        return NamaManager;
    }

    public void setNamaManager(String namaManager) {
        NamaManager = namaManager;
    }

    public String getNikManager() {
        return NikManager;
    }

    public void setNikManager(String nikManager) {
        NikManager = nikManager;
    }

    public String getNamaGm() {
        return NamaGm;
    }

    public void setNamaGm(String namaGm) {
        NamaGm = namaGm;
    }

    public String getNikGm() {
        return NikGm;
    }

    public void setNikGm(String nikGm) {
        NikGm = nikGm;
    }
}