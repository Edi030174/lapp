package net.lintasarta.permohonan.model.predicate;

import net.lintasarta.permohonan.model.TPermohonan;
import org.apache.commons.collections.Predicate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 26, 2010
 * Time: 12:40:08 PM
  */
public class TanggalPermohonan implements Predicate{
    Date tanggalAwal;
    Date tanggalAkhir;

    public TanggalPermohonan(Date tanggalAwal, Date tanggalAkhir) {
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
    }

    @Override
    public boolean evaluate(Object o) {
        Timestamp updatedDate = ((TPermohonan) o).getUpdated_date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        Date tanggalUpdate = null;
        try {
            tanggalUpdate = sdf.parse(sdf.format(updatedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (tanggalUpdate != null) {
            if (tanggalUpdate.compareTo(tanggalAwal) >= 0) {
                if (tanggalUpdate.compareTo(tanggalAkhir) <= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
