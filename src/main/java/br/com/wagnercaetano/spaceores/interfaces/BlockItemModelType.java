package br.com.wagnercaetano.spaceores.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BlockItemModelType {

    String value();

}
