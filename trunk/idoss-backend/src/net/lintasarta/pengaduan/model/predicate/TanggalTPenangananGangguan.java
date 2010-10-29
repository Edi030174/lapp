package net.lintasarta.pengaduan.model.predicate;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import org.apache.commons.collections.Predicate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Oct 11, 2010
 * Time: 1:01:57 PM
 */
public class TanggalTPenangananGangguan implements Predicate {
    Date tanggalAwal;
    Date tanggalAkhir;

    public TanggalTPenangananGangguan(Date tanggalAwal, Date tanggalAkhir) {
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
    }

    @Override
    public boolean evaluate(Object o) {
        Timestamp createdDate = ((TPenangananGangguan) o).getCreated_date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        Date tanggalCreate = null;
        try {
            tanggalCreate = sdf.parse(sdf.format(createdDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (tanggalCreate != null) {
            if (tanggalCreate.compareTo(tanggalAwal) >= 0) {
                if (tanggalCreate.compareTo(tanggalAkhir) <= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
