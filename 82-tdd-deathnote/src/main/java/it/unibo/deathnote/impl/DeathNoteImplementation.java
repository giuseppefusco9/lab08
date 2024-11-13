package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote {

    private Map<String, Death> deathNote;
    private String lastNameWritten;

    public DeathNoteImplementation(){
        this.deathNote = new HashMap<>();
        this.lastNameWritten = "";
    }

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > RULES.size()){
            throw new IllegalArgumentException("ruleNumber < 1 or > than the number of rules");
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if(name == null){
            throw new NullPointerException("The string given is null");
        }
        this.lastNameWritten = name;
        deathNote.put(name, new Death());
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(deathNote.isEmpty() || cause == null){
            throw new IllegalStateException("the string given is null or Death Note doesn't contain any name");
        }
        return deathNote.get(lastNameWritten).setCause(cause);
    }

    @Override
    public boolean writeDetails(String details) {
        if(deathNote.isEmpty() || details == null){
            throw new IllegalStateException("the string given is null or Death Note doesn't contain any name");
        }
        return deathNote.get(lastNameWritten).setDetails(details);
    }

    @Override
    public String getDeathCause(String name) {
        if(!isNameWritten(name)){
            throw new IllegalArgumentException("The name is not in the death note");
        }
        return deathNote.get(name).getCause();
    }

    @Override
    public String getDeathDetails(String name) {
        if(!deathNote.containsKey(name)){
            throw new IllegalArgumentException("The name is not in the death note");
        }
        return deathNote.get(name).getDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        return deathNote.containsKey(name);
    }

    private class Death{
        private static final String DEFAULT_CAUSE = "heart attack";
        private static final int VALID_DETAILS_MS = 6040;
        private static final int VALID_CAUSE_MS = 40;
        private String cause;
        private String details;
        private long deathTime;


        public Death(){
            this.deathTime = System.currentTimeMillis();
            this.cause = DEFAULT_CAUSE;
            this.details = "";
        }

        public boolean setCause(String cause){
            if(System.currentTimeMillis() < deathTime + VALID_CAUSE_MS){
                this.cause = cause;
                return true;
            }
            else{
                return false;
            }
        }

        public boolean setDetails(String details){
            if(System.currentTimeMillis() < deathTime + VALID_DETAILS_MS){
                this.details = details;
                return true;
            }
            else{
                return false;
            }
        }
        
        public String getCause(){
            return this.cause;
        }

        public String getDetails(){
            return this.details;
        }
    }

}
