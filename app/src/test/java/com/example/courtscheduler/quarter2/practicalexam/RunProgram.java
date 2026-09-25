package com.example.courtscheduler.quarter2.practicalexam;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class RunProgram {
    @Test
    public void Run() {
        StringBuilder simulatedInput = new StringBuilder();
        simulatedInput.append("2\n");
        ByteArrayInputStream automatedInput = new ByteArrayInputStream(simulatedInput.toString().getBytes());

        Scanner masterScanner = new Scanner(automatedInput);

        MainMenu mainMenu = new MainMenu();
        mainMenu.Menu(masterScanner);
    }
}