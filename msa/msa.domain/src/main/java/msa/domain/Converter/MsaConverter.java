package msa.domain.Converter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MsaConverter {
	
	public <T,S> T convertObject(S source, Class<T> claz ) throws IllegalAccessException, InvocationTargetException, InstantiationException{
		T dest = claz.newInstance();
		BeanUtils.copyProperties(dest, source);
		return dest;
	}
	
	public <T, S> List<T> convertList(List<S> source,Class<T> claz  ) throws InstantiationException, IllegalAccessException, InvocationTargetException{
		List<T> listaDto = new ArrayList<T>();
		for(int i = 0;i<source.size();i++) {
			T nazione = claz.newInstance();
			BeanUtils.copyProperties(nazione,source.get(i));
			listaDto.add(nazione);
		}
		return listaDto;
	}

}
