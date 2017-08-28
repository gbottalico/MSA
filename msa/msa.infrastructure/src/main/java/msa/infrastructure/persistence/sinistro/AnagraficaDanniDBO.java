package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class AnagraficaDanniDBO {
    @Field("anagrafica")
    private FullAnagraficaControparteDBO anagrafica;
    @Field("danni")
    private DanniDBO danni;

    public FullAnagraficaControparteDBO getAnagrafica() {
        return anagrafica;
    }

    public void setAnagrafica(FullAnagraficaControparteDBO anagrafica) {
        this.anagrafica = anagrafica;
    }

    public DanniDBO getDanni() {
        return danni;
    }

    public void setDanni(DanniDBO danni) {
        this.danni = danni;
    }
}
