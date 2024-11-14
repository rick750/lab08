package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.deathnote.api.DeathNote.RULES;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {
    private static final String NAME_PAPERINO = "Paperino";
    private static final String NAME_PIPPO = "Pippo";
    private static final int ZERO = 0;
    private static final int NEG_VALUE = -1;
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
        assertFalse(dn.isNameWritten(""));
    }



}