package com.example.desafioFipe.service;

public class TrataMarca {
    public String marcaEscolhida(String marca, String baseUrl){
        return baseUrl + "/" + marca + "/modelos";
    }
}
