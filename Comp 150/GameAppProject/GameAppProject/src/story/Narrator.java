package story;

import java.util.Arrays;


import logic.DataManager;
import logic.Game;
import java.util.Random;


import static logic.Random.rand;

/**
 * This class takes care of describing what is happening in the story events of our game.
 * It is much easier to keep track of all the parts of our story in one place: this class.
 */
public class Narrator {
	// There is only one interactive story, so all methods are static.
	private final static int FINAL_CHAPTER = 10;
	private static int chapter = 0;

	private static DataManager data;
	private static int mychoice = rand(1)+1;

	// If everything is static, then we never need a constructor.
	// All variables only store one primitive value, or one instance of an object.
	// All methods can execute on the shared variables above.
	
	/**
	 * Connect to the same data instance used by the Game. So the Narrator can know
	 * what the player's name is, and whether they have died or not.
	 * 
	 * @param data
	 *   Should pass in the same data instance used by the Game.
	 */
	public static void setData(DataManager data) {
		Narrator.data = data; // instance shared by Game, Narrator
	}
	
	/**
	 * A method to get the different parts of our story. Each time this method is
	 * executed, the chapter number advances by one value. Extend the given code,
	 * or you could design your story to behave in a completely different way if 
	 * you want. For example, creating descriptions of your story or character 
	 * dialogue as strings in an array or collection, and choosing parts of it
	 * 	 * in different ways, perhaps partly random.
	 *
	 * @return
	 *   An array of string descriptions.
	 */
	public static String[] tell() {
		if (data.isPlayerChosen() && data.isPlayerDead()) {
			chapter = FINAL_CHAPTER;
		}

		switch (chapter++) {
			case 0:
				return begin();
			case 1:
				return chapter1();
			case 2:
				return chapter2();
			case 3:
				return chapter3();
			case 4:
				return chapter4();
			case 5:
				return chapter5();
			case 6:
				return chapter6();
			case 7:
				return chapter7();
			case 8:
				return chapter8();
			case 9:
				return chapter9();

			case FINAL_CHAPTER:
				return end();
			default:
				return new String[] { "Done telling a story. (debug)" };
		}
	}

	/**
	 * This method returns the current that chapter that we're on
	 * @return
	 * <>chapter</>
	 */
	public static int getChapter(){
		return chapter;
	}
	// The start of the story.
	private static String[] begin() {
		/* 
		 * String of dashes below is example of maximum length line we can have in
		 * a Billboard (62 chars) it is designed this way in the Billboard code by way
		 * of throwing Exceptions. This forces other programmers to follow your
		 * method designs and get useful debug error messages that are expected from
		 * taking advantage of exceptions.
		 *  "--------------------------------------------------------------"
		 */
		String[] intro = {
			"Chapter 0",
			"Hello, " + data.getUserName() + "!",
			"--- A frail voice beckons---",
			"", // a blank line
			"Hero....The realm lies in waste",
			"",
			"it has been a milenia since your last appearance",
			"",
			"The people waver as Lord Illidan wages war against our world",
			"",
			"Be the courage that they need",
			"",
			"I pray thee....In the name of all that is holy and right",
			"",
			"Dawn your armor and lead us once again to victory",
		};
		return intro;
	}
	
	// You could give your own methods useful scene names to help yourself
	// keep track of what parts of your story are about and their order.
	private static String[] chapter1() {
		String[] prose = {
			"Chapter 1"	,
			"---You awake bound and headed towards execution---",
			"A dark elf in carriage whispers to you words ",
			"she urgers you that you are the 'one' ",
			"Sent to save this once prosperous land of valoran",
			"",
			"---You arrive to the site of execution---",
			"by an iron-bar gate---its bars bent wide",
			"you begin to wonder...is this it? ",
			"All I was destined for?",
			"*The sky cries*",
			" A strand of lightning falls and with it, calvalry",
			"?: what a shame to see you so low when you once stood",
			"so high",
			"",
			"You're helplless to watch as thunder lays waste to all ",
			"all that remains is you and the dark elf",
			"a winged being descends from above",
			"raise your head, we've got a war to fight",
			"My name is Sylvanas, Sylvanas Airwalker",
			"Your strength fails you as these words are the last",
			"you hear before passing out.",
			""
		};
		//prose = combine(prose, data.describeMonsters());
		return prose;
	}

	private static String[] chapter2() {
		String[] prose = {
		"Chapter 2",
		"--You awake to the clash of blades--",
		"S: you're awake, thank the twelve",
		"S: you seemed to have been exhausted so we brought you back, ",
		"S: back to our Sanctuary",
		"*You are Fed and Clothed*",
		("S: Forgive me " + DataManager.getUserName() +" but"),
		"S: You are blessed with a power greater than you could",
		"ever imagine...Hero of Light please train with us at once!",
		"*you are led to a field of monsters*",
		"*Sylvanas hands you a sword",
		"Here, This blade known only by the name  of",
		"'MoonFang HeavenPiercer' or in other words ",
		"The blade that will reach the heavens",
		("I want to see you slay those creeps " + DataManager.getUserName())
		};
	    prose = combine(prose, data.describeMonsters()); // if we add on a prose we must have data for the monsters we want to describe.Furthermore we must have monster objects themselves
		return prose;
	}

