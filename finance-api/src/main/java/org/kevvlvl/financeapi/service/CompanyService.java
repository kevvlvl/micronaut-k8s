package org.kevvlvl.financeapi.service;

import org.kevvlvl.financeapi.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> getCompanies();
}
