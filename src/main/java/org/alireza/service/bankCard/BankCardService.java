package org.alireza.service.bankCard;

import org.alireza.base.service.BaseService;
import org.alireza.model.BankCard;
import org.alireza.model.Loan;

public interface BankCardService extends BaseService<BankCard, Long> {

    BankCard findByNumber(Long cardNumber);
}
