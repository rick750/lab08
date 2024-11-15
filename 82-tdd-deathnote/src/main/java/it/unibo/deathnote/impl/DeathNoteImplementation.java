package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

/**
 * DeathNoteImplementation
 */
public class DeathNoteImplementation implements DeathNote{

    @Override
    public String getDeathCause(String name) {
        throw new IllegalArgumentException("The provided name is not written in this DeathNote");
    }

    @Override
    public String getDeathDetails(String name) {
        throw new IllegalArgumentException("The provided name is not written in this DeathNote");
    }

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber <= 0 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("The given rule number is smaller than 1 or larger than the number of rule");
        }
        return RULES.get(ruleNumber - 1);        
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean writeDeathCause(String cause) {
        throw new IllegalStateException("There is no name written in this DeathNote, or the cause is null");
    }

    @Override
    public boolean writeDetails(String details) {
        throw new IllegalStateException("There is no name written in this DeathNote, or the cause is null");
    }

    @Override
    public void writeName(String name) {
        throw new NullPointerException("The given name is null");
    }
    
}