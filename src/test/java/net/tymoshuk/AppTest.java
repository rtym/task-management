package net.tymoshuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    
    @Test
    void toLower() {

        Assertions.assertEquals("hello",   App.toLower("Hello"));
        
      ;
    }
    
}
