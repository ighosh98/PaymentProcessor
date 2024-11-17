package org.example;

import lombok.Getter;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import org.mockito.Mock;
@Getter
@RunWith(MockitoJUnitRunner.class)
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}