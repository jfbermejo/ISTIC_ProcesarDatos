package io.swagger.api.factories;

import io.swagger.api.UsuarioApiService;
import io.swagger.api.impl.UsuarioApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-26T08:45:53.774Z[GMT]")
public class UsuarioApiServiceFactory {
    private final static UsuarioApiService service = new UsuarioApiServiceImpl();

    public static UsuarioApiService getUsuarioApi() {
        return service;
    }
}
