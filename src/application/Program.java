package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();

        // Leitura do Arquivo CSV,  criação da lista de vendas no bloco Try Catch
        List<Sale> sales = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                Integer month = Integer.parseInt(fields[0]);
                Integer year = Integer.parseInt(fields[1]);
                String seller = fields[2];
                Integer items = Integer.parseInt(fields[3]);
                Double total = Double.parseDouble(fields[4]);

                sales.add(new Sale(month, year, seller, items, total));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage() + " (O sistema não pode encontrar o arquivo especificado) ");
            return; // Encerra o programa em caso de erro na leitura do arquivo
        }

        // Análise das cinco primeiras vendas de 2016 com maior preço médio
        List<Sale> topSales2016 = sales.stream()
                .filter(s -> s.getYear() == 2016)
                .sorted(Comparator.comparing(Sale::getAveragePrice).reversed())
                .limit(5)
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Cinco primeiras vendas de 2016 de maior preço médio:");
        topSales2016.forEach(System.out::println);

        // Cálculo do valor total vendido pelo vendedor nos meses 1 e 7
        Double totalLogan = sales.stream()
                .filter(s -> s.getSeller().equals("Logan") && (s.getMonth() == 1 || s.getMonth() == 7))
                .mapToDouble(Sale::getTotal)
                .sum();
        
    }
}
