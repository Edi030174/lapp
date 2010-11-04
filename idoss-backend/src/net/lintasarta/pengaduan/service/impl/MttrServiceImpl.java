package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.pengaduan.dao.MttrDAO;
import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.pengaduan.service.MttrService;
import net.lintasarta.util.TicketIdGenerator;

import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 24, 2010
 * Time: 8:32:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class MttrServiceImpl implements MttrService {

    private MttrDAO mttrDAO;

    public MttrDAO getMttrDAO() {
        return mttrDAO;
    }

    public void setMttrDAO(MttrDAO mttrDAO) {
        this.mttrDAO = mttrDAO;
    }

    public Mttr getNewMttr() {
        return new Mttr();
    }

    @Override
    public String getGenerateId() {
        int i = mttrDAO.getGenerateId();
        String seq = String.valueOf(i);
        TicketIdGenerator tid = new TicketIdGenerator(seq);
        String ticketIdResult = tid.getTicketId();

        return ticketIdResult;
    }

    @Override
    public Mttr getMttrByMttrId(int t_idoss_mttr_id) {
        Mttr mttr = mttrDAO.getMttrByMttrId(t_idoss_mttr_id);
        return mttr;
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
        Mttr mttrUpdate = getMttrByNomorTiket(mttr.getNomor_tiket());
        if (mttr.getOpened() > 0) {
            mttrUpdate.setOpened(mttr.getOpened());
        }
        if (mttr.getClosed() > 0) {
            mttrUpdate.setClosed(mttr.getClosed());
            mttrUpdate.setLama_pending(getLamaPending(mttrUpdate));
            mttrUpdate.setMttr(getDurasi(mttrUpdate) - mttrUpdate.getLama_pending());
        }
        if (mttr.getInprogress() > 0) {
            mttrUpdate.setInprogress(mttr.getInprogress());
        }
        if (mttr.getPending_start() > 0) {
            mttrUpdate.setPending_start(mttr.getPending_start());
        }
        if (mttr.getPending_end() > 0) {
            mttrUpdate.setPending_end(mttr.getPending_end());
        }
        getMttrDAO().saveOrUpdateMttr(mttrUpdate);
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

    public long getLamaPending(Mttr mttr) {
        long ts = Calendar.getInstance().getTimeInMillis();
        long lama_pending = mttr.getLama_pending();
        if (mttr.getPending_start() > 0) {
            lama_pending = lama_pending + ts - mttr.getPending_start();
            if (mttr.getPending_end() > 0) {
                if (ts >= mttr.getPending_end()) {
                    lama_pending = lama_pending + mttr.getPending_end() - mttr.getPending_start();
                }
            }
        }
        return lama_pending;
    }

    public Mttr getMttrByNomorTiket(String nomorTiket) {
        return mttrDAO.getMttrByNomorTiket(nomorTiket);
    }
}