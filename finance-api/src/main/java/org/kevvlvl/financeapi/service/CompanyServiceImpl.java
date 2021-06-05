package org.kevvlvl.financeapi.service;

import org.kevvlvl.financeapi.dto.CompanyDto;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CompanyServiceImpl implements CompanyService {

    private List<CompanyDto> dtos;

    public CompanyServiceImpl() {
        dtos = new ArrayList<>();
        dtos.add(new CompanyDto("Altavista", "IT"));
        dtos.add(new CompanyDto("PillPusher", "Health"));
        dtos.add(new CompanyDto("BrandTop", "Marketing"));
    }

    public List<CompanyDto> getCompanies() {
        return dtos;
    }
}
