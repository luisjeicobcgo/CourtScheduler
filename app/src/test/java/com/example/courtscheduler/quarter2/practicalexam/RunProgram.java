package com.example.courtscheduler.quarter2.practicalexam;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.Test;

public class RunProgram {
    @Test
    public void Run(){
        StringBuilder simulatedInput = new StringBuilder();
        simulatedInput.append("1\n");
        simulatedInput.append("1\n");
        simulatedInput.append("10:00 AM\n");
        simulatedInput.append("11:00 AM\n");
        simulatedInput.append("September 30 2026\n");
        simulatedInput.append("Basketball\n");
        simulatedInput.append("2\n");
        ByteArrayInputStream automatedInput = new ByteArrayInputStream(simulatedInput.toString().getBytes());

        Scanner masterScanner = new Scanner(automatedInput);

        MainMenu mainMenu = new MainMenu();

        mainMenu.Menu(masterScanner);

    }
}
