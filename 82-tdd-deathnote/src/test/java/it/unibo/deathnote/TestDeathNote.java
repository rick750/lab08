package it.unibo.deathnote;

import static java.lang.Thread.sleep;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.deathnote.api.DeathNote.RULES;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {
    private static final String EMPTY_STRING = "";
    private static final String DETAILS = "details...";
    private static final String CAUSE_KARTING_ACCIDENT = "karting accident";
    private static final String NAME_PAPERINO = "Paperino";
    private static final String NAME_PIPPO = "Pippo";
    private static final int ZERO = 0;
    private static final int NEG_VALUE = -1;
    private static final String CAUSE_HEART_ATTACK = "heart attack";
    private static final int WAIT_CAUSE = 100;

    private DeathNoteImplementation dn;

    @BeforeEach
    void sitUp(){
        this.dn = new DeathNoteImplementation();
    }

    /**
        Tests that rule number 0 and negative rules do not exist in the DeathNote rules
    */
    @Test
    void TestInvalidRules() {
        try {
            dn.getRule(ZERO);
        } catch (final IllegalArgumentException e) {          
            assertTrue(e instanceof IllegalArgumentException);
            assertNotNull(e.getMessage());  
            assertFalse(e.getMessage().isBlank());
        }
        try {
            dn.getRule(NEG_VALUE);
        } catch (final IllegalArgumentException e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    } 

    /**
        Checks that no rule is empty or null in the DeathNote rules
    */
    @Test
    void TestValidRules() {
        for (int i = 1; i <= RULES.size(); i++) {
            final String rule = dn.getRule(i);          
            assertNotNull(rule);  
            assertFalse(rule.isBlank());
        }
    }

    /**
     * Checks that the human whose name is written in the DeathNote will eventually die
    */
    @Test
    void TestActualDeath() {
        assertFalse(dn.isNameWritten(NAME_PIPPO));
        dn.writeName(NAME_PIPPO);
        assertTrue(dn.isNameWritten(NAME_PIPPO));
        assertFalse(dn.isNameWritten(NAME_PAPERINO));
        assertFalse(dn.isNameWritten(EMPTY_STRING));
    }

    /**
     * Checks that only if the cause of death is written within the next 40 milliseconds of writing the person's name, it will happen
    */
    @Test
    void TestCauseOfDeath() throws InterruptedException {
        try {
            dn.writeDeathCause(CAUSE_HEART_ATTACK);
        } catch (IllegalStateException e1) {
            assertTrue(e1 instanceof IllegalStateException);
        }            
        dn.writeName(NAME_PIPPO);            
        assertEquals(CAUSE_HEART_ATTACK, dn.getDeathCause(NAME_PIPPO));
        dn.writeName(NAME_PAPERINO);            
        assertEquals(CAUSE_KARTING_ACCIDENT, dn.getDeathCause(NAME_PAPERINO));
        sleep(WAIT_CAUSE);
        assertFalse(dn.writeDeathCause("lung cancer"));
        assertEquals("karting accident", dn.getDeathCause(NAME_PAPERINO));   
    }

    /**
     * Checks that only if the cause of death is written within the next 6 seconds and 40 milliseconds of writing the death's details, it will happen
    */
    @Test
    void TestDeathDetails() throws InterruptedException {
        try {
            dn.writeDetails(DETAILS);
        } catch (IllegalStateException e1) {
            assertTrue(e1 instanceof IllegalStateException);
        } 
        dn.writeName(NAME_PIPPO); 
        assertEquals(EMPTY_STRING, dn.getDeathDetails(NAME_PIPPO));
        assertTrue(dn.writeDetails("run for too long"));
        assertEquals("run for too long", dn.getDeathDetails(NAME_PIPPO));
        dn.writeName(NAME_PAPERINO);
        sleep(6100);
        assertFalse(dn.writeDetails("he should have stopped earlier"));
        assertEquals(EMPTY_STRING, dn.getDeathDetails(NAME_PAPERINO));
    }
}