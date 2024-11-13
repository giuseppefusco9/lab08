package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    private static final int MS_TO_SLEEP_6100 = 6100;
    private static final int MS_TO_SLEEP_100 = 100;
    private static final String MAURO_ROSSI = "MAURO ROSSI";
    private static final String MARCO_VERDI = "MARCO VERDI";
    private DeathNote deathNote;

    @BeforeEach
    void init(){
        deathNote = new DeathNoteImplementation();
    }

    @Test
    public void testIllegalRule(){
        for(int i : List.of(-1 , 0 , DeathNote.RULES.size() + 1)){
            try{
                deathNote.getRule(i);
            }catch(IllegalArgumentException e){
                assertNotNull(e.getMessage());
                assertFalse(e.getMessage().isEmpty());
                assertFalse(e.getMessage().isBlank());
            }
        }
    }

    @Test
    public void testRules(){
        for(String rule : DeathNote.RULES){
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test
    public void testWriteName(){
        assertFalse(deathNote.isNameWritten(MARCO_VERDI));
        deathNote.writeName(MARCO_VERDI);
        assertTrue(deathNote.isNameWritten(MARCO_VERDI));
        assertFalse(deathNote.isNameWritten(MAURO_ROSSI));
        assertFalse(MARCO_VERDI.isBlank());
    }

    @Test
    public void testWriteDeathCause() throws InterruptedException{
        assertThrows(IllegalStateException.class, () -> {
            deathNote.writeDeathCause("fall from stairs");
        });
        deathNote.writeName(MARCO_VERDI);
        assertEquals("heart attack", deathNote.getDeathCause(MARCO_VERDI));
        deathNote.writeName(MAURO_ROSSI);
        assertTrue(deathNote.writeDeathCause("karting accident"));
        assertEquals("karting accident", deathNote.getDeathCause(MAURO_ROSSI));
        Thread.sleep(MS_TO_SLEEP_100);
        assertFalse(deathNote.writeDeathCause("breaking neck"));
        assertEquals("karting accident", deathNote.getDeathCause(MAURO_ROSSI));
    }

    @Test
    public void testWriteDetails() throws InterruptedException{
        assertThrows(IllegalStateException.class, () -> {
            deathNote.writeDetails("heart attack");
        });
        deathNote.writeName(MARCO_VERDI);
        assertEquals("", deathNote.getDeathDetails(MARCO_VERDI));
        assertTrue(deathNote.writeDetails("Ran for too long"));
        deathNote.writeName(MAURO_ROSSI);
        Thread.sleep(MS_TO_SLEEP_6100);
        assertFalse(deathNote.writeDetails("Got scared"));
        assertEquals("", deathNote.getDeathDetails(MAURO_ROSSI));

    }

}