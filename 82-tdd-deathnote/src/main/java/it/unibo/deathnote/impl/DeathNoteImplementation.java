package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

/**
 * DeathNoteImplementation
 */
public class DeathNoteImplementation implements DeathNote{

    private static final int CAUSE_TIME = 40;    
    private static final int DETAILS_TIME = 6040;
    
        Map <String, Death> deathnote = new HashMap<>();
        private String lastName;
    
        @Override
        public String getDeathCause(String name) {
			if(!deathnote.containsKey(name)) {
            	throw new IllegalArgumentException("The provided name is not written in this DeathNote");
			}
			return deathnote.get(name).getCause();
        }
    
        @Override
        public String getDeathDetails(String name) {
            if(!deathnote.containsKey(name)) {
            	throw new IllegalArgumentException("The provided name is not written in this DeathNote");
			}
			return deathnote.get(name).getDetails();
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
            return deathnote.containsKey(name);
        }
    
        @Override
        public boolean writeDeathCause(String cause) {
            if (cause == null || deathnote.isEmpty()) {
                throw new IllegalStateException("There is no name written in this DeathNote, or the cause is null");
            }
            if (getCurrentTime() < deathnote.get(this.lastName).time + CAUSE_TIME) {
                deathnote.get(this.lastName).setCause(cause);
                return true;
            }
            return false;
        }
    
        @Override
        public boolean writeDetails(String details) {
            if (details == null || deathnote.isEmpty()) {
                throw new IllegalStateException("There is no name written in this DeathNote, or the cause is null");
            }
            if (getCurrentTime() < deathnote.get(this.lastName).time + DETAILS_TIME) {
				deathnote.get(this.lastName).setCause(details);
				return true;
			}
			return false;
    	}

		@Override
		public void writeName(String name) {
			if (name == null) {
				throw new NullPointerException("The given name is null");
			}
			deathnote.put(name, null);
			this.lastName = name;
		}

			private static long getCurrentTime() {
		return System.currentTimeMillis();
		}

    private static class Death {


        private static final String EMPTY_STRING = "";
        private static final String DEF_CAUSE = "heart attack";
        private String cause;
        private String details;
        private final long time;
        
        private Death() {
            this.cause = DEF_CAUSE;
            this.details = EMPTY_STRING;
			this.time = getCurrentTime();
        }

        public String getCause() {
            return this.cause;
        }

        public String getDetails() {
            return this.details;
        }   
        
        public void setCause(String cause) {
            this.cause = cause;
        }

        public void setDetails(String details) {
            this.details = details;
        }

    }
    
}
