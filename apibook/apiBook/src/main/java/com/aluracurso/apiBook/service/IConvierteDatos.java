package com.aluracurso.apiBook.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
