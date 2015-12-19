package com.interview.services;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileSearchImpl implements IProfileSearch {
  @Override
  public JSONObject getProfiles(String key) {
    JSONObject json = new JSONObject();
    try {
      json.put("name", "Raj");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return json;
  }
}
