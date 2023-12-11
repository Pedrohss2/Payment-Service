package model.services;

public interface OnlInePaymentService {
     double paymentFee(Double amount);
     double interest(Double amount,Integer months);
}
