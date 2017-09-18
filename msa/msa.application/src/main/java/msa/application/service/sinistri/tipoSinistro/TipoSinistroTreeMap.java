package msa.application.service.sinistri.tipoSinistro;

import msa.application.exceptions.InternalMsaException;
import msa.domain.object.sinistro.BaseSinistroDO;
import msa.domain.object.sinistro.SinistroRcaDO;
import msa.domain.object.enums.TipiSinisto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by simon.calabrese on 11/09/2017.
 */
@Component
public class TipoSinistroTreeMap<T extends BaseSinistroDO> extends CalcoloTipoSinistroFunctions {

    private LeafRca leafRca;
    private LeafOther<T> leafOther;

    public TipiSinisto calcolaTipoSinistro(final T sinistro) throws InternalMsaException {
        init(sinistro);
        if(leafRca != null) {
            return leafRca.navigate(leafRca);
        } else {
            return leafOther.navigate();
        }
    }



    @SuppressWarnings("unchecked")
    private <T extends BaseSinistroDO> void init(T sinistro) throws InternalMsaException {

        //LeafRca isInItaliaAndCTTContrAndContr = new LeafRca();

        if (isRca.apply(sinistro).equals(Boolean.TRUE)) {
            final SinistroRcaDO sinistroRca = (SinistroRcaDO) sinistro;


            this.leafRca = new LeafRca(sinistroRca, 0, numVeicoliEqualsDue,
                    Stream.of(
                            new LeafRca(sinistroRca, 0, isIntaliaAndCTTClienteAndControparte,
                                    Stream.of(
                                            new LeafRca(sinistroRca, 1, getByCidAndPresente, finalStepRca),
                                            new LeafRca(sinistroRca, 0, fromSinistroToRca, finalStepRca)
                                    ).collect(Collectors.toList())),
                            new LeafRca(sinistroRca, 1, isIntaliaAndCardClienteAndControparte,
                                    Stream.of(
                                            new LeafRca(sinistroRca, 0, getByCidAndPresente, finalStepRca),
                                            new LeafRca(sinistroRca, 1, getGetPercCIDFull, finalStepRca)
                                    ).collect(Collectors.toList()))
                    ).collect(Collectors.toList()));
        } else {
            this.leafOther = new LeafOther<>(sinistro,getGaranzia, finalStepOthers); //Todo FIXME!
        }

    }

    private class LeafOther<T> {
        private T sinistro;
        private Function<T, String> functionforResult;
        private Function<String, TipiSinisto> finalStep;

        public LeafOther(T sinistro, Function<T, String> functionforResult, Function<String, TipiSinisto> finalStep) {
            this.sinistro = sinistro;
            this.functionforResult = functionforResult;
            this.finalStep = finalStep;
        }

        private TipiSinisto navigate() {
            return this.finalStep.apply(this.functionforResult.apply(this.sinistro));
        }
    }

    private class LeafRca {
        private SinistroRcaDO sinistro;
        private Integer result;
        private Function<SinistroRcaDO, Integer> functionforResult;
        private List<LeafRca> nextStepsByResult;
        private Function<Integer, TipiSinisto> finalStep;

        public LeafRca(SinistroRcaDO sinistro, Integer result,
                       Function<SinistroRcaDO, Integer> functionforResult,
                       List<LeafRca> nextStepsByResult) {
            this.sinistro = sinistro;
            this.result = result;
            this.functionforResult = functionforResult;
            this.nextStepsByResult = nextStepsByResult;
        }

        public LeafRca(SinistroRcaDO sinistro, Integer result, Function<SinistroRcaDO, Integer> functionforResult, Function<Integer, TipiSinisto> finalStep) {
            this.sinistro = sinistro;
            this.result = result;
            this.functionforResult = functionforResult;
            this.finalStep = finalStep;
        }

        public SinistroRcaDO getSinistro() {
            return sinistro;
        }

        public void setSinistro(SinistroRcaDO sinistro) {
            this.sinistro = sinistro;
        }

        public Integer getResult() {
            return result;
        }

        public void setResult(Integer result) {
            this.result = result;
        }

        public Function<SinistroRcaDO, Integer> getFunctionforResult() {
            return functionforResult;
        }

        public void setFunctionforResult(Function<SinistroRcaDO, Integer> functionforResult) {
            this.functionforResult = functionforResult;
        }

        public List<LeafRca> getNextStepsByResult() {
            return nextStepsByResult;
        }

        public void setNextStepsByResult(List<LeafRca> nextStepsByResult) {
            this.nextStepsByResult = nextStepsByResult;
        }

        public Function<Integer, TipiSinisto> getFinalStep() {
            return finalStep;
        }

        public void setFinalStep(Function<Integer, TipiSinisto> finalStep) {
            this.finalStep = finalStep;
        }
        private TipiSinisto navigate(final LeafRca leaf) {
            if(leaf.getFinalStep() == null) {
                return navigate(leaf.getNextStepsByResult()
                        .stream()
                        .filter(elem -> leaf.getFunctionforResult().apply(sinistro).equals(elem.getResult()))
                        .findFirst()
                        .orElse(null));
            } else {
                return leaf.getFinalStep().apply(functionforResult.apply(sinistro));
            }
        }
    }


}
