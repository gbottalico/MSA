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

        private MsaFunction<Map< String, String>, String> M23Tree = map -> {
            try {
                return casaRegoleBaseRepository.findAll().stream()
                        .filter(e -> e.getIdCompagnia().equals(map.get("compagnia")))
                        .map(e -> e.getIncaricoPerito() ? "0" : "1").findFirst().orElse(null);
            }catch (Exception e) {
                throw new InternalMsaException();
            }
        };
        private MsaFunction<Map< String, String>, String> M22Tree = map -> {
           try {
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

        private String getCodeByView(DispatcherDO dispatcherDO) throws InternalMsaException {
            if(tree.keySet().contains(dispatcherDO.getThisView())) {
                return tree.get(dispatcherDO.getThisView()).apply(dispatcherDO.getParamMap());
            } else {
                return null;
            }
        }
    }

}
