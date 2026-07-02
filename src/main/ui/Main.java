package ui;

import model.Intention;
import model.card.AttackCard;
import model.card.Card;
import model.card.DefendCard;
import model.enemy.Enemy;
import model.player.Player;
import model.room.BattleRoom;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Card> playerCards = new ArrayList<>();
        playerCards.add(new AttackCard("Strike", 6, 1, "Deals 6 damage."));
        playerCards.add(new DefendCard("Defend", 5, 1, "Gives 5 block."));

        Player player = new Player("Player 1", playerCards);
        Enemy enemy = new Enemy("Slime", 20, 5);

        BattleRoom room = new BattleRoom(1);
        room.addEnemy(enemy);
        room.enterRoom(player);
        room.displayRoomDetails();

        runCombat(player, enemy);
    }

    private static void runCombat(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);

        while (player.getHealth() > 0 && !enemy.isDefeated()) {
            player.resetEnergy();
            player.clearBlock();
            printTurnStart(enemy);
            playPlayerTurn(scanner, player, enemy);

            if (!enemy.isDefeated()) {
                executeEnemyTurn(player, enemy);
                enemy.nextIntention();
            }
        }

        System.out.println(enemy.isDefeated()
                ? "You defeated " + enemy.getName() + "!"
                : "You were defeated.");
    }

    private static void printTurnStart(Enemy enemy) {
        System.out.println("\n--- Player turn ---");
        if (enemy.getBlock() > 0) {
            System.out.println(enemy.getName() + " starts this turn with "
                    + enemy.getBlock() + " block.");
        }
    }

    private static void executeEnemyTurn(Player player, Enemy enemy) {
        enemy.clearBlock();

        if (enemy.getCurrentIntention() == Intention.ATTACK) {
            int blockBeforeAttack = player.getBlock();
            int healthBeforeAttack = player.getHealth();
            int damageTaken = player.takeDamage(enemy.getAttackDamage());
            int blockedDamage = Math.min(blockBeforeAttack, enemy.getAttackDamage());
            System.out.println(enemy.getName() + " attacks for "
                    + enemy.getAttackDamage() + ". You take " + damageTaken + " damage.");
            if (blockedDamage > 0) {
                System.out.println("You block " + blockedDamage + " damage.");
            }
            System.out.println("Player health: " + healthBeforeAttack
                    + " -> " + player.getHealth());
            System.out.println("Player block remaining: " + player.getBlock());
        } else if (enemy.getCurrentIntention() == Intention.DEFEND) {
            enemy.defend();
            System.out.println(enemy.getName() + " gains " + enemy.getDefense() + " block.");
        }
    }

    private static void playPlayerTurn(Scanner scanner, Player player, Enemy enemy) {
        while (player.getEnergy() > 0 && !enemy.isDefeated()) {
            printCombatState(player, enemy);
            printCards(player);
            System.out.print("Choose a card number, or 0 to end your turn and let the enemy act: ");

            Integer choice = readNumber(scanner);
            if (choice == null) {
                System.out.println("Please enter a number.");
                continue;
            }
            if (choice == 0) {
                break;
            }
            if (choice < 1 || choice > player.getDeck().size()) {
                System.out.println("That card does not exist.");
                continue;
            }

            Card card = player.getDeck().get(choice - 1);
            if (card.getCost() > player.getEnergy()) {
                System.out.println("You do not have enough energy.");
                continue;
            }

            player.setEnergy(player.getEnergy() - card.getCost());
            if (card.getType() == Intention.ATTACK) {
                int attemptedDamage = card.getStatAmount();
                int blockBeforeAttack = enemy.getBlock();
                int healthDamage = enemy.takeDamage(attemptedDamage);
                int blockedDamage = Math.min(blockBeforeAttack, attemptedDamage);

                System.out.println("You deal " + healthDamage + " damage to "
                        + enemy.getName() + ".");
                if (blockedDamage > 0) {
                    System.out.println(enemy.getName() + " blocks " + blockedDamage + " damage.");
                }
            } else if (card.getType() == Intention.DEFEND) {
                player.addBlock(card.getStatAmount());
                System.out.println("You gain " + card.getStatAmount() + " block.");
            }
        }
    }

    private static void printCombatState(Player player, Enemy enemy) {
        System.out.println("\n" + player.getName() + ": " + player.getHealth()
                + " HP, " + player.getEnergy() + " energy, " + player.getBlock() + " block");
        System.out.println(enemy.getName() + ": " + enemy.getHealth()
                + " HP, " + enemy.getBlock() + " block");
        System.out.println("Enemy's next action: " + formatIntention(enemy));
    }

    private static String formatIntention(Enemy enemy) {
        if (enemy.getCurrentIntention() == Intention.ATTACK) {
            return "ATTACK for " + enemy.getAttackDamage() + " damage";
        }
        if (enemy.getCurrentIntention() == Intention.DEFEND) {
            return "DEFEND";
        }
        return enemy.getCurrentIntention().name();
    }

    private static void printCards(Player player) {
        for (int i = 0; i < player.getDeck().size(); i++) {
            Card card = player.getDeck().get(i);
            System.out.println((i + 1) + ". " + card.getName()
                    + " (" + card.getCost() + " energy, "
                    + card.getStatAmount() + " " + card.getType().name().toLowerCase() + ")");
        }
    }

    private static Integer readNumber(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}
