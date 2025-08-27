package com.merkana.core.service;

import com.merkana.core.domain.bo.StoreBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class StoreService {

    @NonNull
    public Optional<StoreBO> findById(@NonNull String id) {
        return Optional.empty();
    }

}
