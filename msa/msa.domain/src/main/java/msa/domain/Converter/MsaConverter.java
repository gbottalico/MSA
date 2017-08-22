package msa.domain.Converter;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Component
public class MsaConverter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ModelMapper mapper;

    public <T, S> T convertObject(S source, Class<T> claz) {
        return mapper.map(source, claz);
    }

    public <T, S> List<T> convertList(List<S> source, Class<T> claz) {
        return convertObject(source, (S s) -> convertObject(s,claz));
    }

    public <T, S> List<T> convertList(List<S> source, Function<S,Class<T>> getClassFunction) {
        return convertObject(source, (S s) -> convertObject(s,getClassFunction.apply(s)));
    }

    public <T, S> T convertObject(S source, Function<S, T> conversionFunction) {
        return conversionFunction.apply(source);
    }

    public <T> T enrichObject(T source, Function<T, T> conversionFunction) {
        return conversionFunction.apply(source);
    }

    public <T, S> List<T> convertObject(List<S> source, Function<S, T> conversionFunction) {
        return source.stream().map(e -> convertObject(e, conversionFunction)).collect(Collectors.toList());
    }

}
