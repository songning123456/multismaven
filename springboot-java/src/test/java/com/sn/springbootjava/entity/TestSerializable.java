package com.sn.springbootjava.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sn
 */
@Data
public class TestSerializable implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    private String value;


    public String toString() {
        return "[hashcode=" + hashCode() + ",value=" + value + "]";
    }
}
