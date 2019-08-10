/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import view.manager;
import controller.*;
import javax.persistence.LockModeType;
import model.*;

/**
 *
 * @author 嘉诚
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class valueDAO {

    @PersistenceContext(unitName = "moneyConv")
    private EntityManager em;
//    public Currency findCurrencyByName;

    public Currency findCurrencyByName(String nameString) {
        try {
            Currency currency = em.find(Currency.class, nameString);
            System.out.print((float)currency.value);
        return currency;
        } catch (Exception e) {
            System.out.println("integration.valueDAO.findCurrencyByName()");
        }
return null;

    }

    public void storeCurrency(Currency currency) {

        em.merge(currency);


    }
}
