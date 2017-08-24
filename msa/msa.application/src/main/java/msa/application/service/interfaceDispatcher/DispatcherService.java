package msa.application.service.interfaceDispatcher;

import msa.application.config.BaseDTO;
import msa.application.dto.dispatcher.DispatcherDTO;
import msa.application.exceptions.InternalMsaException;
import msa.domain.Converter.FunctionUtils;
import msa.domain.object.dispatcher.DispatcherDO;
import msa.domain.object.dispatcher.NavigazioneViewDO;
import msa.infrastructure.repository.DispatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@Service
public class DispatcherService extends DispatcherUtils {

    @Autowired
    private DispatcherRepository dispatcherRepository;

    private static final String START_VIEW = "M11";
    private static final Map<Integer, String> DEFAULT_STARTER_MAP = new HashMap<>();

    static {
        DEFAULT_STARTER_MAP.put(0, START_VIEW);
    }

    //Todo FIxME dopo 26 si deve fermare
    public BaseDTO<Map<Integer, String>> getNextInterface(final DispatcherDTO view) throws InternalMsaException {
        final DispatcherDO dispatcherDO = converter.convertObject(view, DispatcherDO.class);
        final Optional<NavigazioneViewDO> allViewBySinistro = dispatcherRepository.getAllViewBySinistro(view.getNumSinistroProvv());
        final String lastView = allViewBySinistro
                .map(e -> e.getViewNavigate().get(e.getViewNavigate().size() - 1))
                .orElse(START_VIEW);
        final Supplier<String> defaultGet = () -> lastView;
        dispatcherDO.setLastView(lastView);
        final NavigazioneViewDO navigazioneAggiornata;
        //Nel caso in cui siamo nel primo step
        if (lastView.equalsIgnoreCase(START_VIEW)) {
            navigazioneAggiornata = new NavigazioneViewDO();
            navigazioneAggiornata.setNumSinistro(view.getNumSinistroProvv());
            DEFAULT_STARTER_MAP.put(getNewIndex(DEFAULT_STARTER_MAP), dispatcherRepository.getNextInterface(dispatcherDO).orElseGet(defaultGet));
            navigazioneAggiornata.setViewNavigate(DEFAULT_STARTER_MAP);
        } else {
            //Step Successivi
            final Optional<String> codeForNextView = getCodeForNextView(dispatcherDO);
            final Optional<DispatcherDO> newDispatcher;
            if (codeForNextView.isPresent()) {
                newDispatcher = codeForNextView.map(e -> {
                    dispatcherDO.setParamCod(e);
                    return dispatcherDO;
                });
            } else {
                newDispatcher = Optional.of(dispatcherDO);
            }
            newDispatcher.map(e -> dispatcherRepository.getNextInterface(e));
            navigazioneAggiornata = newDispatcher.map(e -> dispatcherRepository.getNextInterface(e))
                    .map(e -> {
                        final NavigazioneViewDO navigazioneViewDO = allViewBySinistro.get();
                        if (e.isPresent())
                            navigazioneViewDO.getViewNavigate().put(getNewIndex(navigazioneViewDO.getViewNavigate()), e.orElseGet(defaultGet));
                        return navigazioneViewDO;
                    }).orElse(null);
        }
        dispatcherRepository.persistInViewNavigated(navigazioneAggiornata);
        return new BaseDTO<>(new TreeMap<>(navigazioneAggiornata.getViewNavigate()));
    }

    private Integer getNewIndex(Map<Integer, String> indexViews) {
        return indexViews.keySet().stream().max(Comparator.naturalOrder()).map(e -> e + 1).orElse(null);
    }
}
