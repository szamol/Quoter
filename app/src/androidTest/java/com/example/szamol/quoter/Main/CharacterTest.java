package com.example.szamol.quoter.Main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    Character character;

    @Before
    public void setUp() throws Exception {
        character = new Character("exampleName", "exampleKey", "exampleQuote");
    }

    @Test
    public void getQuote() {
        assertEquals("exampleQuote", character.getQuote());
    }

    @Test
    public void getName() {
        assertEquals("exampleName", character.getName());
    }

    @Test
    public void getKey() {
        assertEquals("exampleKey", character.getKey());
    }

}