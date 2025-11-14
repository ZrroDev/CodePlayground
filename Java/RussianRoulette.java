import java.util.Arrays;
import java.util.Scanner; // Read Input Library

public class RussianRoulette {

    static int[] bulletPos = {0};
    static int currentPos = 1;
    static int minBullets = 1;
    static int maxBullets = 6;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== RUSSIAN ROULETTE ===");
            System.out.println("1. Select how many bullets you want and spin the chamber");
            System.out.println("2. Shoot!");
            System.out.println("3. Print chamber state (debug)");
            System.out.println("4. Exit");
            int opcion = getIntInput(sc, "Choose an option (1-4): ", 1, 4);

            switch (opcion) {
                case 1 -> {
                    System.out.println("");
                    int b = getIntInput(sc, "How many bullets do you want to reload? (1-" + maxBullets + "): ", 1, maxBullets);
                    ReloadORSpinChamber(b);
                    System.out.println("");
                    System.out.println(b + " bullet(s) reloaded and chamber spinned");
                }
                case 2 -> {
                    Shoot();
                }
                case 3 -> {
                    PrintStatus();
                }
                case 4 -> {
                    System.out.println("Bye!");
                    sc.close();
                    return;
                }
            }
        }
    }

    static void ReloadORSpinChamber(int bulletsToReload) {

        bulletsToReload = bulletsToReload > maxBullets ?  maxBullets : bulletsToReload; // Not more than 6 bullets

        bulletPos = new int[bulletsToReload];

        for (int i = 0; i < bulletPos.length; i++) {
                
            int randomPos =  GetRandomPos(); // Random bullet position

            if (i > 0) {
                while (ContainsValue(bulletPos, randomPos, i)) {
                    randomPos = GetRandomPos();
                }
            }

            bulletPos[i] = randomPos; // Set the random pos for the bullet
        }

        currentPos = 1; // Reset current pos
    }

    static void Shoot() {

        if (bulletPos.length < 1) {
            System.out.println("");
            System.out.println("No bullets left, need to reload");
            
            return;
        }

        System.out.println("");
        System.out.println("Try number " + currentPos);
        
        for (int i = 0; i < bulletPos.length; i++) {
            
            if (currentPos == bulletPos[i]) {
                System.out.println("");
                System.out.println("You're dead!");

                // Empty the magazine on current pos
                int[] newArr = new int[bulletPos.length - 1];
                int k = 0; // index for auxiliar array

                for (int j = 0; j < bulletPos.length; j++) {
                    if (j == i) continue; // Skip the shot bullet
                    newArr[k] = bulletPos[j];
                    k++;
                }

                bulletPos = newArr;
                currentPos++; // Next chamber position 

                return;
            }
        }

        System.out.println("");
        System.out.println("You're safe...");

        currentPos++; // Next chamber position 
    }

    static void PrintStatus() {
        System.out.println("");
        System.out.println("Bullet/s position: " + Arrays.toString(bulletPos));
    }

    static int GetRandomPos() {
        return (int)(Math.random() * maxBullets) + minBullets;
    }

    static boolean ContainsValue(int[] arr, int value, int upToIndex) {
        for (int i = 0; i < upToIndex; i++) {
            if (arr[i] == value) return true;
        }
        return false;
    }

    // Read an int given by user (Needs a valid value on the specific given range)
    static int getIntInput(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNextInt()) {
                System.out.println("Not a valid input, enter a new number");
                sc.next(); // Consume no valid token
                continue;
            }
            int val = sc.nextInt();
            if (val < min || val > max) {
                System.out.println("Please enter a value between " + min + " y " + max + ".");
                continue;
            }
            return val;
        }
    }
}
