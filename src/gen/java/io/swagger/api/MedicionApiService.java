package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Medicion;


import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-26T08:45:53.774Z[GMT]")

public abstract class MedicionApiService {
    
    public abstract Response consultarMedicionUsuario( @NotNull Long usuarioId, @NotNull Long medicionId,SecurityContext securityContext) throws NotFoundException;
    
}

