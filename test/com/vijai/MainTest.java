package com.vijai;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    private CustomMapImpl<User, String> userData;
    private final int TEST_ENTRIES = 200000;

    @Before
    public void setup() {
        userData = new CustomMapImpl<User, String>();
    }

   @Test
   public void test_put_method() {
       userData.put(new User("10", "Andrey"), "info: user name = Andrey");
   }

   @Test
    public void test_Put_and_get_methods() {
        Object K = new User("11", "Nikolay");
        String V = "info: user name = Nikolay";
        userData.put(K, V);
        Object valueResult = userData.get(K);
        assertEquals(V, valueResult);
   }

   @Test
   public void test_size_methos() {
       userData.put(new User("10", "Andrey"), "info: user name = Andrey");
       userData.put(new User("11", "Dmitry"), "info: user name = Dmitry");
       userData.put(new User("12", "Oleg"), "info: user name = Oleg");
       int size = userData.size();
       assertEquals(3, size);
   }

   @Test
    public void test_replace_userName() {
       userData.put(new User("10", "Andrey"), "info: user name = Andrey");
       userData.put(new User("10", "Nikolay"), "info: user name = Nikolay");

       Object checkById = userData.get(new User("10", null));
       assertEquals("info: user name = Nikolay", checkById);

       Object checkByDeletedName = userData.get(new User(null, "Andrey"));
       assertEquals(null, checkByDeletedName);

       Object checkByAddedName = userData.get(new User(null, "Nikolay"));
       assertEquals(null, checkByAddedName); //hashCode() checks by id only!

       int size = userData.size();
       assertEquals(1, size);
   }

   @Test
    public void test_remove_method() {
       userData.put(new User("10", "Andrey"), "info: user name = Andrey");
       userData.put(new User("11", "Nikolay"), "info: user name = Nikolay");
       userData.remove(new User("10", null));

       int size = userData.size();
       assertEquals(1, size);

       Object checkById = userData.get(new User("10", null));
       assertEquals(null, checkById);

       Object checkById2 = userData.get(new User("11", null));
       assertEquals("info: user name = Nikolay", checkById2);
   }

   @Test
    public void test_hashMap_collisions() {

       for(int i = 0; i < TEST_ENTRIES; i++){
           userData.put(new User(Integer.toString(i), Integer.toString(i)), Integer.toString(i));
       }

       for(int i = 0; i < TEST_ENTRIES; i++){
           Object value =  userData.get(new User(Integer.toString(i), Integer.toString(i)));
           assertEquals(Integer.toString(i), value);
       }
   }
}