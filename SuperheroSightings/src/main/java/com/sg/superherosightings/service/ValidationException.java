/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.io.IOException;

/**
 *
 * @author husst
 */
public class ValidationException extends IOException {

    public ValidationException(String message) {
        super(message);
    }
}
