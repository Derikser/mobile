import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        zad1();
        zad2();
        zad3();
        zad4();
        zad5();
        zad6();
        zad7();
        zad8();
        zad9();
        zad10();
        zad11();
        zad12();
        zad13();
        zad14();
        zad15();
        zad16();
        zad17();
        zad18();
        zad19();
        zad20();
    }

    // Задача 1: Четное или нечетное число
    public static void zad1() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = sc.nextInt();
        if (num % 2 == 0) {
            System.out.println("Четное");
        } else {
            System.out.println("Нечетное");
        }
    }

    // Задача 2: Минимальное из трех чисел
    public static void zad2() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите три числа: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int min = Math.min(a, Math.min(b, c));
        System.out.println("Минимальное число: " + min);
    }

    // Задача 3: Таблица умножения
    public static void zad3() {
        System.out.println("Таблица умножения на 5:");
        for (int i = 1; i <= 10; i++) {
            System.out.println("5 * " + i + " = " + (5 * i));
        }
    }

    // Задача 4: Сумма чисел от 1 до N
    public static void zad4() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число N: ");
        int n = sc.nextInt();
        int sum = (n * (n + 1)) / 2;
        System.out.println("Сумма чисел от 1 до " + n + " = " + sum);
    }

    // Задача 5: Число Фибоначчи
    public static void zad5() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число N: ");
        int n = sc.nextInt();
        int a = 0, b = 1;
        System.out.print("Числа Фибоначчи: ");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println();
    }
    // Задача 6: Проверка простого числа
    public static void zad6() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = sc.nextInt();
        boolean isPrime = true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime && num > 1) {
            System.out.println("Число простое");
        } else {
            System.out.println("Число не простое");
        }
    }

    // Задача 7: Обратный порядок чисел
    public static void zad7() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число N: ");
        int n = sc.nextInt();
        for (int i = n; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Задача 8: Сумма четных чисел
    public static void zad8() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите два числа A и B: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int sum = 0;
        for (int i = a; i <= b; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        System.out.println("Сумма четных чисел: " + sum);
    }

    // Задача 9: Реверс строки
    public static void zad9() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = sc.nextLine();
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println("Реверс строки: " + reversed);
    }

    // Задача 10: Количество цифр в числе
    public static void zad10() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = sc.nextInt();
        int count = String.valueOf(num).length();
        System.out.println("Количество цифр в числе: " + count);
    }

    // Задача 11: Факториал числа
    public static void zad11() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число N: ");
        int n = sc.nextInt();
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println("Факториал числа: " + factorial);
    }

    // Задача 12: Сумма цифр числа
    public static void zad12() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = sc.nextInt();
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        System.out.println("Сумма цифр числа: " + sum);
    }

    // Задача 13: Палиндром
    public static void zad13() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = sc.nextLine();
        String reversed = new StringBuilder(str).reverse().toString();
        if (str.equals(reversed)) {
            System.out.println("Строка является палиндромом");
        } else {
            System.out.println("Строка не является палиндромом");
        }
    }

    // Задача 14: Найти максимальное число в массиве
    public static void zad14() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Максимальное число в массиве: " + max);
    }

    // Задача 15: Сумма всех элементов массива
    public static void zad15() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Введите элементы массива: ");
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        System.out.println("Сумма всех элементов массива: " + sum);
    }

    // Задача 16: Количество положительных и отрицательных чисел
    public static void zad16() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        int positiveCount = 0, negativeCount = 0;
        System.out.print("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (arr[i] > 0) {
                positiveCount++;
            } else if (arr[i] < 0) {
                negativeCount++;
            }
        }
        System.out.println("Положительных чисел: " + positiveCount);
        System.out.println("Отрицательных чисел: " + negativeCount);
    }

    // Задача 17: Простые числа в диапазоне
    public static void zad17() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите два числа A и B: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.print("Простые числа в диапазоне: ");
        for (int num = a; num <= b; num++) {
            if (isPrime(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }

    // Задача 18: Подсчет гласных и согласных в строке
    public static void zad18() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = sc.nextLine();
        int vowels = 0, consonants = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if (isVowel(c)) {
                vowels++;
            } else if (Character.isLetter(c)) {
                consonants++;
            }
        }
        System.out.println("Гласных: " + vowels);
        System.out.println("Согласных: " + consonants);
    }

    // Задача 19: Перестановка слов в строке
    public static void zad19() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = sc.nextLine();
        String[] words = str.split(" ");
        StringBuilder reversedStr = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedStr.append(words[i]).append(" ");
        }
        System.out.println("Перестановка слов: " + reversedStr.toString().trim());
    }

    // Задача 20: Число Армстронга
    public static void zad20() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = sc.nextInt();
        int digits = String.valueOf(num).length();
        int sum = 0, temp = num;
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }
        if (sum == num) {
            System.out.println("Число является числом Армстронга");
        } else {
            System.out.println("Число не является числом Армстронга");
        }
    }

    // Проверка на простое число
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Проверка на гласную
    public static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }
}
