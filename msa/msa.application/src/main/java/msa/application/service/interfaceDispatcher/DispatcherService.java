package msa.application.service.interfaceDispatcher;

import com.gs.collections.impl.block.factory.Comparators;
import msa.application.config.BaseDTO;
import msa.application.dto.dispatcher.DispatcherDTO;
import msa.application.exceptions.InternalMsaException;
import msa.domain.object.dispatcher.DispatcherDO;
import msa.domain.object.dispatcher.NavigazioneViewDO;
import msa.infrastructure.repository.DispatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@Service
public class DispatcherService extends DispatcherUtils {

    @Autowired
    private DispatcherRepository dispatcherRepository;

    private static final String START_VIEW = "M11";

    //Todo FIxME dopo 26 si deve fermare
    public BaseDTO<List<String>> getNextInterface(final DispatcherDTO view) throws InternalMsaException {
        DispatcherDO dispatcherDO = converter.convertObject(view, DispatcherDO.class);
        final Optional<NavigazioneViewDO> allViewBySinistro = dispatcherRepository.getAllViewBySinistro(view.getNumSinistroProvv());
        final String lastView = allViewBySinistro
                .map(e -> e.getViewNavigate()
                        .stream()
                        .max(Comparators.naturalOrder())
                        .orElse(null))
                .orElse(START_VIEW);
        dispatcherDO.setLastView(lastView);
        final NavigazioneViewDO navigazioneAggiornata;
        if (lastView.equalsIgnoreCase(START_VIEW)) {
            navigazioneAggiornata = new NavigazioneViewDO();
            navigazioneAggiornata.setNumSinistro(view.getNumSinistroProvv());
            navigazioneAggiornata.setViewNavigate(new ArrayList<>(Arrays.asList(lastView, dispatcherRepository.getNextInterface(dispatcherDO))));
        } else {
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
            navigazioneAggiornata = newDispatcher.map(e -> dispatcherRepository.getNextInterface(e))
                    .map(e -> {
                        final NavigazioneViewDO navigazioneViewDO = allViewBySinistro.get();
                        navigazioneViewDO.getViewNavigate().add(e);
                        return navigazioneViewDO;
                    }).orElse(null);
        }
        dispatcherRepository.persistInViewNavigated(navigazioneAggiornata);
        return new BaseDTO<>(navigazioneAggiornata.getViewNavigate()
                .stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList()));
    }
}
