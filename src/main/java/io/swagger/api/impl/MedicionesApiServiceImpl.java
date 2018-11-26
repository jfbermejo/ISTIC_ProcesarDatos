package io.swagger.api.impl;

import com.google.gson.Gson;
import io.swagger.api.*;

import io.swagger.model.Medicion;
import io.swagger.model.Mediciones;


import java.util.*;

import io.swagger.api.NotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-26T08:45:53.774Z[GMT]")

public class MedicionesApiServiceImpl extends MedicionesApiService {
    
        List<Medicion> mediciones;
    Integer medicionIdContador;

    public MedicionesApiServiceImpl() {

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

        medicionIdContador = 6;

    }

    
    @Override
    public Response consultarMedicionesUsuario( @NotNull Long usuarioId, SecurityContext securityContext) throws NotFoundException {
        Iterator<Medicion> it = mediciones.iterator();
        Medicion medicionAux;
        Mediciones medicionesAux = new Mediciones();

        String id = usuarioId.toString();

        while ( it.hasNext() ){
            medicionAux = it.next();

            if( medicionAux.getUsuarioId().equals( id ) ){

                medicionesAux.add( medicionAux );

            }
        }

        if( medicionesAux.isEmpty() ){
            return Response.status(Response.Status.NOT_FOUND).entity("Mediciones no encontradas para el usuario").build();
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(medicionesAux);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }

    }
    
    @Override
    public Response crearMedicion(Long usuarioId, Object body, SecurityContext securityContext) throws NotFoundException {
        LinkedHashMap mapa = (LinkedHashMap) body;

        String usuario = mapa.get( "usuarioId" ).toString();
        String tiempo = mapa.get( "tiempo" ).toString();
        Integer pulso = (Integer) mapa.get( "pulso" );
        Integer oxigeno = (Integer) mapa.get( "oxigeno" );

        Medicion medicionNueva = new Medicion();
        medicionNueva.setMedicionId( medicionIdContador.toString() );
        medicionIdContador++;
        medicionNueva.setUsuarioId( usuario );
        medicionNueva.setTiempo( tiempo );
        medicionNueva.setPulso( pulso );
        medicionNueva.setOxigeno( oxigeno );

        mediciones.add( medicionNueva );

        Gson gson = new Gson();

        String json = gson.toJson( medicionNueva.getMedicionId() );
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @Override
    public Response eliminarMedicionUsuario( @NotNull Long usuarioId,  @NotNull Long medicionId, SecurityContext securityContext) throws NotFoundException {
        Iterator<Medicion> it = mediciones.iterator();
        Medicion aux;

        String idUsuarioString = usuarioId.toString();
        String idMedicionString = medicionId.toString();

        while( it.hasNext()){
            aux = it.next();
            if( aux.getUsuarioId().equals(idUsuarioString) && aux.getMedicionId().equals(idMedicionString)){

                mediciones.remove( aux );

                return Response.ok(Response.Status.OK).build();
            }
        }

        // medición no encontrado, devuelve un 404
        return Response.status(Response.Status.NOT_FOUND).entity("Medición de usuario no encontrada").build();
    }
    
    @Override
    public Response modificarMedicionesUsuario( @NotNull Long usuarioId,  @NotNull Long medicionId, Medicion body, SecurityContext securityContext) throws NotFoundException {

        Iterator<Medicion> it = mediciones.iterator();
        Medicion aux;

        String idUsuarioString = usuarioId.toString();
        String idMedicionString = medicionId.toString();

        while( it.hasNext()){
            aux = it.next();
            if( aux.getUsuarioId().equals(idUsuarioString) && aux.getMedicionId().equals(idMedicionString)){

                aux.setTiempo( body.getTiempo() );
                aux.setOxigeno( body.getOxigeno() );
                aux.setPulso( body.getPulso() );

                return Response.ok(Response.Status.OK).build();
            }
        }

        // usuario no encontrado, devuelve un 404
        return Response.status(Response.Status.NOT_FOUND).entity("Medición no encontrado").build();
    }
    
}

