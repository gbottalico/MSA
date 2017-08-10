package msa.application.service.util;

import msa.application.dto.sinistro.anagrafica.BaseAnagraficaDTO;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by simon.calabrese on 10/08/2017.
 */
public class UtilsService {

    private static final List<String> vocali = Arrays.asList("A", "E", "I", "O", "U");
    private static final List<String> consonanti = Arrays.asList("B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z");

    public String calcolaCodiceFiscale(final BaseAnagraficaDTO anagrafica) {


        return null;
    }

    Function<String, String> stringToConsonantiOrConsPiuVoc = stringa -> {
        if(stringa.length() < 3) {
            return stringa.concat("XXX").substring(0,3);
        }

        final String cons = Arrays.stream(ArrayUtils.toObject(stringa.toCharArray()))
                .map(String::valueOf)
                .filter(consonanti::contains)
                .collect(Collectors.joining());

        if (cons.length() > 3) {
            return cons.substring(0, 3);
        } else {
            return cons.concat(Arrays.stream(ArrayUtils.toObject(stringa.toCharArray()))
                    .map(String::valueOf)
                    .filter(vocali::contains)
                    .collect(Collectors.joining())).substring(0,3);
        }
    };

    private String modificaNC(String stringa, boolean cod) {
        String nuovastringa = "";
        stringa = stringa.trim().toUpperCase();

        String consonanti = getConsonanti(stringa);      // Ottengo tutte le consonanti e tutte le vocali della stringa
        String vocali = getVocali(stringa);

        // Controlla i possibili casi
        if (consonanti.length() == 3) {                   // La stringa contiene solo 3 consonanti, quindi ho gia" la modifica
            nuovastringa = consonanti;
        }
        // Le consonanti non sono sufficienti, e la stinga e" più lunga o
        // uguale a 3 caratteri [aggiungo le vocali mancanti]
        else if ((consonanti.length() < 3) && (stringa.length() >= 3)) {
            nuovastringa = consonanti;
            nuovastringa = aggiungiVocali(nuovastringa, vocali);
        }
        // Le consonanti non sono sufficienti, e la stringa
        //contiene meno di 3 caratteri [aggiungo consonanti e vocali, e le x]
        else if ((consonanti.length() < 3) && (stringa.length() < 3)) {
            nuovastringa = consonanti;
            nuovastringa += vocali;
            nuovastringa = aggiungiX(nuovastringa);
        }
        // Le consonanti sono in eccesso, prendo solo le
        //prime 3 nel caso del cognome; nel caso del nome la 0, 2, 3
        else if (consonanti.length() > 3) {
            // true indica il nome e false il cognome
            if (!cod) nuovastringa = consonanti.substring(0, 3);
            else nuovastringa = consonanti.charAt(0) + "" + consonanti.charAt(2) + "" + consonanti.charAt(3);
        }

        return nuovastringa;
    }
}
