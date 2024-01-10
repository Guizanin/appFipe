package com.example.desafioFipe.principal;

import com.example.desafioFipe.model.Modelos;
import com.example.desafioFipe.model.Veiculo;
import com.example.desafioFipe.service.*;
import com.example.desafioFipe.model.Dados;

import java.util.Comparator;
import java.util.Scanner;


public class Principal {
    private final Scanner inputDados = new Scanner(System.in);

    public final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){


        String tipoVeiculo;
        String endereco;

        ConsumoApi consumoApi = new ConsumoApi();
        ConverteDados converteDados = new ConverteDados();
        

        //input do tipo de veículo
        System.out.println("""
                \n*** OPÇÕES DE VEÍCULO ***
                - CARROS;
                - MOTOS;
                - CAMINHOES;
                \n""");
        tipoVeiculo = inputDados.nextLine().toLowerCase();

        TrataOpcoes trataOpcoes = new TrataOpcoes();
        endereco = trataOpcoes.trataInput(tipoVeiculo, BASE_URL);

        var json = consumoApi.obterDados(endereco);

        var marcas = converteDados.obterLista(json, Dados.class);

        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                        .forEach(System.out::println);


        System.out.println("\n\nInforme o código da marca para consulta");
        tipoVeiculo = inputDados.nextLine();

        TrataMarca trataMarca = new TrataMarca();
        endereco = trataMarca.marcaEscolhida(tipoVeiculo, endereco);

        json = consumoApi.obterDados(endereco);
        var modelos = converteDados.obterDados(json, Modelos.class);

        System.out.println("\nmodelos desta marca: ");
        modelos.modelos().stream()
                .sorted(Comparator.comparing((Dados::codigo)))
                .forEach(System.out::println);


        System.out.println("\n\nDigite o código do modelo");
        tipoVeiculo = inputDados.nextLine();

        TrataAnosModelo trataAnosModelo = new TrataAnosModelo();
        endereco = trataAnosModelo.trataAnoModelo(tipoVeiculo, endereco);

        json = consumoApi.obterDados(endereco);
        var anosModelo = converteDados.obterLista(json, Dados.class);

        anosModelo.stream()
                        .sorted(Comparator.comparing(Dados::codigo))
                                .forEach(System.out::println);

        System.out.println("\n\nEscolha o código do ano:");
        tipoVeiculo = inputDados.nextLine();

        TrataCodigoAno trataCodigoAno = new TrataCodigoAno();
        endereco = trataCodigoAno.trataAno(tipoVeiculo, endereco);

        json = consumoApi.obterDados(endereco);
        var modeloEscolhido = converteDados.obterDados(json, Veiculo.class);

        System.out.println(modeloEscolhido);

    }
}
