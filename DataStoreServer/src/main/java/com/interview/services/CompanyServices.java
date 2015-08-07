package com.interview.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("companyService")
public class CompanyServices {

  public List<String> getRelatedCompaniesForSkills(List<String> skills) {
    List<String> list = new ArrayList<String>();

    list.add("Microsoft");
    list.add("IBM");

    return list;
  }

}
