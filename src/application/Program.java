package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.OnlInePaymentService;
import model.services.PaypalService;

import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter localDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre com os dados do contrato: ");

        System.out.print("Numero: ");
        int number = sc.nextInt();

        System.out.print("Date: ");
        LocalDate date = LocalDate.parse(sc.next(), localDate);

        System.out.println("Valor do contrato: ");
        double contractValue = sc.nextDouble();

        Contract contract = new Contract(number, date, contractValue);

        System.out.println("Entre com o numero de parcelas: ");
        int numOfInstallments = sc.nextInt();

        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract, numOfInstallments);

        System.out.println("PARCERLAS: ");
        for(Installment installment : contract.getInstallmentsList()) {
            System.out.println(installment);
        }

    }
}