	private static String[] chapter3() {
		String[] prose = {
		"Chapter 3"	,
		"exhausted , you are left on your behind after the battle",
		"S: It seems that you are worn out after such a meager",
		"encounter. That certainly will not do. If that's all it ",
		"takes to break you, then I'm afraid you may only return as ",
		"a war casualty.",
		"S: here have a regeneration potion",
		"*You are handed a regeneration potion*",
		"Come, we have much preparation to do",
		"--The next 12 wolf moons are spent training in preparation--",
		"--The time is rough and rigorous--",
		"As an outsider, you are shunned at first but through merit",
		"you gain the trust of the towns people and after many great",
		"and heroic deeds your name is known throughout the land",
		"a fortnite before the expected time of the Invasion of",
		"Lord Illidan, you find your self in the fields once more.",
		"elegant as a river, yet powerful like a watterfall",
		"You and your blade are now as one. With Eyes closed you look",
		"within and see a light, it calls to you time and time again",
		"only for you to be awoken by the calls of V'halmeara",
		"Enter: V'halmeara: S rank Battle Mage known for her",
		"Formidable magic.",
		(DataManager.getUserName() + "!" + DataManager.getUserName() + "!" + DataManager.getUserName() + "!"),
		"They're here, the legion is here!",

		"Before you head off to battle, V'halmeara offers you 1 of 3",
		"items to be of use to you in battle."
		};
		//prose = combine(prose, data.describeMonsters());
		return prose;
	}

