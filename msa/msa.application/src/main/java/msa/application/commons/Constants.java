package msa.application.commons;

import msa.application.dto.domain.CodiciCatastaliPojo;

import java.util.function.Function;

/**
 * Created by simon.calabrese on 24/07/2017.
 */
public class Constants {
    public static final Function<String,String[]> SPLIT_LNES_BY_COMMA= row -> row.split(";");
    public static final Function<String[],CodiciCatastaliPojo> ARRAY_TO_CODICI_CATASTALI = array -> new CodiciCatastaliPojo(array[0],array[1],array[2]);
    public static final Function<String,CodiciCatastaliPojo> ROW_TO_CODICI_CATASTALI = SPLIT_LNES_BY_COMMA.andThen(ARRAY_TO_CODICI_CATASTALI);
}
