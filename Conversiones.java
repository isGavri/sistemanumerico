import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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

    String sistemaOrigen = scanner.nextLine();

    System.out.println("Ingrese el número: ");
    String numero = scanner.nextLine();

    System.out.println("Ingrese el sistema de su número \n"
                       + "(1)binario"
                       + "(2)decimal"
                       + "(3)hexadecimal "
                       + "(4)octal ");
    String sistemaDestino = scanner.nextLine();

    String resultado = convertir(sistemaOrigen, numero, sistemaDestino);

    System.out.println("El resultado de la conversión es: " + resultado);
  }

  public static String convertir(String sistemaOrigen, String numero,
                                 String sistemaDestino) {
    int numeroDecimal = 0;

    // Convertir el número de origen a decimal
    switch (sistemaOrigen) {
    case "1":
      numeroDecimal = binarioADecimal(numero);
      break;
    case "2":
      numeroDecimal = Integer.parseInt(numero);
      break;
    case "3":
      numeroDecimal = hexadecimalADecimal(numero);
      break;
    case "4":
      numeroDecimal = octalADecimal(numero);
      break;
    default:
      return "Sistema de origen no válido.";
    }

    // Convertir el número decimal al sistema de destino
    switch (sistemaDestino) {
    case "1":
      return decimalABinario(numeroDecimal);
    case "2":
      return Integer.toString(numeroDecimal);
    case "3":
      return decimalAHexadecimal(numeroDecimal);
    case "4":
      return decimalAOctal(numeroDecimal);
    default:
      return "Sistema de destino no válido.";
    }
  }

  // Conversión de Binario a Decimal
  public static int binarioADecimal(String numeroBinario) {
    int decimal = 0;
    int potencia = 0;

    for (int i = numeroBinario.length() - 1; i >= 0; i--) {
      if (numeroBinario.charAt(i) == '1') {
        decimal += Math.pow(2, potencia);
      }
      potencia++;
    }
    return decimal;
  }

  // Conversión de Octal a Decimal
  public static int octalADecimal(String numeroOctal) {
    int decimal = 0;
    int potencia = 0;

    for (int i = numeroOctal.length() - 1; i >= 0; i--) {
      int digito = numeroOctal.charAt(i) - '0';
      decimal += digito * Math.pow(8, potencia);
      potencia++;
    }
    return decimal;
  }

  // Conversión de Hexadecimal a Decimal
  public static int hexadecimalADecimal(String numeroHex) {
    int decimal = 0;
    int potencia = 0;

    for (int i = numeroHex.length() - 1; i >= 0; i--) {
      char digito = numeroHex.charAt(i);
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
  public static String decimalABinario(int numeroDecimal) {
    StringBuilder binario = new StringBuilder();

    while (numeroDecimal > 0) {
      binario.insert(0, numeroDecimal % 2);
      numeroDecimal /= 2;
    }

    return binario.length() > 0 ? binario.toString() : "0";
  }

  // Conversión de Decimal a Octal
  public static String decimalAOctal(int numeroDecimal) {
    StringBuilder octal = new StringBuilder();

    while (numeroDecimal > 0) {
      octal.insert(0, numeroDecimal % 8);
      numeroDecimal /= 8;
    }

    return octal.length() > 0 ? octal.toString() : "0";
  }

  // Conversión de Decimal a Hexadecimal
  public static String decimalAHexadecimal(int numeroDecimal) {
    StringBuilder hexadecimal = new StringBuilder();
    char[] hexDigits = "0123456789ABCDEF".toCharArray();

    while (numeroDecimal > 0) {
      int residuo = numeroDecimal % 16;
      hexadecimal.insert(0, hexDigits[residuo]);
      numeroDecimal /= 16;
    }

    return hexadecimal.length() > 0 ? hexadecimal.toString() : "0";
  }
}
