package msa.application.service.util;

import msa.application.dto.sinistro.anagrafica.BaseAnagraficaDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.application.service.enumerator.Api;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;


/**
 * Created by simon.calabrese on 10/08/2017.
 */
@Service
public class UtilsService extends BaseService {

    private static final List<String> vocali = Arrays.asList("A", "E", "I", "O", "U");
    private static final List<String> consonanti = Arrays.asList("B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z");

    public String calcolaCodiceFiscale(final BaseAnagraficaDTO anagrafica) throws InternalMsaException {
        try {
            String descrizioneComune = anagrafica.getLuogoNascita().getDescrizioneComune().replaceAll(" ", "%20");
            Document parse = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(
                            new StringReader(
                                    doGetCallXml(Api.ID_CITTA_CODICE_FISCALE,
                                            getGetParamsBuilder()
                                                    .appendParam("mask", descrizioneComune)
                                    )
                            )
                    ));
            parse.normalizeDocument();
            String idCitta = parse.getDocumentElement()
                    .getChildNodes()
                    .item(0)
                    .getNextSibling()
                    .getAttributes()
                    .getNamedItem("id")
                    .getNodeValue();
            String nomeCitta = parse.getDocumentElement()
                    .getChildNodes()
                    .item(0)
                    .getNextSibling()
                    .getFirstChild()
                    .getNodeValue();
            Calendar instance = Calendar.getInstance();
            instance.setTime(anagrafica.getDataNascita());

            return doPostCallFormData(Api.CODICE_FISCALE, getFormDataBuilder()
                    .appendParam("name", new String(Base64.getEncoder().encode(anagrafica.getNome().getBytes())))
                    .appendParam("surname", new String(Base64.getEncoder().encode(anagrafica.getCognome().getBytes())))
                    .appendParam("day", instance.get(Calendar.DAY_OF_MONTH))
                    .appendParam("month", instance.get(Calendar.MONTH))
                    .appendParam("year", instance.get(Calendar.YEAR))
                    .appendParam("gender", String.valueOf(anagrafica.getSesso()))
                    .appendParam("city", nomeCitta)
                    .appendParam("idcity", idCitta));
        } catch (Exception e) {
            throw new InternalMsaException();
        }
    }
}
