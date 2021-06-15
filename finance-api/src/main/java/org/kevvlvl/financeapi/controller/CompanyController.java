package org.kevvlvl.financeapi.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.tracing.annotation.ContinueSpan;
import org.kevvlvl.financeapi.dto.CompanyDto;
import org.kevvlvl.financeapi.service.CompanyService;

import javax.inject.Inject;
import java.util.List;

@Controller("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Inject
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Get(value = "/list", produces = MediaType.APPLICATION_JSON)
    @ContinueSpan
    public List<CompanyDto> getCompanies() {
        return this.companyService.getCompanies();
    }
}
