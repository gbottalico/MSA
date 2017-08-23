package msa.application.service.interfaceDispatcher;

import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.application.service.sinistri.MsaFunction;
import msa.domain.object.dispatcher.DispatcherDO;
import msa.infrastructure.base.repository.domain.CasaRegoleBaseRepository;
import msa.infrastructure.repository.SinistriRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
public class DispatcherUtils extends BaseService {
    @Autowired
    private CasaRegoleBaseRepository casaRegoleBaseRepository;
    @Autowired
    private SinistriRepository sinistriRepository;

    protected Optional<String> getCodeForNextView(DispatcherDO dispatcherDO) throws InternalMsaException {
        return new ComplexTree().getCodeByView(dispatcherDO);
    }


    private class ComplexTree {
        private Map<String, MsaFunction<Integer, String>> tree;

        private MsaFunction<Integer, Integer> getSinistro =
                numSinis -> {
                    try {
                        return sinistriRepository.getSinistroByNumProvv(numSinis).getCompagnia();
                    } catch (Exception e) {
                        throw new InternalMsaException();
                    }
                };


        private MsaFunction<Integer, String> M23Tree = compagnia -> {
            try {
                return casaRegoleBaseRepository.findAll().stream()
                        .filter(e -> e.getIdCompagnia().equals(String.valueOf(compagnia)))
                        .map(e -> e.getIncaricoPerito() ? "0" : "1").findFirst().orElse(null);
            } catch (Exception e) {
                throw new InternalMsaException();
            }
        };
        private MsaFunction<Integer, String> M22Tree = compagnia -> {
            try {
                String param = casaRegoleBaseRepository.findAll().stream()
                        .filter(e -> e.getIdCompagnia().equals(String.valueOf(compagnia)))
                        .map(e -> {
                            if (e.getCarrozzeriaConvenzionata()) {
                                return "0";
                            } else if (!e.getCarrozzeriaConvenzionata() && e.getIncaricoPerito()) {
                                return "1";
                            } else {
                                return "2";
                            }
                        }).findFirst().orElse(null);
                return param;
            } catch (Exception e) {
                throw new InternalMsaException();
            }
        };

        private ComplexTree() {
            initTree();
        }

        private void initTree() {
            tree = new HashMap<>();
            tree.put("M22", M22Tree);
            tree.put("M23", M23Tree);
        }

        private Optional<String> getCodeByView(DispatcherDO dispatcherDO) throws InternalMsaException {
            if (tree.keySet().contains(dispatcherDO.getLastView())) {
                return Optional.of(getSinistro.andThen(tree.get(dispatcherDO.getLastView())).apply(dispatcherDO.getNumSinistroProvv()));
            } else {
                return Optional.empty();
            }
        }
    }

}