	private static String[] chapter4() {

		String[] prose = {
		"Chapter 4",
		"Your Resolve is firm and found",
		"The promises you made, the people you swore to protect",
		"and all the dreams of the people of Valoran. You vow",
		"To keep your purpose in this world to fulfil destiny",
		"S: " + (DataManager.getUserName() + "! we will accompany"),
		("you into battle!"),
		"--You are joined by V'halmeara and Sylvanas--",
		"the three of you ride off into the battle",
		"The wails of war fill the air, it all seemsto familiar yet ",
		"you press forward determined on your goal.",
		"A ball of fire comes down upon your trio yet it is not",
		"Prevailent , as V'halmeeara scoff at thelowly attack that ",
		"forced her to use her shield ability",
		"V'halmeara: Such magic could never be of trouble to me",
		"S: Splitting up here would be the best option,I will lead ",
		"and coordinate the troops, V'halmeara pleaseassist our lowe",
		"level mages",
		(DataManager.getUserName() + " you must go fourth and bring"),
		"ruination to Illidans plan for this realm",
		"as such you charge into the eye of the battle!"
		};
		prose = combine(prose, data.describeMonsters());
		return prose;
	}
	private static String[] chapter5() {
		String[] prose = {
		"Chapter 5",
		"As the battle rages on, you see light far off in the ",
		"distance. A strand of Lightning follows, this... this ",
		"could only be.....Sylvanas' ultimate move:",
		"The lightning Branch of Crucifixion.",
		"An explosion decimates the warzone that Sylvanas gazes at",
		"the opposition is wiped out entirely",
		"silence fills her battlefield until victorious warcries begin",
		"to sound.",
		"To your right, you feel a massive amount of mana condensing",
		"unsure of whether it is a foe or ally, you clench your sword",
		"tighter than ever, ",
		"",
		"V: Everlasting roar of the water dragon!!! V'halmeara calls ",
		"to her staff.",
		"you shiver as Leviathan himmself manifests ",
		"and lays waste to Illidans legion of demon hunters",
		"Amazed, you resolve to also tie up your own loose ends",
		"Although only practiced once, you decide to use 'that' move",
		"Demacian Justice",
		"You plunge your blade in the earth and call upon the might of",
		" Valoran to execute the targeted enemies",
		"Onward we march",
		"--Or so we believed"
		};
		return prose;
	}
	private static String[] chapter6(){

		String[] prose = {
		"Chapter 6",
		"*The sky darkens as a voice appears out of nowhere",
		"They think me defeated, enchained. But I am unbowed...",
		"Noble is this carnage.",
		"Challenge me, mortals! I am here!",
		"MY name is Illidan, and I am not YOUR enemy, I am THE enemy",
		"*Illidan peers in your direction*",
		"I: I sense his mana deep within you",
		"Pantheon, I see you! Like a lamprey you attach yourself",
		"to a host! I will carve you from this mortal's form,",
		"and eviscerate you both!",
		"the being lunges towards you only to be stopped by V'halmearas",
		"shield.",
		"S: I will snuff out the darkness and make light eternal!",
		"Sylvanas cries out as she flanks Illidan",
		"I: You would bind yourself to this realm. Sylvanas? Fine,",
		"let me show you the ground!",
		"The two duke it down, as blades clash over and over",
		"You rush to the side of Sylvanas as V'halmeara provides",
		"artillery and support."

		};

		prose = combine(prose, data.describeMonsters());
		return prose;
	}
	private static String[] chapter7(){
	String[] prose ={
	"Chapter 7",
	"Even against the odds, Illidans strength is unfathomable, ",
	"Bloodied and on one knee, the thoughts forbidden to a warrior",
	"begin to creep in ...Is this the end? Is this where we perish?",
	"",
	"the light fades, and a voice calls to you from a distant light",
	"?: Is that all you have to offer?!! I would never have thought",
	"myself wrong is choosing a vessel. I suppose you dying here would",
	"also kill me too prove me right and show me what you truly",
	"are capable of. Mortal. Pantheon the climber of stars abides",
	"in you",

	"--Memories of an ancient time come flooding to you--",
	"With fire in his eye and the wind under his feet",
	"he fled the destruction. In the chill of the night-time",
	"when naught but a shrew shuffled in the undergrowth",
	"her home was reduced to embers in a moment. A flash of a cannon,",
	"the roar of an army on the verge of assault,",
	"and the shrill screech of a vole clutched by the midnight owl",
	"which patrolled the skies signaled the end of his pacific life.",
	"Behind him, crushed under the hammer of war, his family's resting",
	"place receded into the distance. Vengeance stirred within; ",
	"the fire of his eye caught the tinder in his heart."
	};
		return prose;
	};
	private static String[] chapter8() {
		String[] prose = {
				"Chapter 8",
				"*You awake again in the battle*",
				"broken but not defeated Sylvanas and V'halmeara stand firm",
				"Inpsired, a new power flows within you as you glow with life",
				"I: At last , you reveal yourself , Pantheon .",
				"I: Hail Pantheon! How noble and proud he carries himself!",
				"I: Let me hear you cry of friendship and brotherhood!",
				"I: Come betrayer! Come fiend!"
		};
		prose = combine(prose, data.describeMonsters());
		return prose;
	};
	private static String[] chapter9() {
		String[] prose = {
		"Chapter 9",
		"Unable to keep up with you and your tremendous power",
		"Illidan seeks to escape the battle but it is to no avail",
		"as you strike at his achilles. He lets out a cry for mercy",
		"Pitiful and irritating as it is you ponder:"

		};
		//prose = combine(prose, data.describeMonsters());
		return prose;
	};
	// Two simple and different ways the game can end.
	// "--------------------------------------------------------------"
	private static String[] end() {
		String[] ending = null;
		if (data.isPlayerDead()) 
			ending = new String[] { // you are expected to write more...
				"--End--",
				"I: on the other side.....they will greet you as heroes",
				"Your player has died...",
				"Get rekt noob :D"
			};
		else if(mychoice == 1) {

			ending = new String[]{ // you are expected to write more...

					"--End--",
					"Death is the only mercy you will recieve",
					"even though it is too good for you",
					"You utter a prayer to summon your most powerful atttack",
					" Unlimited Blade Works!",
					"I am the bone of my sword",
					"Steel is my body and fire is my blood",
					"I have created over a thousand blades",
					"Unknown to Death,",
					"Nor known to Life.",
					"Have withstood pain to create many weapons",
					"Yet, those hands will never hold anything",
					"So as I pray, Unlimited Blade Works.",
					"In the blink of an eye countless blades hail ",
					"from the heavens themselves",
					"The demon is torn asunder till naught remains of him",
					"The battle, the war, the conflict is over",
					"You plung your blade in the earth and shout of victory ",
					"along side Sylvanas, V'halmeara and the rest of the",
					"Valoranian army" ,
					"I Hope You enjoyed! Thank you for playing !"
			};
		}
		else if(mychoice == 0){
			System.out.println("my choice = 0");
			ending = new String[] {
			"--End--",
			"You Turn from the foe in benevolence...",
			"Sentimentality was always your biggest weakness old",
			"friend.",
			"Illidan take this oppurtunity to attack once again, ",
			"Without hesitation you end his misery with a single",
			"blow.....",
			"You plung your blade in the earth and shout of victory ",
			"along side Sylvanas, V'halmeara and the rest of the",
			"Valoranian army",
					"I Hope You enjoyed! Thank you for playing !"
			};
		}
		return ending;
	}
	
	/**
	 * Checks if the Narrator is ready to end its story.
	 * This helps Game know what to do next. Perhaps you
	 * could check similarly for other chapters?
	 *
	 * @return
	 *   Returns <code>true</code> if the Narrator is currently about 
	 *   to tell the final chapter in the story, and <code>false</code> otherwise.
	 */
	public static boolean isAtFinalChapter() {
		return chapter == FINAL_CHAPTER;
	}
	
	// A helper method to sequence together two string arrays into one array.
	private static String[] combine(String[] first, String[] second) {
		// Copy the first, and make enough extra space for the second.
		String[] combined = Arrays.copyOf(first, first.length + second.length);
		// Copy the second into the extra space.
		System.arraycopy(second, 0, combined, first.length, second.length);
		return combined;
	}
	
	/*
	 * Maybe you could design your own line-breaking method to create arrays of Strings
	 * in a much more efficient way than I have done above? Pass in a String, choose how
	 * to partition it, and return an array of Strings. It may help you design a story
	 * in your code much faster instead of writing chapters of it inside String arrays
	 * like I did above.
	 */
}