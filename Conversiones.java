import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.swing.plaf.OptionPaneUI;

public class Conversiones {

  static InputStreamReader input = new InputStreamReader(System.in);
  static BufferedReader reader = new BufferedReader(input);

  public static void main(String[] args) throws IOException {

    Integer option = 1;

    while (option == 1) {
      mainMenu();
      System.out.println(
          "Quieres realizar otra operacion? (1)Si (Cualquier)No");
      try {
        option = Integer.parseInt(reader.readLine());
      } catch (NumberFormatException e) {
        System.out.println("Saliendo a pesar de tu retraso");
        option = 2;
      }
    }
  }

  private static void mainMenu() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Ingrese el sistema de su número \n"
                       + "(1)binario"
                       + "(2)decimal"
                       + "(3)hexadecimal "
                       + "(4)octal ");

    String from = scanner.nextLine();

    System.out.println("Ingrese el número: ");
    String numero = scanner.nextLine();

    System.out.println("Ingrese el sistema de su número \n"
                       + "(1)binario"
                       + "(2)decimal"
                       + "(3)hexadecimal "
                       + "(4)octal ");
    String to = scanner.nextLine();

    String resultado = convertir(from, numero, to);

    System.out.println("Conversion:" + resultado);
  }

  public static String convertir(String from, String numero, String to) {
    int dec = 0;

    // Convertir el número de origen a decimal
    switch (from) {
    case "1":
      dec = bintodec(numero);
      break;
    case "2":
      dec = Integer.parseInt(numero);
      break;
    case "3":
      dec = hextodec(numero);
      break;
    case "4":
      dec = octtodec(numero);
      break;
    default:
      return "Sistema de origen no válido.";
    }

    // Convertir el número decimal al sistema de destino
    switch (to) {
    case "1":
      return dectobin(dec);
    case "2":
      return Integer.toString(dec);
    case "3":
      return dextohex(dec);
    case "4":
      return dectooct(dec);
    default:
      return "Sistema de destino no válido.";
    }
  }

  // Conversión de Binario a Decimal
  public static int bintodec(String bin) {
    int decimal = 0;
    int potencia = 0;

    for (int i = bin.length() - 1; i >= 0; i--) {
      if (bin.charAt(i) == '1') {
        decimal += Math.pow(2, potencia);
      }
      potencia++;
    }
    return decimal;
  }

  // Conversión de Octal a Decimal
  public static int octtodec(String oct) {
    int decimal = 0;
    int potencia = 0;

    for (int i = oct.length() - 1; i >= 0; i--) {
      int digito = oct.charAt(i) - '0';
      decimal += digito * Math.pow(8, potencia);
      potencia++;
    }
    return decimal;
  }

  // Conversión de Hexadecimal a Decimal
  public static int hextodec(String hex) {
    int decimal = 0;
    int potencia = 0;

    for (int i = hex.length() - 1; i >= 0; i--) {
      char digito = hex.charAt(i);
      int valor;

      if (digito >= '0' && digito <= '9') {
        valor = digito - '0';
      } else {
        valor = digito - 'A' + 10;
      }

      decimal += valor * Math.pow(16, potencia);
      potencia++;
    }
    return decimal;
  }

  // Conversión de Decimal a Binario
  public static String dectobin(int dec) {
    StringBuilder bin = new StringBuilder();

    while (dec > 0) {
      bin.insert(0, dec % 2);
      dec /= 2;
    }

    return bin.length() > 0 ? bin.toString() : "0";
  }

  // Conversión de Decimal a Octal
  public static String dectooct(int dec) {
    StringBuilder oct = new StringBuilder();

    while (dec > 0) {
      oct.insert(0, dec % 8);
      dec /= 8;
    }

    return oct.length() > 0 ? oct.toString() : "0";
  }

  // Conversión de Decimal a Hexadecimal
  public static String dextohex(int dec) {
    StringBuilder hex = new StringBuilder();
    char[] hexDigits = "0123456789ABCDEF".toCharArray();

    while (dec > 0) {
      int residuo = dec % 16;
      hex.insert(0, hexDigits[residuo]);
      dec /= 16;
    }

    return hex.length() > 0 ? hex.toString() : "0";
  }
}
