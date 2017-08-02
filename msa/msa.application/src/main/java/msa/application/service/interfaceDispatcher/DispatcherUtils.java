package msa.application.service.interfaceDispatcher;

import msa.application.service.base.BaseService;
import msa.domain.object.dispatcher.DispatcherDO;
import msa.infrastructure.base.repository.domain.CasaRegoleBaseRepository;
import msa.infrastructure.persistence.domain.CasaRegoleDBO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
public class DispatcherUtils extends BaseService{
    @Autowired
    private CasaRegoleBaseRepository casaRegoleBaseRepository;

    private static final Map<String, Function<CasaRegoleDBO, String>> subDispatching = new HashMap<>();
    private static List<String> toSubDispatching = Arrays.asList("M22", "M23");

    static {
        subDispatching.put("M22", e -> {
            if (e.getCarrozzeriaConvenzionata()) {
                return "0";
            } else if (!e.getCarrozzeriaConvenzionata() && e.getIncaricoPerito()) {
                return "1";
            } else {
                return "2";
            }
        });
        subDispatching.put("M23", e -> {
            if (e.getIncaricoPerito()) {
                return "0";
            } else {
                return "1";
            }
        });
    }

    protected DispatcherDO getParameterIfSubDispatching(final DispatcherDO dispatcherDO) {
        if (isSubDispatching(dispatcherDO)) {
            dispatcherDO.setParam(casaRegoleBaseRepository.findAll().stream()
                    .filter(e -> e.getIdCompagnia().equals(dispatcherDO.getParam()))
                    .map(subDispatching.get(dispatcherDO.getThisView()))
                    .findFirst()
                    .orElse(null));
            return dispatcherDO;
        } else {
            return dispatcherDO;
        }
    }

    private Boolean isSubDispatching(final DispatcherDO dispatcherDO) {
        return DispatcherUtils.toSubDispatching.contains(dispatcherDO.getThisView());
    }

}
