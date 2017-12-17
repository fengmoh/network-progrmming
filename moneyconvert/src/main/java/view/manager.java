/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.ejb.EJB;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import controller.MoneyFacade;

/**
 *
 * @author 嘉诚
 */
@Named("acctManager")
@ConversationScoped
public class manager implements Serializable {

    @EJB
    private MoneyFacade moneyFacade;
    private float transactionAmount;
    private float result;
//    private Integer sRMB;
//    private Integer sEUR;
//    private Integer sSEK;
//    private Integer sUSD;
    private String fCurrency;
    private String sCurrency;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public void convert() {
        try {
            startConversation();
            result = moneyFacade.convert(fCurrency, sCurrency, transactionAmount);
            getResult();
        } catch (Exception e) {
            System.out.print("ero");
            HandleException(e);
            
        }      
    }
private void HandleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }
    public float getResult() {
        return result;
    }

    public float gettransactionAmount() {
        return transactionAmount;
    }

    public void settransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getfCurrency() {
        return fCurrency;
    }

    public void setfCurrency(String currency) {
        this.fCurrency = currency;
    }

    public String getsCurrency() {
        return sCurrency;
    }

    public void setsCurrency(String currency) {
        this.sCurrency = currency;
    }

}
