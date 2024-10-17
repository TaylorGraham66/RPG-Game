import java.util.Random;
import java.util.Scanner;

public class tgrah_prog4 {
	
	
	public static void main(String[] args) {
				
	
		System.out.println("While wondering through the woods, you encountered a wild goblin! It's dual wielding knives and looks hungry... Fight or Die!");
		
		Boolean killGob = false;



		Scanner inpt = new Scanner(System.in);
			
		// Main do While loop
		do{
				
			System.out.printf("Player HP: %d/100, MP: %d/50", chance.playerHP, chance.playerMP);
			System.out.printf("\nGoblin HP: %d/80\n", chance.gobHP);
			
			if(chance.playerHP <= 0) {
				System.out.println("[*]You have died! Game Over!");
				chance.quit = true;
				break;
			}else if(chance.gobHP <= 0) {
				System.out.println("[*]You have killed the goblin and gained 10xp!");
				chance.quit = true;
				killGob = true;
				chance.xp += 10;
				levelUp();
				skillUpgrade();
				break;
			}else if(chance.flee) {
				break;
			}

			System.out.printf("Choose your Action! \n1. Basic Attack\n2. Fireball\n3. Heal\n4. Empower\n5. Flee\n");	
			
			String choice = inpt.nextLine();
			
			if(choice.toLowerCase().equals("basic") || choice.equals("1") || choice.toLowerCase().equals("basic attack")) {
				playerBasic();
				gobAttack();
			}else if(choice.toLowerCase().equals("fireball") || choice.equals("2")) {
				playerFire();
				gobAttack();
			}else if(choice.toLowerCase().equals("heal") || choice.equals("3")) {
				playerHeal();
				gobAttack();
			}else if(choice.toLowerCase().equals("empower") || choice.equals("4")) {
				empower();
				gobAttack();
			}else if(choice.toLowerCase().equals("flee") || choice.equals("5")) {
				flee();
			}else {
				System.out.println("Action not recognized");
			}
				
			
			
		}while(!chance.quit);

		
		//After Battle

		if(killGob) {

			System.out.println("[*]After defeating the Goblin you decide to go back to your village");



		}else if(chance.flee) {
			System.out.println("[*]The goblin was too strong... you need to train harder");
		}

		inpt.close();
	}
	
	//Methods Used
		
		//Global Variables https://nam04.safelinks.protection.outlook.com/?url=https%3A%2F%2Fstackoverflow.com%2Fquestions%2F4646577%2Fglobal-variables-in-java&data=05%7C02%7Ctgrah22%40lsu.edu%7C90cdba686cbb47fc241008dcea5d9454%7C2d4dad3f50ae47d983a09ae2b1f466f8%7C0%7C0%7C638642934836295789%7CUnknown%7CTWFpbGZsb3d8eyJWIjoiMC4wLjAwMDAiLCJQIjoiV2luMzIiLCJBTiI6Ik1haWwiLCJXVCI6Mn0%3D%7C0%7C%7C%7C&sdata=2EDxepE1oUgOpyQvXQ7HgIKYKEtf%2FLTeXzZe5iuOcvA%3D&reserved=0
		public class chance {
		
			//Roll Chance Variables
			public static int playerLowBasic = 5;
			public static int playerHighBasic = 10;
			public static int playerLowFire = 10;
			public static int playerHighFire = 25;
			public static int playerLowHeal = 20;
			public static int playerHighHeal = 30;
			
			//Resource Variables
			public static int playerHP = 100;
			public static int playerMP = 50;
			public static int gobHP = 80;
			public static int dmg = 0;
			public static int heal = 0;
			
			//Sentinel Value / Booleans
			public static boolean quit = false;
			public static boolean flee = false;
			public static boolean levelUp = false;

			//Experience Variables
			public static int xp = 0;
			public static int expToLevel = 10;
			public static int level = 1;

			//Character Values
			public static int strength = 0;
			public static int magic = 0;
			public static int health = 0;
			public static int skillPoint = 0;
		}
	
		public static void playerBasic() {
		
			Random rand = new Random();
			int miss = rand.nextInt(1,11);
		
			if(miss < 9) {
			chance.dmg = rand.nextInt(chance.playerLowBasic, chance.playerHighBasic + 1);
			chance.dmg += chance.strength * 10;
			chance.gobHP -= chance.dmg;
			System.out.printf("Your basic attack landed! It did %d damage!\n", chance.dmg);
			}else {
				System.out.println("You're basic attack missed!");
				chance.dmg = 0;
			}
		}
		
