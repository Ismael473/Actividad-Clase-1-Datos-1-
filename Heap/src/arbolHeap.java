public class arbolHeap {
    
    // Cree un gran montón de raíz: trate la matriz como una estructura de almacenamiento secuencial de un árbol binario completo
    private int[] buildMaxHeap(int[] array){
        // Comience desde el nodo primario (array.length-1-1) / 2 del último nodo array.length-1 hasta el nodo raíz 0, y ajuste repetidamente el montón
        for(int i=(array.length-2)/2;i>=0;i--){
            adjustDownToUp(array, i,array.length);
        }
        return array;
    }

    // Ajusta la matriz de elementos [k] de abajo hacia arriba y ajusta gradualmente la estructura de árbol
    private void adjustDownToUp(int[] array,int k,int length){
        int temp = array[k];
        for (int i = 2 * k + 1; i <length-1; i = 2 * i + 1) {// i es el niño izquierdo inicializado en el nodo k, y se ajusta hacia abajo a lo largo del niño más grande
            if (i <length && array [i] <array [i + 1]) {// toma el subíndice del nodo hijo más grande del nodo
                i ++; // Si el hijo derecho del nodo> hijo izquierdo, tome el índice del nodo hijo derecho
            }
            if (temp>= array [i]) {
// root node> = el mayor de los hijos izquierdo y derecho, el ajuste se ha completado
                break;
            } else {// nodo raíz
                array [k] = array [i]; // Ajuste el valor mayor array [i] en los nodos secundarios izquierdo y derecho al nodo primario
                k = i; // [Key] modifica el valor de k para que pueda ajustarse hacia abajo
            }
        }
        array [k] = temp; // El valor del nodo ajustado se coloca en la posición final

    }
    public int[] heapSort(int[] array){
        array = buildMaxHeap (array); // Construcción inicial, array [0] es el elemento con el valor más grande en la primera pasada
        for(int i=array.length-1;i>1;i--){
            int temp = array [0]; // Intercambia el elemento superior y el elemento inferior del montón para obtener la posición de clasificación correcta del elemento más grande actual
            array[0] = array[i];
            array[i] = temp;
            adjustDownToUp(array, 0, i); // ordenar, ordenar los elementos restantes en una pila
        }
        return array;
    }
    public int[] deleteAny(int[] array){
        // Cambia el último elemento del montón con el elemento superior del montón, y luego lo elimina
        for (int x = 0; x<array.length;x++){// cambia la primera posicion por el valor del ultimo, para hacer una demostración se puede cambiar el valor de 6 por una variable la cual sería el indice del número a eliminar
            if (array[x] == 32){
                array[x] = array[array.length-1];
                int[] newarray = new int[array.length-1];//crea el nuevo array y lo hace del tamaño del array anterior menos dos ya que elimina el ultimo digito
                for (int i =0; i<array.length-1; i++){
                    newarray[i]=array[i];

                }
                // Ajusta el nodo raíz en este momento
                adjustDownToUp(newarray, 0, newarray.length);
                return newarray;
            }
        }

        return array;
    }
    public int[] insertData(int[] array, int data){
        array [array.length-1] = data; // Coloque el nuevo nodo al final del montón
        int k = array.length-1; // Nodos a ajustar
        int parent = (k-1) / 2; // nodo padre
        while(parent >=0 && data>array[parent]){
            array [k] = array [parent]; // Parents down
            k = parent;
            if(parent != 0){
                parent = (parent-1) / 2; // continuar comparando hacia arriba
            } else {// El nodo raíz se ha ajustado y está fuera del ciclo
                break;
            }
        }
        array [k] = data; // Coloque el nodo insertado en la posición correcta
        return array;
    }

    public void toString(int[] array){
        for(int i:array){
            System.out.print(i+" ");
        }
        System.out.println("");
    }

    public static void main(String args[]){
        arbolHeap hs = new arbolHeap();
        int[] array = {87,45,78,32,17,65,53,9,122};
        System.out.println("Construyendo el gran montón de root:");
        hs.toString(hs.buildMaxHeap(array));
        System.out.println("Eliminar el 32:");
        hs.toString(hs.deleteAny(array));
        System.out.println("Insertar elemento 63:");
        hs.toString(hs.insertData(array, 63));
        System.out.println("Big Root Heap Sort:");
        hs.toString(hs.heapSort(array));
    }
}
