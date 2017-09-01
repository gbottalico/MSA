package msa.application.service.util;

import msa.application.commons.Constants;
import msa.application.config.Message;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.sinistro.anagrafica.BaseAnagraficaDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.application.service.enumerator.Api;
import msa.domain.Converter.FunctionUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.function.Function;


/**
 * Created by simon.calabrese on 10/08/2017.
 */
@Service
public class UtilsService extends BaseService {


    private Function<String, String> toEncodedString = (String s) -> new String(Base64.getEncoder()
            .encode(s.replaceAll(
                    "/(^[\\s\\xA0]+|[\\s\\xA0]+$)/g",
                    "")
                    .getBytes()
            )
    ).replace("+", "||");

    private static final String[] patternToRemoveInCodError = {"ERR: ", " Impossibile calcolare il Codice Fiscale_[0-9]{2}"};

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
                    .getNodeValue()
                    .trim();
            Calendar instance = Calendar.getInstance();
            instance.setTime(anagrafica.getDataNascita());
            final String resultCall = doPostCallFormData(Api.CODICE_FISCALE, getFormDataBuilder()
                    .appendParam("name", converter.enrichObject(anagrafica.getNome(), toEncodedString))
                    .appendParam("surname", converter.enrichObject(anagrafica.getCognome(), toEncodedString))
                    .appendParam("day", instance.get(Calendar.DAY_OF_MONTH))
                    .appendParam("month", instance.get(Calendar.MONTH) + 1)
                    .appendParam("year", instance.get(Calendar.YEAR))
                    .appendParam("gender", String.valueOf(anagrafica.getSesso()))
                    .appendParam("city", nomeCitta)
                    .appendParam("idcity", idCitta));
            if (resultCall.matches(Constants.MATCHER_CODICE_FISCALE_VALIDO)) {
                return resultCall;
            } else {

                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR,
                        "MSA014",
                        s -> s.concat(FunctionUtils.removePatternInList(resultCall, patternToRemoveInCodError))));
            }
        } catch (InternalMsaException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalMsaException();
        }
    }
}
