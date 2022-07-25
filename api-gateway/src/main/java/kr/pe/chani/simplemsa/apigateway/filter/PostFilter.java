package kr.pe.chani.simplemsa.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class PostFilter extends ZuulFilter {

	@Override
	/** 필수 오버라이드, "pre" / "route" / "post" 문자열을 지정하여 해당 필터가 어떤 필터인지 결정*/
	public String filterType() {
		return "post";
	}

	@Override
	/**Pre1Filter, Pre2Filter 등과 같이 각각 여러개의 필터를 구현한다면 필터들의 실행 순서를 결정
	 * 이때 각 필터들의 실행 순서를 정해주는 것이 filterOrder() 메소드 입니다.
	 * 숫자가 작은 필터가 먼저 수행*/
	public int filterOrder() {
		return 1;
	}

	@Override
	/**
	 *  해당 필터가 작동할지 여부
	 *  만약 false를 리턴하면 런타임 시에 해당 필터는 동작하지 않음*/
	public boolean shouldFilter() {
		return true;
	}

	@Override
	/**요청 응답 전후에 처리할 특정 로직을 여기에서 구현*/
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		log.info("===== START Gateway Post Filter. =====");

		int httpStatus = ctx.getResponse().getStatus();
		String respHttpBody = getRespHttpBody(ctx);
		log.info("[Post Filter] HttpStatus : {}, Response HttpBody : {}", httpStatus, respHttpBody);

		return null;
	}


	private String getRespHttpBody(RequestContext ctx) {
		String responseBody = ctx.getResponseBody();
		if (responseBody == null) {
			InputStream is  = ctx.getResponseDataStream();
			try {
				byte[] ib = StreamUtils.copyToByteArray(is);
				responseBody = new String(ib, StandardCharsets.UTF_8);
				ctx.setResponseDataStream(new ByteArrayInputStream(ib));
			} catch (IOException e) {
				log.error("It is failed to obtainning Response Http Body.", e);
			}
		}
		return responseBody;
	}
}