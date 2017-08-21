package msa.application.dto.sinistro.rca.dannoRca;

import java.io.Serializable;

public class DanniDTO implements Serializable {
   private Boolean a;
   private Boolean adx;
   private Boolean asx;
   private Boolean cdx;
   private Boolean csx;
   private Boolean d;
   private Boolean ddx;
   private Boolean dsx;
    private String descrizioneDanno;



    public String getDescrizioneDanno() {

        return descrizioneDanno;
    }

    public void setDescrizioneDanno(String descrizioneDanno) {
        this.descrizioneDanno = descrizioneDanno;
    }

    public Boolean getA() {

        return a;
    }

    public void setA(Boolean a) {
        this.a = a;
    }

    public Boolean getAdx() {
        return adx;
    }

    public void setAdx(Boolean adx) {
        this.adx = adx;
    }

    public Boolean getAsx() {
        return asx;
    }

    public void setAsx(Boolean asx) {
        this.asx = asx;
    }

    public Boolean getCdx() {
        return cdx;
    }

    public void setCdx(Boolean cdx) {
        this.cdx = cdx;
    }

    public Boolean getCsx() {
        return csx;
    }

    public void setCsx(Boolean csx) {
        this.csx = csx;
    }

    public Boolean getD() {
        return d;
    }

    public void setD(Boolean d) {
        this.d = d;
    }

    public Boolean getDdx() {
        return ddx;
    }

    public void setDdx(Boolean ddx) {
        this.ddx = ddx;
    }

    public Boolean getDsx() {
        return dsx;
    }

    public void setDsx(Boolean dsx) {
        this.dsx = dsx;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DanniDTO danniDTO = (DanniDTO) o;

        if (a != null ? !a.equals(danniDTO.a) : danniDTO.a != null) return false;
        if (adx != null ? !adx.equals(danniDTO.adx) : danniDTO.adx != null) return false;
        if (asx != null ? !asx.equals(danniDTO.asx) : danniDTO.asx != null) return false;
        if (cdx != null ? !cdx.equals(danniDTO.cdx) : danniDTO.cdx != null) return false;
        if (csx != null ? !csx.equals(danniDTO.csx) : danniDTO.csx != null) return false;
        if (d != null ? !d.equals(danniDTO.d) : danniDTO.d != null) return false;
        if (ddx != null ? !ddx.equals(danniDTO.ddx) : danniDTO.ddx != null) return false;
        if (dsx != null ? !dsx.equals(danniDTO.dsx) : danniDTO.dsx != null) return false;
        return descrizioneDanno != null ? descrizioneDanno.equals(danniDTO.descrizioneDanno) : danniDTO.descrizioneDanno == null;
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (adx != null ? adx.hashCode() : 0);
        result = 31 * result + (asx != null ? asx.hashCode() : 0);
        result = 31 * result + (cdx != null ? cdx.hashCode() : 0);
        result = 31 * result + (csx != null ? csx.hashCode() : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        result = 31 * result + (ddx != null ? ddx.hashCode() : 0);
        result = 31 * result + (dsx != null ? dsx.hashCode() : 0);
        result = 31 * result + (descrizioneDanno != null ? descrizioneDanno.hashCode() : 0);
        return result;
    }
}
