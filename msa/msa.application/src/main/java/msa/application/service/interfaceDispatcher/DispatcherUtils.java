package msa.application.service.interfaceDispatcher;

import msa.application.commons.functions.FunctionUtils;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.application.service.sinistri.MsaFunction;
import msa.domain.object.dispatcher.DispatcherDO;
import msa.domain.object.sinistro.SinistroDO;
import msa.infrastructure.base.repository.domain.CasaRegoleBaseRepository;
import msa.infrastructure.repository.SinistriRepository;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
public class DispatcherUtils extends BaseService {
    @Autowired
    private CasaRegoleBaseRepository casaRegoleBaseRepository;
    @Autowired
    private SinistriRepository sinistriRepository;

    protected String getCodeForNextView(DispatcherDO dispatcherDO) throws InternalMsaException {
        return new ComplexTree().getCodeByView(dispatcherDO);
    }


    private class ComplexTree {
        private Map<String, MsaFunction<Map<String, String>, String>> tree;

        private MsaFunction<Map< String, String>, String> M15Tree = map -> {
            if (map.get("condition").equalsIgnoreCase("S"))
                return "0";
            else {
                try {
                    SinistroDO numSinistroProvv = sinistriRepository.getSinistroByNumProvv(FunctionUtils.numberConverter(map.get("numSinistroProvv"), Integer::valueOf));
                    if (numSinistroProvv.getEventoRca().getNumVeicoli() > 1) {
                        return "1";
                    } else {
                        return "2";
                    }
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            }
        };
        private MsaFunction<Map< String, String>, String> M16Tree = map -> {
            try {
                SinistroDO numSinistroProvv = sinistriRepository.getSinistroByNumProvv(FunctionUtils.numberConverter(map.get("numSinistroProvv"), Integer::valueOf));
                if (numSinistroProvv.getEventoRca().getNumVeicoli() > 1) {
                    return "0";
                } else {
                    return "1";
                }
            } catch (Exception e) {
                throw new InternalMsaException();
            }
        };
        private MsaFunction<Map< String, String>, String> M23Tree = map -> casaRegoleBaseRepository.findAll().stream()
                .filter(e -> e.getIdCompagnia().equals(map.get("compagnia")))
                .map(e -> e.getIncaricoPerito() ? "0" : "1").findFirst().orElse(null);
        private MsaFunction<Map< String, String>, String> M22Tree = map -> {
            String param = casaRegoleBaseRepository.findAll().stream()
                    .filter(e -> e.getIdCompagnia().equals(map.get("compagnia")))
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
        };

        private ComplexTree() {
            initTree();
        }

        private void initTree() {
            tree = new HashMap<>();
            tree.put("M15", M15Tree);
            tree.put("M16", M16Tree);
            tree.put("M22", M22Tree);
            tree.put("M23", M23Tree);
        }

        private String getCodeByView(DispatcherDO dispatcherDO) throws InternalMsaException {
            if(MapUtils.isEmpty(dispatcherDO.getParamMap())){
                return null;
            }
            if(tree.keySet().contains(dispatcherDO.getThisView())) {
                return tree.get(dispatcherDO.getThisView()).apply(dispatcherDO.getParamMap());
            } else {
                return dispatcherDO.getParamMap().get("condition");
            }
        }
    }

}
