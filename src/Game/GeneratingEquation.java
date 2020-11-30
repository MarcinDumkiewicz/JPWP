package Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** Klasa do tworzenia rownania */
public class GeneratingEquation{
    /** Pierwsza liczba rownania */
    public int firstNumber;
    /** Druga liczba rownania */
    public int secondNumber;
    /** Miejsce luki 1 - pierwsza liczba jest luka, 2 - druga liczba jest luka, 3 - wynik jest luka */
    public int emptyPlace;
    /** Rodzaj operacji 1.dodawanie 2.odejmowanie 3. mnozenie 4.dzielenie */
    public char operation;
    /** Wynik */
    public int result;

    /** Funkcja zwracajaca rownanie*/
    public String getEq() {
        return eq;
    }
    /** Zawartosc rownania */
    private String eq;
    /** Generator losowy*/
    public Random randomGenerator = new Random();

    /** Funkcja zwracajaca liczbe potrzebna do uzupelnienia rownania */
    public int getMissingNumber() {
        return missingNumber;
    }
    /** Wartosc potrzebna do uzupelnienia rownania */
    private int missingNumber;


    /** Konstruktor
     * @param firstNumber pierwsza liczba
     * @param secondNumber druga liczba
     * @param emptyPlace liczba odpowiadajaca luce
     * @param result wynik
     * */
    public GeneratingEquation(int firstNumber, int secondNumber, int emptyPlace, int result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.emptyPlace = emptyPlace;
        this.result = result;
    }

    /** Generowanie losowej liczby */
    public int setFirstNumber(){
        return randomGenerator.nextInt(15);
    }
    /** Przypisywanie wylosowanej liczby*/
    public int getFirstNumber(){
        firstNumber = setFirstNumber();
        System.out.println(firstNumber);
        return firstNumber;
    }

    /** Generowanie drugiej losowej liczby*/
    public int setSecondNumber(){
        return randomGenerator.nextInt(15);
    }
    /** Przypisywanie drugiej wylosowanej liczby*/
    public int getSecondNumber(){
        secondNumber = setSecondNumber();
        System.out.println(secondNumber);
        return secondNumber;

    }

    /**Funkcja sluzaca do losowania operacji oraz liczenie wyniku na podstawie otrzymanych liczb */
    public int Calculating(){
        Random r = new Random();
        int A = firstNumber;
        int B = secondNumber;
        operation = '?';
        result = 0;

        switch (r.nextInt(4)){
            case 0: operation = '+';
                result = A+B;
                break;
            case 1: operation = '-';
                result = A-B;
                break;
            case 2: operation = '*';
                result = A*B;
                break;
            case 3: operation = '/';
                try{
                    result = A/B;
                }catch (ArithmeticException ex){
                    ex.printStackTrace();
                }
                break;
            default: operation = '?';
        }
        System.out.println(result);
        System.out.println(operation);
        return result;
    }

    /** Losowanie luki w rownaniu */
    public int getEmptyPlace(){
        return randomGenerator.nextInt(2);
    }

    /** Ustawianie luki w rownaniu*/
    public String setEmptyPlace(){
        emptyPlace = getEmptyPlace();
        System.out.println(emptyPlace);
        String r = "";
        if(emptyPlace < 1 ){
            r += " _";
            r += operation;
            r += secondNumber;
            r += "=";
            r += result;
            eq = r;
        return r;
        }
        else if(emptyPlace == 1) {
            r += firstNumber;
            r += operation;
            r += " _";
            r += "=";
            r += result;
            eq = r;
            return r;
        }
        else if(emptyPlace > 1 ) {
            r += firstNumber;
            r += operation;
            r += secondNumber;
            r += "=";
            r += " _";
            eq = r;
            return r;

        }
        return r;
        }
        /** Tworzenie dodatkowych blednych odpowiedzi oraz dodanie do nich poprawnej */
    public int getRandomNumber(){
        return randomGenerator.nextInt(15);
    }
    /** Funckja tworzaca dodatkowe bledne odpowiedzi oraz dopisywana do nich jest odpowiedz poprawna*/
    public List creatingAnswers()
    {
        List<Integer> answers = new ArrayList<>();

        if (emptyPlace < 1){
            answers.add(firstNumber);
            missingNumber = firstNumber;
        }
        else if (emptyPlace == 1){
            answers.add(secondNumber);
            missingNumber = secondNumber;
        }
        else if (emptyPlace > 1) {
            answers.add(result);
            missingNumber = result;
        }

        for (int i = 0; i < 7; i++)
        {
            int rand = getRandomNumber();
            while (answers.contains(rand)){
                rand = getRandomNumber();
            }
            answers.add(rand);
        }

        Collections.shuffle(answers);
        System.out.println(answers);
        return answers;
    }
    }

