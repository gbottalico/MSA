package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class AnagraficaDanniDBO {
    @Field("anagrafica")
    private FullAnagraficaDBO anagrafica;
    @Field("danni")
    private DanniDBO danni;

    public FullAnagraficaDBO getAnagrafica() {
        return anagrafica;
    }

    public void setAnagrafica(FullAnagraficaDBO anagrafica) {
        this.anagrafica = anagrafica;
    }

    public DanniDBO getDanni() {
        return danni;
    }

    public void setDanni(DanniDBO danni) {
        this.danni = danni;
    }
}
