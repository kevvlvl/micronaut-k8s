package org.kevvlvl.financeapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyDto {

    @JsonCreator
    public CompanyDto(@JsonProperty("companyName") String companyName,
                          @JsonProperty("industry") String industry) {
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
}
