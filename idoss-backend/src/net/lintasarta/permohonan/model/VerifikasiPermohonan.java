package net.lintasarta.permohonan.model;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Nov 11, 2010
 * Time: 4:22:45 PM
 */
public class VerifikasiPermohonan {
    private String status_track_permohonan;
    private String nik_pelaksana;

    public VerifikasiPermohonan() {
    }

    public String getStatus_track_permohonan() {
        return status_track_permohonan;
    }

    public void setStatus_track_permohonan(String status_track_permohonan) {
        this.status_track_permohonan = status_track_permohonan;
    }

    public String getNik_pelaksana() {
        return nik_pelaksana;
    }

    public void setNik_pelaksana(String nik_pelaksana) {
        this.nik_pelaksana = nik_pelaksana;
    }
}
