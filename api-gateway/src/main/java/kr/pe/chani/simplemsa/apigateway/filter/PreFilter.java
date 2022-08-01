package kr.pe.chani.simplemsa.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Slf4j
@Component
public class PreFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	private static final String AUTHORIZATION_VALUE = "12345";

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		log.info("===== START Pre Filter. =====");

		String reqUri = ctx.getRequest().getRequestURI();
		String reqHttpBody = getReqHttpBody(ctx);
		log.info("[Pre Filter] : Request reqUri : {} HttpBody : {}", reqUri, reqHttpBody);

		//HTTP 헤더의 Authorization 을 추출하여 키값이 유효한경우 api 라우팅, 유효하지 않은경우 401에러
		String authorization = ctx.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
		if(StringUtils.isEmpty(authorization) || !AUTHORIZATION_VALUE.equals(authorization)) {
			respError(ctx);
		}

		//이런식으로 커스텀 헤더를 추가할 수도 있음
		ctx.addZuulRequestHeader("foo", "bar");
		return null;

	}

	private void respError(RequestContext ctx) {
		try {
			ctx.setSendZuulResponse(false);
			ctx.getResponse().sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getReqHttpBody(RequestContext ctx) {
		String reqHttpBody = null;
		try {
			InputStream in = (InputStream) ctx.get("requestEntity");
			if (in == null) {
				in = ctx.getRequest().getInputStream();
				reqHttpBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
			}
		} catch (IOException e) {
			log.error("It is failed to obtainning Request Http Body.", e);
			return "";
		}
		return reqHttpBody;
	}
}