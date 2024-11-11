package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {
    private static final int ZERO = 0;
    private static final int NEG_VALUE = -1;
    private DeathNoteImplementation dn;

    @BeforeEach
    void sitUp(){
        this.dn = new DeathNoteImplementation();
    }

    @Test
    void TestInvalidRules() {
        try {
            dn.getRule(ZERO);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertEquals(dn.getRule(ZERO), e);
            assertFalse(e.getMessage().isBlank());
        }
        try {
            dn.getRule(NEG_VALUE);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertEquals(dn.getRule(NEG_VALUE), e);
            assertFalse(e.getMessage().isBlank());
        }
    } 

}