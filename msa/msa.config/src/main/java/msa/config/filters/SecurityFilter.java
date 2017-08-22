package msa.config.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private ObjectMapper objectMapper;



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		boolean openAmEnabled = properties.isOpenAmEnabled();
//		logger.info("\n\nRICHIESTA = " + request.getRequestURL().toString());
//		if (openAmEnabled) {
//			// recupero header
//			String amToken = request.getHeader(openAmMembership.getCookieNameForToken().get(0));
//			NfcUserDTO nfcUser = null;
//			if (openAmMembership.isTokenValid(amToken)) {
//				if (request.getHeader("nfcSessionCookie") != null) {
//					nfcUser = objectMapper.readValue(request.getHeader("nfcSessionCookie"), NfcUserDTO.class);
//					if (nfcUser != null && !StringUtils.isBlank(nfcUser.getUsername())) {
//						CurrentUserContextHolder.setCurrentUser(nfcUser);
//						if (!openAmMembership.getAttribute(amToken, Attributi.uid).equalsIgnoreCase(nfcUser.getUsername())) {
//							rejectRequest(request, response);
//						}
//					}
//				}
//			} else {
//				rejectRequest(request, response);
//			}
//		} else {
//			NfcUserDTO nfcUser = new NfcUserDTO();
//			System.out.println(request.getHeader("nfcSessionCookie"));
//			if (request.getHeader("nfcSessionCookie") != null) {
//				nfcUser = objectMapper.readValue(request.getHeader("nfcSessionCookie"), NfcUserDTO.class);
//				CurrentUserContextHolder.setCurrentUser(nfcUser);
//			} 
//		}
		filterChain.doFilter(request, response);

	}

	/**
	 * Rigetta la request in caso di invalidita' del token creando una response
	 * con codice 401 (UNAUTHORIZED)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private void rejectRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}

}
