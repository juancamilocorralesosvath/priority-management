package datastructures;


public class HashTable<K, V> implements IHashTable<K, V> {


// manera de crear un arreglo generico, o una manera de hacerlo.
    private HashNode<K, V>[] table;

    // este nodo "deleted" es mi referencia para saber cuándo
    // o mejor dicho en qué posición he hecho una eliminación de un
    // elemento. (para no dejar en ese espacio un valor null).
    
    private HashNode<K, V> deleted;
    private int size;



    private static final double KNUTH =  (Math.sqrt(5) - 1) / 2.0;
    //private static final int RADIX_FACTOR = 128;

    // size == m
    public HashTable(int size){
        this.size = size;
        table = new HashNode[size];
    }


    private int hashFunction(K object) {
        // 1. saco el toString del objeto
        // String info = object.toString();
        // 2. ese toString lo convierto a entero con lo que vimos de ASCII
        // long key = radix128Ascii(info);
        // como los valores estaban dando demasiado grandes, se tuvo que usar el metodo hashcode
        int key = object.hashCode();
        // 3. metodo de la multiplicacion para hacer el hash
        int hash = (int) Math.floor( size * ((key * KNUTH) % 1 ) );

        return hash;
    }
    /* 
    private long radix128Ascii(String str){
        long sum = 0;
        int radixIndicator = str.length() - 1;
        for (int i = 0; i < str.length(); i++) {
            int asciiValue = str.charAt(i);
            sum +=  asciiValue * Math.pow(RADIX_FACTOR, radixIndicator);
            radixIndicator--;
        }
        return sum;
    }
    */
    
    @Override
    public boolean add(K key, V value) {
        int address = hashFunction(key);
        boolean result = false;
        HashNode<K, V> newNode = new HashNode<>(key, value);
        // CHAINING
        if (this.table[address] == null || this.table[address] == deleted ){
            table[address] = newNode;
            result = true;
        }else{
            HashNode<K, V> current = this.table[address];
            this.table[address] = newNode;
            current.setPrev(newNode);
            newNode.setNext(current);
            result = true;
        }

        return result;
    }

    @Override
    public V search(K key) {

        int address = hashFunction(key);
        V result = null;
        // me ubico en la posicion del arreglo
        if(this.table[address] != null){
            result =  this.table[address].getValue();

        }

        return result;
    }

    @Override
    public V delete(K key) {

        int address = hashFunction(key);
        V result = null;

        // me ubico en la posicion del arreglo
        if(this.table[address] != null){

            HashNode<K, V> current = this.table[address];
            result =  current.getValue();

            if(current.getNext() != null){

                current = current.getNext();
                current.setPrev(null);
                this.table[address] = current;

            }else {

                this.table[address] = deleted;

            }
        }
        return result;
    }

    public String print(){
        String msg="";

        if(this.table == null){
            msg="There aren't task registered";
        }
        else{
            for (int index = 0; index < table.length; index++) {
                if(this.table[index]==null){

                }else{

                    print(this.table[index]);
                }
            }
        }

        return msg;
    }

    public String print(HashNode<K, V> current){
        String msg="";

        V  temp =current.getValue();
        msg= temp.toString();
        System.out.println(msg);

        if(current.getNext()!= null){
            print(current.getNext());
        }
        
        return msg;
    }

    public int getSize() {
        return size;
    }

    public HashNode<K, V>[] getTable(){
        return table;
    }

}
