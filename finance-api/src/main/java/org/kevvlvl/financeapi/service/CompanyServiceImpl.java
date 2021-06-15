package org.kevvlvl.financeapi.service;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import org.kevvlvl.financeapi.dto.CompanyDto;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CompanyServiceImpl implements CompanyService {

    private List<CompanyDto> dtos;

    private RxHttpClient client;

    @Value("${micronaut.application.audit-api-endpoint}")
    private String auditApiHost;

    @Inject
    public CompanyServiceImpl(RxHttpClient client) {

        this.client = client;

        dtos = new ArrayList<>();
        dtos.add(new CompanyDto("Altavista", "IT"));
        dtos.add(new CompanyDto("PillPusher", "Health"));
        dtos.add(new CompanyDto("BrandTop", "Marketing"));
    }

    public List<CompanyDto> getCompanies() {

        // Call the Audit API to log the response
        HttpResponse<String> auditResponse = this.client.toBlocking().exchange(HttpRequest.POST(auditApiHost, dtos.toString()));

        if(auditResponse != null && auditResponse.getStatus().equals(HttpStatus.OK))
            return dtos;
        else
            return null;
    }
}