		public static void playerFire() {
			Random rand = new Random();

			if(chance.playerMP >= 6) {
				chance.dmg = rand.nextInt(chance.playerLowFire, chance.playerHighFire + 1);
				chance.dmg += chance.magic * 10;
				chance.gobHP -= chance.dmg;
				System.out.printf("Your fireball landed! It did %d damage! It used 6 MP\n", chance.dmg);
				chance.playerMP -= 6;
			}else {
				System.out.println("You don't have enough MP! Failed to cast!");
				chance.dmg = 0;
			}
		}
		
		public static void playerHeal() {
			Random rand = new Random();

			
				if(chance.playerMP >= 8) {
					chance.heal = rand.nextInt(chance.playerLowHeal, chance.playerHighHeal + 1);
					if((chance.playerHP + chance.heal) <= 100) {
						chance.heal += chance.health * 10;
						chance.playerHP += chance.heal;
						System.out.printf("You healed %d HP! It used 8 MP\n", chance.heal);
						chance.playerMP -= 8;
					}else {
						System.out.println("You healed to full!");
						chance.playerHP = 100;
					}
				}else {
					System.out.println("You don't have enough MP! Failed to cast!");
					chance.heal = 0;
				}

		}
		
		public static void empower() {
			
			if(chance.playerMP >= 10) {
				chance.playerLowBasic++;
				chance.playerHighBasic += 2;
				chance.playerLowFire++;
				chance.playerHighFire += 2;
				chance.playerLowHeal++;
				chance.playerHighHeal += 2;
				chance.playerMP -= 10;
				System.out.println("You have permanently empowered your stats!");
			}else {
				System.out.println("You don't have enough MP! Failed to cast!");
			}	
		}
		
		public static void flee() {
			Random rand = new Random();
			int fleeChance = rand.nextInt(1,5);
	
			if(fleeChance < 4) {
				System.out.println("You have escaped");
				chance.quit = true;
				chance.flee = true;
			}else {
				System.out.println("The goblin stabbed you in the back while you were trying to run away! You died!");
				chance.quit = true;
			}	
		}
		
		public static void gobAttack() {
			Random rand = new Random();
			
			if(chance.gobHP > 0) {
				for(int i = 1; i < 3; i++) {
					int miss = rand.nextInt(1,11);
					if(miss <= 6) {
					int gobDMG = rand.nextInt(3,8);
					chance.playerHP -= gobDMG;
					System.out.printf("The goblin attacked! it did %d damage!\n", gobDMG);
				}else {
					System.out.println("The goblin missed!");
				}
				}
			}else {
				System.out.printf("");
			}
			}

			public static void levelUp() {

				if(chance.xp >= chance.expToLevel) {
					chance.level++;
					chance.expToLevel *= 2;
					chance.playerLowBasic += 4;
					chance.playerHighBasic += 6;
					chance.playerLowFire += 4;
					chance.playerHighFire += 6;
					chance.playerLowHeal += 4;
					chance.playerHighHeal += 6;
					chance.playerHP += 50;
					chance.playerMP += 25;
					chance.skillPoint++;
					System.out.println("[*]You have leveled up! You feel the power surging through you!");
				}

			}

			public static void skillUpgrade() {

				if(chance.skillPoint > 0) {
					
					System.out.println("[*]You have a skill point. Decide if you want to upgrade your Strength, Magic, or Health.");

					Scanner skillInpt = new Scanner(System.in);
					String decision = skillInpt.nextLine();

					if(decision.toLowerCase().equals("strength")) {
						chance.strength++;
						System.out.println("[*]You have increased your strength!");
					}else if(decision.toLowerCase().equals("magic")) {
						chance.magic++;
						System.out.println("[*]You have increased your magic power!");
					}else if(decision.toLowerCase().equals("health")) {
						chance.health++;
						System.out.println("[*]You have increased your health!");
					}else {
						System.out.println("[*]Input not recognized, please type 'Strength', 'Magic', or 'Health'.");
						skillUpgrade();
					}

					skillInpt.close();
				}

			}

		}

		
	
	
	
	

