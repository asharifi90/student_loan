package org.alireza.repository.bankCard;

import org.alireza.base.repository.BaseRepositoryImpl;
import org.alireza.model.BankCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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


    @Override
    public BankCard findByNumber(Long cardNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "From BankCard s WHERE s.cardNumber = :cardNumber";
        Query<BankCard> cardQuery = session.createQuery(hql, BankCard.class);
        cardQuery.setParameter("cardNumber", cardNumber);
        BankCard bankCard = cardQuery.getSingleResult();
        return bankCard;
    }
}
