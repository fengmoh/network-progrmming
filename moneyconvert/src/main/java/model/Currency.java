/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 嘉诚
 */
@Entity
public class Currency implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public String currencyName;
    public float value;

    public Currency() {
    }

    public Currency(String currencyname, float value) {
        this.currencyName = currencyname;
        this.value = value;
    }
}
