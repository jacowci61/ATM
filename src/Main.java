import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

    String filePath = "src/ATMDataTEST.txt";

    long UserCard = WorkWithData.readInputtedCreditCard();
    System.out.println(ATM.Authorization(UserCard, filePath));
    }
}