package balsa.strategy

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grails.plugin.springsecurity.web.GrailsRedirectStrategy

class BalsaRedirectStrategy extends GrailsRedirectStrategy {
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		String redirectUrl = calculateRedirectUrl(request, url);
		if (redirectUrl.contains('login/auth')) {
			redirectUrl += "?reqURL=" + request.getRequestURL()
		}
		redirectUrl = response.encodeRedirectURL(redirectUrl);

		log.debug("Redirecting to '" + redirectUrl + "'");

		response.sendRedirect(redirectUrl);
	}
}
