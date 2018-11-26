package io.swagger.api.impl;

import com.google.gson.Gson;
import io.swagger.api.*;

import io.swagger.model.Usuario;

import java.util.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import io.swagger.api.NotFoundException;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-24T17:48:27.964Z[GMT]")

public class UsuarioApiServiceImpl extends UsuarioApiService {

    List<Usuario> usuarios;
    Integer usuarioIdContador;

    public UsuarioApiServiceImpl(){
        usuarios = new LinkedList<Usuario>();

        Usuario user1 = new Usuario();
        user1.setUsuarioId("1");
        user1.setNombre("Antonio");
        user1.setApellidos("Sánchez");
        user1.setEmail("as@mail.es");
        user1.setTelefono("900900900");
        user1.setLocalidad("Casar de Cáceres");
        usuarios.add(user1);

        Usuario user2 = new Usuario();
        user2.setUsuarioId("2");
        user2.setNombre("María");
        user2.setApellidos("González");
        user2.setEmail("mg@mail.es");
        user2.setTelefono("900900901");
        user2.setLocalidad("Arroyo de la luz");
        usuarios.add(user2);

        Usuario user3 = new Usuario();
        user3.setUsuarioId("3");
        user3.setNombre("Nerea");
        user3.setApellidos("Suarez");
        user3.setEmail("ns@mail.es");
        user3.setTelefono("900900902");
        user3.setLocalidad("Albalá");
        usuarios.add(user3);

        Usuario user4 = new Usuario();
        user4.setUsuarioId("4");
        user4.setNombre("Pedro");
        user4.setApellidos("Gutiérrez");
        user4.setEmail("pg@mail.es");
        user4.setTelefono("900900903");
        user4.setLocalidad("Jaraíz de la Vera");
        usuarios.add(user4);

        Usuario user5 = new Usuario();
        user5.setUsuarioId("5");
        user5.setNombre("José");
        user5.setApellidos("Narváez");
        user5.setEmail("jn@mail.es");
        user5.setTelefono("900900904");
        user5.setLocalidad("Casar de Cáceres");
        usuarios.add(user5);

        usuarioIdContador = 6;

    }

    @Override
    public Response consultarUsuario( @NotNull Long usuarioId, SecurityContext securityContext) throws NotFoundException {

        Iterator<Usuario> it = usuarios.iterator();
        Usuario aux;

        String id = usuarioId.toString();

        while( it.hasNext()){
            aux = it.next();
            if( aux.getUsuarioId().equals(id)){

                Gson gson = new Gson();

                String json = gson.toJson(aux);
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            }
        }

        // usuario no encontrado, devuelve un 404
        return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
    }

    @Override
    public Response crearUsuario(Object body, SecurityContext securityContext) throws NotFoundException {

        LinkedHashMap mapa = (LinkedHashMap) body;

        String nombre = mapa.get( "nombre" ).toString();
        String apellidos = mapa.get( "apellidos" ).toString();
        String email = mapa.get( "email" ).toString();
        String telefono = mapa.get( "telefono" ).toString();
        String localidad = mapa.get( "localidad" ).toString();

        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setUsuarioId( usuarioIdContador.toString() );
        usuarioIdContador++;
        usuarioNuevo.setNombre( nombre );
        usuarioNuevo.setApellidos( apellidos );
        usuarioNuevo.setEmail( email );
        usuarioNuevo.setTelefono( telefono );
        usuarioNuevo.setLocalidad( localidad );

        usuarios.add( usuarioNuevo );

        Gson gson = new Gson();

        String json = gson.toJson( usuarioNuevo.getUsuarioId() );
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response eliminarUsuario( @NotNull Long usuarioId, SecurityContext securityContext) throws NotFoundException {

        Iterator<Usuario> it = usuarios.iterator();
        Usuario aux;

        String id = usuarioId.toString();

        while( it.hasNext()){
            aux = it.next();
            if( aux.getUsuarioId().equals(id)){

                usuarios.remove( aux );

                return Response.ok(Response.Status.OK).build();
            }
        }

        // usuario no encontrado, devuelve un 404
        return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
    }

    @Override
    public Response modificarUsuario( @NotNull Long usuarioId, Usuario body, SecurityContext securityContext) throws NotFoundException {

        Iterator<Usuario> it = usuarios.iterator();
        Usuario aux;

        String id = usuarioId.toString();

        while( it.hasNext()){
            aux = it.next();
            if( aux.getUsuarioId().equals(id)){

                aux.setNombre( body.getNombre() );
                aux.setApellidos( body.getApellidos() );
                aux.setEmail( body.getEmail() );
                aux.setTelefono( body.getTelefono() );
                aux.setLocalidad( body.getLocalidad() );

                return Response.ok(Response.Status.OK).build();
            }
        }

        // usuario no encontrado, devuelve un 404
        return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
    }

}