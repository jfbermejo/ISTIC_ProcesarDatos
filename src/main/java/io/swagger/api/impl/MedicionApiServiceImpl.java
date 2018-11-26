package io.swagger.api.impl;

import com.google.gson.Gson;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Medicion;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-24T17:48:27.964Z[GMT]")

public class MedicionApiServiceImpl extends MedicionApiService {

    List<Medicion> mediciones;

    public MedicionApiServiceImpl() {

        mediciones = new LinkedList<Medicion>();

        Medicion med1 = new Medicion();
        med1.setMedicionId( "1" );
        med1.setUsuarioId( "1" );
        med1.setTiempo( "25-11-2018 9:15" );
        med1.setPulso( 67 );
        med1.setOxigeno( 99 );
        mediciones.add( med1 );

        Medicion med2 = new Medicion();
        med2.setMedicionId( "2" );
        med2.setUsuarioId( "1" );
        med2.setTiempo( "25-11-2018 9:30" );
        med2.setPulso( 69 );
        med2.setOxigeno( 98 );
        mediciones.add( med2 );

        Medicion med3 = new Medicion();
        med3.setMedicionId( "3" );
        med3.setUsuarioId( "1" );
        med3.setTiempo( "25-11-2018 9:45" );
        med3.setPulso( 75 );
        med3.setOxigeno( 99 );
        mediciones.add( med3 );

        Medicion med4 = new Medicion();
        med4.setMedicionId( "4" );
        med4.setUsuarioId( "2" );
        med4.setTiempo( "25-11-2018 9:15" );
        med4.setPulso( 120 );
        med4.setOxigeno( 95 );
        mediciones.add( med4 );

        Medicion med5 = new Medicion();
        med5.setMedicionId( "5" );
        med5.setUsuarioId( "2" );
        med5.setTiempo( "25-11-2018 9:30" );
        med5.setPulso( 123 );
        med5.setOxigeno( 93 );
        mediciones.add( med5 );

    }

    @Override
    public Response consultarMedicionUsuario( @NotNull Long usuarioId,  @NotNull Long medicionId, SecurityContext securityContext) throws NotFoundException {

        Iterator<Medicion> it = mediciones.iterator();
        Medicion medicionAux;

        String idUsuarioString = usuarioId.toString();
        String idMedicionString = medicionId.toString();

        while ( it.hasNext() ){
            medicionAux = it.next();

            if( medicionAux.getUsuarioId().equals( idUsuarioString ) && medicionAux.getMedicionId().equals( idMedicionString )){

                Gson gson = new Gson();

                String json = gson.toJson(medicionAux);
                return Response.ok(json, MediaType.APPLICATION_JSON).build();

            }
        }


        // medicion no encontrado, devuelve un 404
        return Response.status(Response.Status.NOT_FOUND).entity("Medición no encontrada").build();
    }

}

