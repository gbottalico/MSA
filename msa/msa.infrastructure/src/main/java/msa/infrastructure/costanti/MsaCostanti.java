package msa.infrastructure.costanti;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class MsaCostanti {
        public static final Character PARAM_COMUNE = 'C';
        public static final Character PARAM_NAZIONE = 'N';
        public static final Character PARAM_PROVINCIA = 'P';
        public static final Character PARAM_CAP ='A';

        public static final Integer COD_NAZIONE_ITALIA = 1;
        public static final Integer COD_NAZIONE_SAN_MARINO = 272;
        public static final Integer COD_NAZIONE_VATICANO = 72;
        public static final String COD_RUOLO_TERZO_TRASPORTATO_CLIENTE = "6";
        public static final String COD_RUOLO_TERZO_TRASPORTATO_CONTROPARTE = "7";
        public static final String COD_GARANZIA_RCA = "rca";
        public static final Integer COD_RUOLO_LEGALE = 21;
        public static final Integer COD_RUOLO_CONTRAENTE = 22;
        public static final Integer COD_RUOLO_CONTROPARTE = 23;
        public static final Integer COD_RUOLO_PROPRIETARIO = 24;
        public static final String COD_PARTITA_DANNO = "PD";

        public static final Supplier<Date> dataInizioCard = () -> {
                final Calendar ca = Calendar.getInstance();
                ca.set(1970,1,1);
                return ca.getTime();
        };

        public static final Predicate<Integer> nazioneInCard = e -> Stream.of(COD_NAZIONE_ITALIA,COD_NAZIONE_SAN_MARINO,COD_NAZIONE_VATICANO)
                .anyMatch(elem -> Integer.compare(elem,e) == 0);


}