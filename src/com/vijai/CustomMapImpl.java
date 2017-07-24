package com.vijai;


public class CustomMapImpl<K, V> implements CustomMap  {

    private Entry<K,V>[] table;
    private int capacity = 100;  //number of buckets
    private int size = 0;

    static class Entry<K, V> {
        Object key;
        Object value;
        Entry<K,V> next;

        public Entry(Object key, Object value, Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    public CustomMapImpl(){
        table = new Entry[capacity];
    }
    @Override
    public void put(Object key, Object value){
        if(key==null)
            return;    //does not allow to put null.
        int hash=hash(key);

        Entry<K,V> newEntry = new Entry<K,V>(key, value, null);

        //if table location does not contain any entry, store entry there.
        if(table[hash] == null){
            table[hash] = newEntry;
            size++;
        }else{
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];

            while(current != null){ // last entry of bucket.
                if(current.key.equals(key)){
                    if(previous==null){  //node has to be insert on first of bucket.
                        newEntry.next=current.next;
                        table[hash]=newEntry;
                        return;
                    }
                    else{
                        newEntry.next=current.next;
                        previous.next=newEntry;
                        return;
                    }
                }
                previous=current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }


    @Override
    public Object get(Object key) {
        int hash = hash(key);
        if(table[hash] == null){
            return null;
        }else{
            Entry<K,V> temp = table[hash];
            while(temp!= null){
                if(temp.key.equals(key))
                    return temp.value;
                temp = temp.next; //return value corresponding to key.
            }
            System.out.println("Key is not found");
            return null;
        }
    }

    @Override
    public Object remove(Object key) {
        int hash=hash(key);
        if(table[hash] == null){
            return null;
        }else{
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];

            while(current != null){ //we have reached last entry node of bucket.
                if(current.key.equals(key)){
                    if(previous==null){  //delete first entry node.
                        table[hash]=table[hash].next;
                        size--;
                    }
                    else{
                        previous.next=current.next;
                    }
                }
                previous=current;
                current = current.next;
            }
            return null;
        }

    }

    @Override
    public int size() {
        return this.size;
    }

    private int hash(Object key){
        return Math.abs(key.hashCode()) % capacity;
    }

    // for testing purpose
    public void display(){

        for(int i=0;i<capacity;i++){
            if(table[i]!=null){
                Entry<K, V> entry=table[i];
                while(entry!=null){
                    System.out.print("{"+entry.key+"="+entry.value+"}" +" ");
                    entry=entry.next;
                }
            }
        }
    }
}
