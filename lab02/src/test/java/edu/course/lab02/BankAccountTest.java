package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BankAccountTest {

    @Test
    void withdrawDecreasesBalance() {
        BankAccount account = new BankAccount(100);

        account.withdraw(30);

        assertEquals(70, account.getBalance());
    }

    @Test
    void withdrawMoreThanBalanceThrows() {
        BankAccount account = new BankAccount(50);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(51));
        assertEquals(50, account.getBalance());
    }

    @Test
    void constructorRejectsNegativeBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    @Test
    void depositIncreasesBalance() {
        BankAccount account = new BankAccount(0);

        account.deposit(25);

        assertEquals(25, account.getBalance());
    }

    @Test
    void depositRejectsNonPositiveAmount() {
        BankAccount account = new BankAccount(10);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-5));
    }

    @Test
    void withdrawRejectsNonPositiveAmount() {
        BankAccount account = new BankAccount(10);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5));
    }
}