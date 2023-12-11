package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;

public class ContractService {
    private OnlInePaymentService onlInePaymentService;

    public ContractService(OnlInePaymentService onlInePaymentService) {
        this.onlInePaymentService = onlInePaymentService;
    }
    public void processContract(Contract contract, Integer months) {
        double basicSalary = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);

            double interest = onlInePaymentService.interest(basicSalary, i);
            double fee = onlInePaymentService.paymentFee(basicSalary + interest);
            double quota = basicSalary + interest + fee;

            contract.getInstallmentsList().add(new Installment(dueDate, quota));
        }
    }
}
