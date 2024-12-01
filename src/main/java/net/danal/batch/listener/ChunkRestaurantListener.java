package net.danal.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
public class ChunkRestaurantListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
        log.info("BeforeChunkProcessing... : {}", context.toString());
    }

    @Override
    public void afterChunk(ChunkContext context) {
        log.info("AfterChunkProcessing... : {}", context.toString());
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        log.info("ErrorDuringChunkProcessing... : {}", context.toString());
    }
}
