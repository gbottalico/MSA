package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class DanniDBO {
    @Field("a")
    private Boolean a;
    @Field("adx")
    private Boolean adx;
    @Field("asx")
    private Boolean asx;
    @Field("cdx")

    private Boolean cdx;

    @Field("csx")
    private Boolean csx;
    @Field("d")

    private Boolean d;
    @Field("ddx")


    private Boolean ddx;
    @Field("dsx")

    private Boolean dsx;
    @Field("descrizioneDanni")

    private String descrizioneDanno;

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

    public String getDescrizioneDanno() {
        return descrizioneDanno;
    }

    public void setDescrizioneDanno(String descrizioneDanno) {
        this.descrizioneDanno = descrizioneDanno;
    }
}
