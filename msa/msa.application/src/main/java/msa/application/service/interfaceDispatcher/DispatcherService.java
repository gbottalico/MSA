package msa.application.service.interfaceDispatcher;

import com.gs.collections.impl.block.factory.Comparators;
import msa.application.config.BaseDTO;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.dispatcher.DispatcherDTO;
import msa.application.exceptions.InternalMsaException;
import msa.domain.object.dispatcher.DispatcherDO;
import msa.domain.object.dispatcher.NavigazioneViewDO;
import msa.infrastructure.repository.DispatcherRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@Service
public class DispatcherService extends DispatcherUtils {

    @Autowired
    private DispatcherRepository dispatcherRepository;

    private static final String START_VIEW = "M11";

    public BaseDTO<Map<Integer, String>> getAllInterface(final Integer numSinistroProvv) throws InternalMsaException {
        try {
            return new BaseDTO<>(new TreeMap<>(getAllInterface(numSinistroProvv, Boolean.TRUE).map(NavigazioneViewDO::getViewNavigate).orElseGet(HashMap::new)));
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA011"));
        }
    }

    private Optional<NavigazioneViewDO> getAllInterface(final Integer numSinistroProvv, Boolean... toDistinct) {
        return dispatcherRepository.getAllViewBySinistro(numSinistroProvv);
    }

    public BaseDTO<Map<Integer, String>> getNextInterfaceAndPersist(final DispatcherDTO view) throws InternalMsaException {
        try {
            final NavigazioneViewDO nextInterface = getNextInterface(view);
            dispatcherRepository.persistInViewNavigated(nextInterface);
            return new BaseDTO<>(new TreeMap<>(nextInterface.getViewNavigate()));
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA010"));
        }
    }

    public NavigazioneViewDO getNextInterface(final DispatcherDTO view) throws InternalMsaException {
        final DispatcherDO dispatcherDO = converter.convertObject(view, DispatcherDO.class);
        final Optional<NavigazioneViewDO> allViewBySinistro = getAllInterface(view.getNumSinistroProvv(), Boolean.TRUE);
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
            final Map<Integer, String> starterMap = new HashMap<>();
            starterMap.put(0, START_VIEW);
            starterMap.put(getNewIndex(starterMap), dispatcherRepository.getNextInterface(dispatcherDO).orElseGet(defaultGet));
            navigazioneAggiornata.setViewNavigate(starterMap);
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
        //TODO FIXME!!!!!!!!!!
        if (navigazioneAggiornata.getViewNavigate().entrySet().stream().map(Map.Entry::getValue).filter(e -> e.equalsIgnoreCase("M22")).count() == 1L) {
            navigazioneAggiornata.getViewNavigate().put(6, "M23");
        }
        return navigazioneAggiornata;
    }

    public Integer resetView(final String garanziaSelected, final Integer numSinistroProvv) throws InternalMsaException {
        final DispatcherDO dispatcherDO = new DispatcherDO();
        dispatcherDO.setGaranziaSelected(garanziaSelected);
        dispatcherDO.setNumSinistroProvv(numSinistroProvv);
        return dispatcherRepository.resetViewByNumSinistro(dispatcherDO);
    }

    private Integer getNewIndex(Map<Integer, String> indexViews) {
        return indexViews.keySet().stream().max(Comparator.naturalOrder()).map(e -> e + 1).orElse(null);
    }

    public Integer getPercentualeAvanzamento(DispatcherDTO dispatcherDTO) throws InternalMsaException {
        final List<Pair<String, Integer>> pairs = dispatcherRepository.getAllInterfaceByGaranzia(dispatcherDTO.getGaranziaSelected()).orElse(null);
        final Map<Integer, String> viewNavigate = getNextInterface(dispatcherDTO).getViewNavigate();
        final String integerStringEntry = viewNavigate.entrySet()
                .stream()
                .limit(viewNavigate.entrySet().size()-1)
                .map(Map.Entry::getValue)
                .max(Comparators.naturalOrder()).orElse(null);

        return pairs.stream().filter(e -> e.getKey().equals(integerStringEntry)).map(Pair::getValue).findFirst().orElse(100);

    }
}
