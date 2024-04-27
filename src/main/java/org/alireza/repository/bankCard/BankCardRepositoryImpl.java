package org.alireza.repository.bankCard;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.model.BankCard;
import org.hibernate.SessionFactory;

public class BankCardRepositoryImpl extends BaseRepositoryImpl<BankCard, Long>
implements BankCardRepository{
    public BankCardRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<BankCard> getEntityClass() {
        return BankCard.class;
    }

    @Override
    public String getEntity() {
        return "BankCard";
    }
}
