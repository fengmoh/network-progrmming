/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import integration.valueDAO;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityNotFoundException;
import model.Currency;
import view.manager;

/**
 *
 * @author 嘉诚
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class MoneyFacade {
    int counter = 0;
    @EJB
    valueDAO valueDB;
    public float convert(String aString, String bString, float amount) {
        Currency SEK = new Currency("SEK", (float) 1.6);
        Currency RMB = new Currency("RMB", 2);
        Currency EUR = new Currency("EUR", 16);
        Currency USD = new Currency("USD", (float) 13);
        valueDB.storeCurrency(SEK);
        valueDB.storeCurrency(RMB);
        valueDB.storeCurrency(EUR);
        valueDB.storeCurrency(USD);
        Currency initialCurrency = valueDB.findCurrencyByName(aString);
        Currency objCurrency = valueDB.findCurrencyByName(bString);
        float result = amount * initialCurrency.value / objCurrency.value;
        return result;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
