package msa.application.service.interfaceDispatcher;

import msa.application.config.BaseDTO;
import msa.application.dto.dispatcher.DispatcherDTO;
import msa.application.exceptions.InternalMsaException;
import msa.domain.object.dispatcher.DispatcherDO;
import msa.infrastructure.repository.DispatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@Service
public class DispatcherService extends DispatcherUtils {

    @Autowired
    private DispatcherRepository dispatcherRepository;

    public BaseDTO<DispatcherDTO> getNextInterface(final DispatcherDTO view) throws InternalMsaException {
        DispatcherDO dispatcherDO = converter.convertObject(view, DispatcherDO.class);
        dispatcherDO.setParam(getCodeForNextView(dispatcherDO));
        view.setNextView(dispatcherRepository.getNextInterface(dispatcherDO).getNextView());
        return new BaseDTO<>(view);
    }
}
