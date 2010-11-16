package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.pengaduan.dao.MttrDAO;
import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.pengaduan.service.MttrService;
import net.lintasarta.util.TicketIdGenerator;

import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 24, 2010
 * Time: 8:32:53 AM
 */
public class MttrServiceImpl implements MttrService {

    private MttrDAO mttrDAO;

    public MttrDAO getMttrDAO() {
        return mttrDAO;
    }

    public void setMttrDAO(MttrDAO mttrDAO) {
        this.mttrDAO = mttrDAO;
    }

    @Override
    public String getGenerateId() {
        int i = mttrDAO.getGenerateId();
        String seq = String.valueOf(i);
        TicketIdGenerator tid = new TicketIdGenerator(seq);

        return tid.getTicketId();
    }

    @Override
    public Mttr getMttrByMttrId(int t_idoss_mttr_id) {
        return mttrDAO.getMttrByMttrId(t_idoss_mttr_id);
    }

    @Override
    public void createMttr(Mttr mttr) {
        int i = mttrDAO.getGenerateId();
        int g = Integer.parseInt(getGenerateId());
        mttr.setT_idoss_mttr_id(g);
        mttr.setGen_id_col(i);
        getMttrDAO().createMttr(mttr);
    }

    @Override
    public void saveOrUpdateMttr(Mttr mttr) {
        if (mttr.getClosed() > 0) {
            mttr.setMttr(getDurasi(mttr) - getLamaPending(mttr));
        }
        getMttrDAO().saveOrUpdateMttr(mttr);
    }

    public long getDurasi(Mttr mttr) {
        long ts = Calendar.getInstance().getTimeInMillis();
        long duration = ts - mttr.getOpened();
        if (mttr.getClosed() > 0) {
            if (ts >= mttr.getClosed()) {
                duration = mttr.getClosed() - mttr.getOpened();
            }
        }
        return duration;
    }

    public long getDurasiPelaksanaan(Mttr mttr) {
        long ts = Calendar.getInstance().getTimeInMillis();
        if (mttr.getInserted_pelaksana() > 0) {
            long durasiPelaksanaan = ts - mttr.getInserted_pelaksana();
            if (mttr.getClosed() > 0) {
                if (ts >= mttr.getClosed()) {
                    durasiPelaksanaan = mttr.getClosed() - mttr.getInserted_pelaksana();
                }
            }
            return durasiPelaksanaan;
        } else {
            return 0;
        }
    }

    public long getDurasiTarget(Mttr mttr) {
        long ts = Calendar.getInstance().getTimeInMillis();
        if (mttr.getTarget() > 0) {
            if (mttr.getTarget() > ts) {
                long durasiTarget = mttr.getTarget() - ts;
                if (mttr.getClosed() > 0) {
                    if (ts >= mttr.getClosed()) {
                        durasiTarget = mttr.getClosed() - mttr.getTarget();
                    }
                }
                return durasiTarget;
            }
            return mttr.getTarget();
        } else {
            return 0;
        }
    }

    public boolean isInProgress(Mttr mttr) {
        long ts = Calendar.getInstance().getTimeInMillis();
        return mttr.getPending_end() > 0 && ts >= mttr.getPending_end();
    }


    public long getLamaPending(Mttr mttr) {
        long ts = Calendar.getInstance().getTimeInMillis();
        long lama_pending = mttr.getLama_pending();

        if (mttr.getPending_end() > 0) {
            if (ts >= mttr.getPending_end()) {
                lama_pending = lama_pending + mttr.getPending_end() - mttr.getPending_start();
            } else {
                lama_pending = lama_pending + ts - mttr.getPending_start();
            }
        }
        return lama_pending;
    }

    public List<Mttr> getMttrByNomorTiket(String nomorTiket) {
        return mttrDAO.getMttrByNomorTiket(nomorTiket);
    }
}