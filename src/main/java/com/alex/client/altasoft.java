package com.alex.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import org.fusesource.restygwt.client.Defaults;


public class altasoft implements EntryPoint {
  public void onModuleLoad() {
    useCorrectRequestBaseUrl();
    RootPanel.get("gwtContainer").add(new TreePanel());
  }

  private void useCorrectRequestBaseUrl() {
    if (isDevelopmentMode()) {
      // our spring boot server is running at port 80. If we don't change the url
      // resty gwt would use the gwt servlet port
      Defaults.setServiceRoot("http://localhost:8080");
    } else {
      // in production our compiled javascript code gets copied into
      // a 'springbootgwt' folder into the static resources. So if we
      // dont change the default url resty gwt would put the folders name
      // to the base url
      Defaults.setServiceRoot(GWT.getHostPageBaseURL());
    }
  }

  private static native boolean isDevelopmentMode()/*-{
    return typeof $wnd.__gwt_sdm !== 'undefined';
  }-*/;
}
