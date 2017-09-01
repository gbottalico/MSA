package msa.application.commons;

import msa.application.dto.domain.CodiciCatastaliPojo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Function;

/**
 * Created by simon.calabrese on 24/07/2017.
 */
public class Constants {
    public static final Function<String,String[]> SPLIT_LNES_BY_COMMA= row -> row.split(";");
    public static final Function<String[],CodiciCatastaliPojo> ARRAY_TO_CODICI_CATASTALI = array -> new CodiciCatastaliPojo(array[0],array[1],array[2]);
    public static final Integer ID_BAREMES_NON_CLASSIFICABILE = 0;
    public static final Integer MAX_NUM_CONTROPARTE_CON_DANNI = 1;
    public static final Function<Date,String> DATE_TO_STRING_DASH = e -> {
        LocalDateTime date = e.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return date.format(formatter);
    };
    public static final Function<Date,String> DATE_TO_STRING_DOT = e -> {
        LocalDateTime date = e.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh.mm.ss");
        return date.format(formatter);
    };

    public static final String MATCHER_CODICE_FISCALE_VALIDO = "[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]";
}
