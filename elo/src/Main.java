import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class elo {

  public static void main(String[] args) {
    int heres = 0;
    int players = 107;
    ArrayList<Integer> elos = new ArrayList<>();
    for (int i = 0; i <= players; i++) {
      elos.add(1000);
    }
    elos.add(1600);
    elos.add(1800);
int count = 0;
    while (count < 1000001) {
      System.out.println(count);
      ArrayList<Integer> picks = pickSix(elos);
      Collections.sort(picks);
      Collections.reverse(picks);

      ArrayList<Integer> teamA = new ArrayList<>();
      ArrayList<Integer> teamB = new ArrayList<>();

      for (int i : picks) {
        if (i == 1) {
          System.out.println("HERE");
          heres += 1;
        }
        Random random = new Random();
        int randomNumber = random.nextInt(2) + 1;
        if (randomNumber == 1) {
          if (teamA.size() != 3) {
            teamA.add(elos.get(i - 1));
          } else {
            teamB.add(elos.get(i - 1));
          }
        } else {
          if (teamB.size() != 3) {
            teamB.add(elos.get(i - 1));
          } else {
            teamA.add(elos.get(i - 1));
          }
        }

//      System.out.println(teamA);
//      System.out.println(teamB);
        elos.remove(i - 1);
      }

      float Ra = 0, Rb = 0;

      for (int number : teamA) {
        Ra += number;
      }

      for (int number : teamB) {
        Rb += number;
      }

      int K = 30;
      System.out.println("ELO A: " + Ra);
      System.out.println("ELO B: " + Rb);
      boolean d = aWin(Ra, Rb);

      if (d) {
        System.out.println("A Wins");
        int winnings = (int) (EloRating(Ra, Rb, K, d) - Ra) * 3;

        Collections.sort(teamA);
        System.out.println(teamA);
        float invOne = 1 / (teamA.get(0) / Ra);
        float invTwo = 1 / (teamA.get(1) / Ra);
        float invThree = 1 / (teamA.get(2) / Ra);
        float invTotal = invOne + invTwo + invThree;
        int oneWin = Math.round((invOne / invTotal) * winnings);
        int twoWin = Math.round((invTwo / invTotal) * winnings);
        int threeWin = winnings - oneWin - twoWin;

        teamA.set(0, teamA.get(0) + oneWin);
        teamA.set(1, teamA.get(1) + twoWin);
        teamA.set(2, teamA.get(2) + threeWin);
        //System.out.println(winnings);
        //System.out.println(teamA);

        Collections.sort(teamB);
        System.out.println(teamB);
        float normOne = (teamB.get(0) / Rb);
        float normTwo = (teamB.get(1) / Rb);
        float normThree = (teamB.get(2) / Rb);
        float normTotal = normOne + normTwo + normThree;
        int oneLose = Math.round((normOne / normTotal) * winnings);
        int twoLose = Math.round((normTwo / normTotal) * winnings);
        int threeLose = winnings - oneLose - twoLose;

        teamB.set(0, teamB.get(0) - oneLose);
        teamB.set(1, teamB.get(1) - twoLose);
        teamB.set(2, teamB.get(2) - threeLose);

        System.out.println("Winnings: " + winnings);
        System.out.println(teamA.get(0)-oneWin + " -> " + teamA.get(0) + " (+" + oneWin + ")");
        System.out.println(teamA.get(1)-twoWin + " -> " + teamA.get(1) + " (+" + twoWin + ")");
        System.out.println(teamA.get(2)-threeWin + " -> " + teamA.get(2) + " (+" + threeWin + ")");

        System.out.println(teamB.get(0)+oneLose + " -> " + teamB.get(0) + " (-" + oneLose + ")");
        System.out.println(teamB.get(1)+twoLose + " -> " + teamB.get(1) + " (-" + twoLose + ")");
        System.out.println(teamB.get(2)+threeLose + " -> " + teamB.get(2) + " (-" + threeLose + ")");
      } else {
        System.out.println("B Wins");
        int winnings = (int) (EloRating(Ra, Rb, K, d) - Rb) * 3;

        Collections.sort(teamB);
        float invOne = 1 / (teamB.get(0) / Rb);
        float invTwo = 1 / (teamB.get(1) / Rb);
        float invThree = 1 / (teamB.get(2) / Rb);
        float invTotal = invOne + invTwo + invThree;
        int oneWin = Math.round((invOne / invTotal) * winnings);
        int twoWin = Math.round((invTwo / invTotal) * winnings);
        int threeWin = winnings - oneWin - twoWin;

        teamB.set(0, teamB.get(0) + oneWin);
        teamB.set(1, teamB.get(1) + twoWin);
        teamB.set(2, teamB.get(2) + threeWin);
        //System.out.println(winnings);

        Collections.sort(teamA);
        float normOne = (teamA.get(0) / Ra);
        float normTwo = (teamA.get(1) / Ra);
        float normThree = (teamA.get(2) / Ra);
        float normTotal = normOne + normTwo + normThree;
        int oneLose = Math.round((normOne / normTotal) * winnings);
        int twoLose = Math.round((normTwo / normTotal) * winnings);
        int threeLose = winnings - oneLose - twoLose;

        System.out.println(teamA);
        System.out.println(teamB);

        teamA.set(0, teamA.get(0) - oneLose);
        teamA.set(1, teamA.get(1) - twoLose);
        teamA.set(2, teamA.get(2) - threeLose);
        System.out.println("Winnings: " + winnings);
        System.out.println(teamA.get(0)+oneLose + " -> " + teamA.get(0) + " (-" + oneLose + ")");
        System.out.println(teamA.get(1)+twoLose + " -> " + teamA.get(1) + " (-" + twoLose + ")");
        System.out.println(teamA.get(2)+threeLose + " -> " + teamA.get(2) + " (-" + threeLose + ")");

        System.out.println(teamB.get(0)-oneWin + " -> " + teamB.get(0) + " (+" + oneWin + ")");
        System.out.println(teamB.get(1)-twoWin + " -> " + teamB.get(1) + " (+" + twoWin + ")");
        System.out.println(teamB.get(2)-threeWin + " -> " + teamB.get(2) + " (+" + threeWin + ")");
      }
      elos.addAll(teamA);
      elos.addAll(teamB);
      Collections.sort(elos);
      Collections.reverse(elos);
      System.out.println(elos);
      System.out.println("");
      count += 1;
      System.out.println(heres);
    }
  }

  static ArrayList<Integer> pickSix(ArrayList<Integer> elos) {
    ArrayList<Integer> chosen = new ArrayList<>();
    while (chosen.size() < 6) {
      Random random = new Random();
      int randomNumber = random.nextInt(elos.size()) + 1;
      if (!chosen.contains(randomNumber)) {
        chosen.add(randomNumber);
      }
    }
    return chosen;
  }

  static Boolean aWin(float a, float b) {
    DecimalFormat df = new DecimalFormat("#.000");
    String roundedNumber = df.format(Probability(b, a));
//    System.out.println(roundedNumber);
    Float rounded = Float.parseFloat(roundedNumber) * 1000;
    System.out.println(rounded/10 + "% TeamA win");

    Random random = new Random();
    int randomNumber = random.nextInt(1000) + 1;

    return randomNumber <= rounded;
  }

  static float Probability(float rating1, float rating2) {
    return 1.0f
        / (1
        + (float) (Math.pow(
        10, 1.0f * (rating1 - rating2)
            / 400)));
  }

  // Function to calculate Elo rating
  // K is a constant.
  // d determines whether Player A wins
  // or Player B.
  static int EloRating(float Ra, float Rb, int K,
      boolean d) {

    // To calculate the Winning
    // Probability of Player B
    float Pb = Probability(Ra, Rb);

    // To calculate the Winning
    // Probability of Player A
    float Pa = Probability(Rb, Ra);

//    System.out.println(Pb + " player b");
//    System.out.println(Pa + " player a");

    // Case -1 When Player A wins
    // Updating the Elo Ratings
    if (d == true) {
      Ra = Math.round(Ra + K * (1 - Pa));
      Rb = Math.round(Rb + K * (0 - Pb));
      return (int) Ra;
    }

    // Case -2 When Player B wins
    // Updating the Elo Ratings
    else {
      Ra = Math.round(Ra + K * (0 - Pa));
      Rb = Math.round(Rb + K * (1 - Pb));
      return (int) Rb;
    }

//    System.out.print("Updated Ratings:-\n");
//    System.out.print(
//        "Ra = "
//            + Math.round(Ra)
//            + " Rb = "
//            + Math.round(Rb));
//
//    ArrayList<Float> myFinal = new ArrayList<>(List.of(Ra, Rb));
  }
}

// This code is contributed by Anant Agarwal.
// (Used to determine the winnings a team should get)
