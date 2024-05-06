package org.alireza.service.bankCard;

import org.alireza.base.service.BaseServiceImpl;
import org.alireza.model.BankCard;
import org.alireza.repository.bankCard.BankCardRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BankCardServiceImpl extends BaseServiceImpl<BankCard, Long, BankCardRepository>
implements BankCardService{
    public BankCardServiceImpl(BankCardRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public BankCard findByNumber(Long cardNumber) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction t = session.beginTransaction();
            BankCard bankCard = repository.findByNumber(cardNumber);
            t.commit();
            return bankCard;
        }catch (Exception e){
            return null;
        }
    }
}
