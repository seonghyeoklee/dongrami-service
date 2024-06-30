package com.dongrami.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheService {
    private final CacheManager cacheManager;

    public List<CacheDto> getCaches() {
        Collection<String> cacheNames = cacheManager.getCacheNames();

        List<CacheDto> response = new ArrayList<>();
        for (String cacheName : cacheNames) {
            Cache cache = cacheManager.getCache(cacheName);

            if (cache != null) {
                ConcurrentHashMap cacheMap = (ConcurrentHashMap) cache.getNativeCache();
                cacheMap.forEach((strKey, strValue) ->
                        response.add(new CacheDto(strKey, strValue))
                );
            }
        }

        return response;
    }

    public void clearCacheByName(String name) {
        for (String cacheNames : cacheManager.getCacheNames()) {
            if (cacheNames.equals(name)) {
                log.info(">>> clear cache : {}", cacheNames);
                cacheManager.getCache(cacheNames).clear();
            }
        }
    }
}
