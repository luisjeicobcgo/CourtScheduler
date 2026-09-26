package com.example.courtscheduler.quarter2.practicalexam;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

public class RunProgram {
    @Test
    public void Run(){
        int interactionCount = 1;
        StringBuilder simulatedInput = new StringBuilder();
        simulatedInput.append("1\n");
        simulatedInput.append("1\n");
        while (interactionCount <= 2){
            if (interactionCount == 1){
                simulatedInput.append("10:00 AM\n");
                simulatedInput.append("11:00 AM\n");
                simulatedInput.append("September 30 2026\n");
                simulatedInput.append("Basketball\n");
            }
            else {
                simulatedInput.append("1\n");
                simulatedInput.append("1:00 PM\n");
                simulatedInput.append("3:00 PM\n");
                simulatedInput.append("September 30 2026\n");
                simulatedInput.append("Volleyball\n");
            }
            interactionCount++;
        }

        simulatedInput.append("2\n");
        simulatedInput.append("1\n");
        simulatedInput.append("1\n");
        simulatedInput.append("3\n");

        ByteArrayInputStream automatedInput = new ByteArrayInputStream(simulatedInput.toString().getBytes());

        Scanner masterScanner = new Scanner(automatedInput);

        MainMenu mainMenu = new MainMenu();

        mainMenu.Menu(masterScanner);

    }
}
