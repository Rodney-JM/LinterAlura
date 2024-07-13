package com.jrm.literAlura.LiterAlura.service;

public interface IConverteDados {
    <T> T obtemDados(String json, Class<T> classe );
}
