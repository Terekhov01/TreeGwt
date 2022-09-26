package com.alex.client;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("altasoft")
public interface TreeService extends RestService {
    @PUT
    public void put(TreeElement element, MethodCallback<Void> callback);
}
