import org.junit.Test;

public class TestOrdenarNumeros {

    private String numeros = "1, 5, -4, 6, -20, -8, -100, 50, 35, 3, 3, 4, 80, 100";
    private String palabras =  "alejandro, casa, aaron, ciruela, perro, zapote, barro, ciruela, dedo, casas, perros, zapotes, zancudos";
    @Test
    public void ordenarNumerosMenorAMayor() throws Exception {
        System.out.println("Ordenar Numeros menor a mayor "+numeros);
        String[] numerosArregloString = numeros.split(",");
        int y = 0;
        int[] numerosArregloEnteros = new int[numerosArregloString.length];
        for (int i = 0; i < numerosArregloString.length; i++) {
            numerosArregloEnteros[i] = Integer.parseInt(numerosArregloString[i].trim());
        }
        for (int posValActual = 1; posValActual < numerosArregloEnteros.length; posValActual++) {
            int valorActual = numerosArregloEnteros[posValActual];
            int posValComparar = posValActual - 1;
            System.out.println("Ubicación a estudio "+posValActual+" valorActual "+valorActual);
            while (posValComparar >= 0 &&  valorActual<numerosArregloEnteros[posValComparar]) {
                numerosArregloEnteros[posValComparar + 1] = numerosArregloEnteros[posValComparar];
                numerosArregloEnteros[posValComparar] = valorActual;
                posValComparar--;
            }
        }
        for (int i = 0; i < numerosArregloEnteros.length; i++) {
            System.out.println("Numero Ordenado " + i + " con valor " + numerosArregloEnteros[i]);
        }
    }

    @Test
    public void ordenarNumerosMayorAMenor() throws Exception {
        System.out.println("Ordenar Numeros mayor a menor "+numeros);
        String[] numerosArregloString = numeros.split(",");
        int y = 0;
        int[] numerosArregloEnteros = new int[numerosArregloString.length];
        for (int i = 0; i < numerosArregloString.length; i++) {
            numerosArregloEnteros[i] = Integer.parseInt(numerosArregloString[i].trim());
        }
        for (int posValActual = 1; posValActual < numerosArregloEnteros.length; posValActual++) {
            int valorActual = numerosArregloEnteros[posValActual];
            int posValComparar = posValActual-1;
            System.out.println("Ubicación a estudio "+posValActual+" valorActual "+valorActual);
            while (posValComparar >= 0 && valorActual > numerosArregloEnteros[posValComparar]) {
                numerosArregloEnteros[posValComparar+1] = numerosArregloEnteros[posValComparar];
                numerosArregloEnteros[posValComparar] = valorActual;
                posValComparar--;
            }
        }
        for (int i = 0; i < numerosArregloEnteros.length; i++) {
            System.out.println("mayor a menor posicion " + i + " con valor " + numerosArregloEnteros[i]);
        }
    }
    @Test
    public void ordenarPalabrasMenorAMayor()
    {
        System.out.println("Palabras a ordenar "+palabras);
        String[] palabrasArreglo = palabras.split(",");
        String[] palabrasArregloSinEspacios = new String[palabrasArreglo.length];
        for(int i = 0 ; i<palabrasArreglo.length; i++)
        {
            palabrasArregloSinEspacios[i] = palabrasArreglo[i].trim();
        }

        for ( int posPalabraActual = 1 ; posPalabraActual< palabrasArregloSinEspacios.length; posPalabraActual++)
        {
            String palabraActual = palabrasArregloSinEspacios[posPalabraActual];
            System.out.println("Ubicación a estudio "+posPalabraActual+" palabraActual "+palabraActual);
            int posPalabraComparar = posPalabraActual - 1;
            while(posPalabraComparar >= 0 && palabraActual.compareTo(palabrasArregloSinEspacios[posPalabraComparar])<0)
            {
                palabrasArregloSinEspacios[posPalabraComparar+1]= palabrasArregloSinEspacios[posPalabraComparar];
                palabrasArregloSinEspacios[posPalabraComparar]= palabraActual;
                posPalabraComparar--;
            }
        }
        for(int i=0; i<palabrasArregloSinEspacios.length; i++)
            System.out.println("Palabra Ordenada "+i+" con valor "+palabrasArregloSinEspacios[i]);


    }

    @Test
    public void ordenarPalabrasMayorAMenor() {
        System.out.println("Palabras a ordenar " + palabras);
        String[] palabrasArreglo = palabras.split(",");
        String[] palabrasArregloSinEspacios = new String[palabrasArreglo.length];
        for (int i = 0; i < palabrasArreglo.length; i++) {
            palabrasArregloSinEspacios[i] = palabrasArreglo[i].trim();
        }
        for(int posPalabraActual = 1; posPalabraActual<palabrasArregloSinEspacios.length; posPalabraActual++)
        {
            String palabraActual = palabrasArregloSinEspacios[posPalabraActual];
            int posPalabraComparar = posPalabraActual - 1;
            while (posPalabraComparar>=0 && palabraActual.compareTo(palabrasArregloSinEspacios[posPalabraComparar])>0)
            {
                palabrasArregloSinEspacios[posPalabraComparar+1] = palabrasArregloSinEspacios[posPalabraComparar];
                palabrasArregloSinEspacios[posPalabraComparar] = palabraActual;
                posPalabraComparar--;
            }
        }
        for (int i = 0; i < palabrasArregloSinEspacios.length; i++) {
            System.out.println(" Palabra Ordenada descendentemente "+i+" con valor "+palabrasArregloSinEspacios[i]);
        }


    }
    }