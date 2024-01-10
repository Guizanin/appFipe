package com.example.desafioFipe.service;

public class TrataOpcoes {
    public String trataInput (String opcao, String baseUrl){
        if(opcao.contains("car")){
            return baseUrl + "carros/marcas";
        } else if (opcao.contains("mot")) {
            return baseUrl + "motos/marcas";
        }else if(opcao.contains("cam")){
            return baseUrl + "caminhoes/marcas";
        }
        return opcao;
    }
}
