package org.kevvlvl.auditapi.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.tracing.annotation.ContinueSpan;
import org.kevvlvl.auditapi.service.AuditService;

import javax.inject.Inject;

@Controller("/audit")
public class AuditController {

    private final AuditService auditService;

    @Inject
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @ContinueSpan
    public HttpResponse<String> getCompanies(@Body String returnedPayload) {
        this.auditService.auditAction(returnedPayload);
        return HttpResponse.ok();
    }
}
