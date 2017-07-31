package msa.domain.object.sinistro;

public class DannoRcaDO {
    private Boolean lesioniConducente;
    private FullAnagraficaDO anagraficaConducente;
    private FullAnagraficaControparteDO anagraficaControparte;
    private DanniDO danniCliente;
    private DanniDO danniControparte;

    public FullAnagraficaDO getAnagraficaConducente() {
        return anagraficaConducente;
    }

    public void setAnagraficaConducente(FullAnagraficaDO anagraficaConducente) {
        this.anagraficaConducente = anagraficaConducente;
    }

    public FullAnagraficaControparteDO getAnagraficaControparte() {
        return anagraficaControparte;
    }

    public void setAnagraficaControparte(FullAnagraficaControparteDO anagraficaControparte) {
        this.anagraficaControparte = anagraficaControparte;
    }

    public DanniDO getDanniCliente() {
        return danniCliente;
    }

    public void setDanniCliente(DanniDO danniCliente) {
        this.danniCliente = danniCliente;
    }

    public DanniDO getDanniControparte() {
        return danniControparte;
    }

    public void setDanniControparte(DanniDO danniControparte) {
        this.danniControparte = danniControparte;
    }
}
