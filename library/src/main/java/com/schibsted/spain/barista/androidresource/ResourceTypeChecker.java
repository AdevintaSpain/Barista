package com.schibsted.spain.barista.androidresource;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;

public class ResourceTypeChecker {

  private static final String ID_TYPE = "id";
  private static final String STRING_TYPE = "string";

  private final Resources resources;

  public ResourceTypeChecker() {
    this.resources = InstrumentationRegistry.getTargetContext().getResources();
  }

  public boolean isIdResource(int id) {
    return resources.getResourceTypeName(id).equals(ID_TYPE);
  }

  public boolean isStringResource(int id) {
    return resources.getResourceTypeName(id).equals(STRING_TYPE);
  }
}
