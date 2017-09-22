package msa.domain.Converter;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
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

    public <T,S> List<T> convertList(List<S> source, final Class<T> claz, UnaryOperator<T> postConverted) {
        final List<T> t = new LinkedList<>();
        source.stream()
                .parallel()
                .map(e -> convertObject(e,claz)).forEach(e -> t.add(postConverted.apply(e)));
        return t;
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
        return source.stream().parallel().map(conversionFunction).collect(Collectors.toList());
    }
    public <T, U, S> T biConvertObject(S sourceA, U sourceB, BiFunction<S,U,T> biMapper) {
        return biMapper.apply(sourceA,sourceB);
    }
    
    public <T> T clone(T source) {
    	return (T) convertObject(source, source.getClass());
    }

}
