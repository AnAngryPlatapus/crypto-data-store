package com.sunny.cds.service.job

import com.sunny.cds.util.loggerDelegate
import org.quartz.DisallowConcurrentExecution
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
@DisallowConcurrentExecution
class BookDataJob(
    private val geminiBookListener: Mono<Void>
) : Job {

    private val log by loggerDelegate

    override fun execute(context: JobExecutionContext?) {
        try {
            log.info("subscribed to ")
//        Flux.from()
            geminiBookListener.subscribe()
            log.info("we should not get here")
        } catch (e: JobExecutionException) {
            log.error(e.message)
            Thread.sleep(3000)
            throw e
        }
    }
}
