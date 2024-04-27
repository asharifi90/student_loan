package org.alireza.service.bankCard;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.model.BankCard;
import org.alireza.repository.bankCard.BankCardRepository;
import org.hibernate.SessionFactory;

public class BankCardServiceImpl extends BaseServiceImpl<BankCard, Long, BankCardRepository>
implements BankCardService{
    public BankCardServiceImpl(BankCardRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
