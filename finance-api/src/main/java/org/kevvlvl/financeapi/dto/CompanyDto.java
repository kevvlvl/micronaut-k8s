package org.kevvlvl.financeapi.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class CompanyDto {

    public CompanyDto(String companyName, String industry) {
        this.companyName = companyName;
        this.industry = industry;
    }

    private final String companyName;
    private final String industry;

    public String getCompanyName() {
        return companyName;
    }

    public String getIndustry() {
        return industry;
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
                "companyName='" + companyName + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }
}
