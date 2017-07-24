package msa.domain.Converter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MsaConverter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public <T, S> T convertObject(S source, Class<T> claz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        T dest = claz.newInstance();
        BeanUtils.copyProperties(dest, source);
        return dest;
    }

    public <T, S> List<T> convertList(List<S> source, Class<T> claz) {
        Function<S,T> copyBeanFunction = s -> {
            try {
                T value = claz.newInstance();
                BeanUtils.copyProperties(value,s);
                return value;
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                logger.error(e.getMessage());
                return null;
            }
        };
        return convertObject(source,copyBeanFunction);
    }

    public <T, S> T convertObject(S source, Function<S, T> conversionFunction) {
        return conversionFunction.apply(source);
    }

    public <T, S> List<T> convertObject(List<S> source, Function<S, T> conversionFunction) {
        return source.stream().map(e -> convertObject(e, conversionFunction)).collect(Collectors.toList());
    }

}
