package se.lexicon.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ScannerInputService implements UserInputService {
    private final Scanner scanner;

    @Autowired
    public ScannerInputService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int getInt() {
        return scanner.nextInt();
    }

    @Override
    public String getString() {
        scanner.nextLine();  // Consume newline left-over
        return scanner.nextLine();
    }
}
