package com.merkana.web.config.interceptor;

import com.merkana.core.ctx.StoreContext;
import com.merkana.core.domain.bo.StoreBO;
import com.merkana.core.exception.DefaultErrorCode;
import com.merkana.core.exception.MerkanaException;
import com.merkana.core.service.StoreService;
import com.merkana.core.util.StringUtils;
import com.merkana.web.util.CustomHeaders;
import com.merkana.web.util.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class StoreHeaderInterceptor implements HandlerInterceptor {

    private final StoreService storeService;

    public StoreHeaderInterceptor(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {

        String storeId = request.getHeader(CustomHeaders.X_STORE);

        if (StringUtils.isEmpty(storeId)) {
            throw new MerkanaException(DefaultErrorCode.REQUIRED_HEADER, CustomHeaders.X_STORE);
        }

        StoreBO store = storeService.findById(storeId)
                .orElseThrow(() -> new MerkanaException(DefaultErrorCode.NOT_FOUND, "store", "id"));

        StoreContext.setContext(StoreContext.builder()
                .storeId(store.getId())
                .build());

        String clientIp = HttpUtils.getClientIp(request);
        log.debug("Request from IP: {} routed to store: {} for URI: {}", clientIp, storeId, request.getRequestURI());

        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                @NonNull Object handler, Exception ex) throws Exception {

        StoreContext.clearContext();
    }

}
