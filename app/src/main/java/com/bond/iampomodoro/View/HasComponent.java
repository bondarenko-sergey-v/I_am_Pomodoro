package com.bond.iampomodoro.View;

/**
 * Created by deepol on 11/09/15.
 */
public interface HasComponent<T> {

  T createComponent();

  T getComponent();
}
