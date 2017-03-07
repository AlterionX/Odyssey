package gui;

/**
 * 
 * @author Benjamin Xu
 *
 */
public enum ChatLogData {
	
	/**
	 * 
	 */
	AARON1(1), AARON2(2), AARON3(3), AUR1(4), AUR2(5),
	NOWELL(6), MALCOLM(7), OSCAR1(8), OSCAR2(9), OTHER1(10),
	OTHER2(11), OTHER3(12), PENNY1(13), PENNY2(14), TER1(15), TER2(16);
	
	/**
	 * 
	 */
	private final String[] CONVO;
	
	/**
	 * 
	 */
	private final String[][] CONVOSDATA = {{"Terence and Aaron \nChat Log 1\n",
        "Aaron\nsup bro\n",
        "Terence\nWhats up\n",
        "Aaron\nHows ur dad lol\n",
        "Terence\nCome on man, that's not funny.\n",
        "Aaron\nI kid I kid. But srsly, me and my buddies are gunna "
            + "come ovr to ur place today? liek 30 ppl or so\n",
        "Terence\nNah man, I can't\n",
        "Aaron\ncome on, itll be great\n",
        "we gonna party all night, no ‘rents to get all butthurt\n",
        "we got nowhere else to go man? i got kicked out yesterday, "
            + "parents being annoying\n",
        "Come on one might?\n",
        "Terence\nugh\n",
        "fine\n",
        "just don't mess up anything\n",
        "Aaron\nu da best brah\n",
        "seeya in a few\n",
        "Terence\nyeah seeya...\n",
        "Aaron is now offline. Messages you send will be delivered when"
            + " Aaron comes online.\n",
        "Terence\nJerk."},
        {"Terence and Aaron \nChat Log 2\n",
        "\n",
        "Aaron\nHey bro wuzzap?\n",
        "Where wer u man I didn't see u at the party\n",
        "Terence\nOh come on, I thought you said you were only staying "
            + "for one day\n",
        "Aaron\nOh did i srry musta been a typo\n",
        "or maybe i was drunk or smthing\n",
        "Terence\nDude youre drunk right now. \n",
        "Anyway its ok. Stay put if you want. I'll just go get Dad\n",
        "and chase you guys out.\n",
        "Aaron\nlol u funny man\n",
        "l8r bro\n",
        "Dont worwaerry we'll keep your place wqe23real safe til awfel "
            + "you get back.as'fd;j'pw ioe ur mom's such a prude Later "
            + "bro awef/f. utthgf,.M\n",
        "seeya whn u gt baecs bro\n",
        "Terence\nOk man.\n",
        "Aaron is now offline. Messages you send will be delivered when"
            + " Aaron comes online.\n",
        "Terence\nOh just you wait. You're gonna be in for it time,"
            + " jerk.\n",
        "You have no idea.\n",
        "But what can I do? I feel so useless, even if I want to help.\n",
        "If Dad were here..."},
        {"Aaron and Terence \nChat Log 3\n",
        "\n",
        "Aaron\nYo what the hell man? Why'd you go off on your stupid "
            + "way to find your dad? You want us in trouble bro? Huh? "
            + "Is that it? You'd better come back or else.\n",
        "\n",
        "Terence\nYou talk about trouble like you're not in for it."
            + " Well, have I got news for you. Oscar is coming back, "
            + "and I would imagine he's quite unhappy with the way you "
            + "trashed our house.\n",
        "\n",
        "Aaron\nYo u are just too funny bro. Your dad's long gone bro, "
            + "no way he's coming back.\n",
        "Probs left your mom, the prude that she is.\n",
        "\n",
        "Terence\nOh really? What if I told you he just talked to me "
            + "right now?\n",
        "\n",
        "Aaron\nNah man, you must be joking. No way.\n",
        "\n",
        "Terence\nok have it your way. Just giving a friendly heads up "
            + "to get the heck out of my house.\n",
        "You are so completely dead when I get back, Aaron. You and "
            + "your friends are in for it so\nmuch more than you "
            + "realize. I'm going to sue the hats off your arrogant "
            + "heads and give them to the poor. They would use it so "
            + "much better than you, you leeches, bullies, idiots.\n",
        "\n",
        "Terence is now offline. Messages you send will be delivered "
            + "when Terence comes online."},
        {"Family Attorney Aurora to Terence \nEmail\n",
        "\n",
        "To (terence@gmail.com)\n",
        "It seems you have a problem on your hands, don't you. I've "
            + "worried about this for a while. \n",
        "But don't just sit there hoping your dad would come back."
            + " I know your dad, he is a wise "
            + "\nand cunning man. He would not want you to be idle "
            + "like this. You have a chance to save this situation, "
            + "Terence. You must act soon, or else you will fail in "
            + "saving anything worth saving. Dreaming may be a solution"
            + " to your emotions, but it does not solve anything else "
            + "in life."},
        {"Aurora and Terence \nChat Log\n",
        "How are you, Terence?\n",
        "Terence\nFine, great. I've just learned that I have just lost"
            + " my home, and anything I could have possibly owned.\n",
        "Yeah, I'm great.\n",
        "Aurora\nWell, maybe it's time to do something.\n",
        "Terence\nMy dad will do something."
            + "He has to come home sometime.\n",
        "Aurora\nOh? And how do you know that?\n",
        "\n",
        "Terence is typing...\n",
        "\n",
        "Exactly correct.\n",
        "So now, if you didn't do anything, you will lose everything,"
            + " correct?\n",
        "So what happens now?\n",
        "Terence\nI don't know, honestly. I'm in the doghouse "
            + "right now. No authority, no nothing. "
            + "Can't even control a lousy pack of friends.\n",
        "Aurora\n So what would you like me to do?\n",
        "Terence\nWhat?\n",
        "Aurora\nI am at your disposal for now. Your father feared"
            + " that this would happen one day. He has necessary "
            + "arrangements for my services. I would assume that you "
            + "would like to use me as an assistant?\n",
        "Terence\n...\n",
        "Aurora\nI will take that as a yes. The proper actions you must"
            + " take will begin now.\n",
        "Terence\nWait, who should I contact?\n",
        "Aurora\nContact Nowell. He is the first among your father's "
            + "friends.\n",
        "Terence\nOkay\n",
        "How do I know you will pull through for me.\n",
        "Aurora\nI've been at this job for years, Terence. It is my "
            + "duty to solve problems.\n",
        "Now do you want my help or not? Or do you consider me "
            + "unworthy?\n",
        "Terence\nI'll accept. Anything to help my father.\n",
        "Aurora\nAlright then. Best of luck to you on your endeavors."
            + " I will gather as much information as possible and "
            + "assist you in any way I can.\n",
        "Terence\nSee you"},
        {"Terence, Aurora, and Nowell \nGroup Chat Log\n",
        "This is now a group chat.\n",
        "Aurora has joined the conversation.\n",
        "Terence has joined the conversation.\n",
        "Nowell has joined the conversation.\n",
        "\n",
        "Terence\nMr. Nowell, I hope you're not busy right now.\n",
        "Nowell\nTerence what a pleasant surprise!\n",
        "How's it going?\n",
        "No need for formalities, just call me Uncle Nowell.\n",
        "Terence\nUncle?\n",
        "How do you know me? All I did was find you in my dad's contacts.\n",
        "Nowell\nAh, but that's the point, young'un.\n",
        "Way back when, I was great friends with your father.\n",
        "Man, we had the best times, you can't imagine\n",
        "I was reminded of them when I saw you. But obviously don't know me do you?\n",
        "That's right, you were only just a kid back then. My how you have grown up. \n",
        "Terence\nI'll take you at your word, cause I'm pretty desperate right now.\n",
        "You say you know my father. Where is he now?\n",
        "Nowell\nAh, but that I do not know, youngun. I'm sorry. In all the years I've known him...\n",
        "But I had thought he had gotten back already. Days out in that country don't do anyone "
            + "any sorta good.\n",
        "But why don't ya come visit me? The tales I could tell you about him?\n",
        "Aurora\nJust cut to the chase. We'll talk about visits later.\n",
        "Anyone you know of that was with him before he disappeared?\n",
        "Nowell\nIts you...\n",
        "\n",
        "Nowell is typing...\n",
        "\n",
        "Well boy, if she's on your side you better achieve what you accomplish.\n",
        "\n",
        "Terence\nWhat's so special, she's only our family attorney.\n",
        "Nowell\nOh no, she's the best, I tell you. But anyway, you heard of Malcolm?\n",
        "He was another one of Oscar's colleagues way back when.\n",
        "He's currently living in the city of Palo Alto in California.\n",
        "Terence\nI've heard the name.\n",
        "I'll be on my way there. But tell me, would you mind if a paid a visit to your place? I'd "
            + "like to know more about my dad.\n",
        "Nowell\nYes, anytime. Anything for a family friend. I'll be meeting you at the airport in "
            + "good ole Philly. Good luck boy. I'll catch ya later.\n",
        "\n",
        "Nowell is now offline. Messages you send will be delivered when Nowell comes online.\n",
        "\n",
        "Terence\nPhilly,huh?\n",
        "Aurora\nDon't worry, I can get you there. I have hired a conveyance for you. It should "
            + "be here shortly..."},
        {"Malcolm and Terence \nChat Log\n",
        "\n",
        "Malcolm\nAh Oscar's son, I see. How are you?\n",
        "I am Malcolm. You may have heard of me before.\n",
        "\n",
        "Terence\nI'm trying to make a name for myself now. Oscar is great, but he can't do anything "
            + "\nabout what's going on at home.\n",
        "\n",
        "Malcolm\nOh? I see you've got spirit like your father had.\n",
        "He was the best man for the job at hand. That country was not for the weak at heart, I tell you.",
        "\n",
        "Terence\nHad? So you don't know his whereabouts either.\n",
        "\n",
        "Malcolm\nSadly, no. But you proceed to follow his trail, I see. Why so?\n",
        "\n",
        "Terence\nI'm actually in a bit of a dilemma now. My household has been overrun. "
            + "They think it's ok to just barge in. The government wants the house, and "
            + "my ‘friends' have been trashing it.\n",
        "\n",
        "Malcolm\nSounds like a problem indeed. But tell me, how can I help you?\n",
        "\n",
        "Terence\nWhat would Oscar do in my situation? I'm trying to know more about him, "
            + "so I may know what to do. I need those cretins out of my household now!\n",
        "\n",
        "Malcolm\nYou speak well, young Terence, though you don't know it. You already have the power "
            + "in your hands.You've grown up so much. But come, let us talk. I want to get to "
            + "know you better. Please, come to \nPalo Alto. Where are you, currently?\n",
        "\n",
        "Terence\nI'll be headed over shortly from Philadelphia. Nowell referred me to you, you know.\n",
        "\n",
        "Malcolm\nAh, Nowell. My old friend. Please, tell him that I said hello.\n",
        "I'll see you later, I have pressing matters to attend to.\n",
        "\n",
        "Terence\nThanks."},
        {"Oscar and Terence \nChat Log 1\n",
        "Oscar\nAre you back?\n",
        "Terence\nuh, who's this?\n",
        "Oscar\nIt's your father.\n",
        "Terence\n*gasp* dad \n",
        "\n",
        "Terence is typing:\n",
        "\n",
        "No! This can't be possible! Dad? Is it really you?\n",
        "You can't be, you've been for so long and I\n",
        "Oscar\nI missed you too son.\n",
        "It took a while, but I've finished what I was sent to do.\n",
        "I don't have much time.\n",
        "Things are going to get a little rough\n",
        "But I am in Ithaca, NY right now.\n",
        "Don't tell anyone though.\n",
        "Terence\nwait\n",
        "Oscar is now offline. Messages you send will be delivered when"
            + " Oscar comes online."
            + "Terence\nHow have you been?\n",
        ".... dad ....\n",
        "Oscar\nIt'll take about 2 days. leave them free\n",
        "Oscar is now offline. Messages you send will be delivered when"
            + " Oscar comes online."},
        {"Terence and Oscar \nChat Log 2",
        "\n",
        "Oscar\nGreat job, son.\n",
        "Terence\nThanks, dad.\n",
        "Oscar\nI'm sorry for leaving you alone, but it was for the best.\n",
        "Terence\nOh? How so?\n",
        "Oscar\nIt is such a relief that someone could grow to such"
            + " proportions. I am proud of you.\n",
        "I feared that, when I was away fighting in the war, my "
            + "household would have been overrun with mongrels like "
            + "these.\n",
        "But you handled the situation admirably.\n",
        "Terence\nThanks dad...\n",
        "Oscar\nI've finished my will- there shouldn't be anyone to"
            + " bother you anymore.\n",
        "Terence\nFarewell.\nTerence is now offline. Messages you send"
            + " will be delivered when Terence comes online.\n",
        "Oscar\nSon..."},
        {"The Bank to Terence \nEmail\n",
        "\n",
        "To (terence@gmail.com)\n",
        "Your house will be foreclosed in a month due to the funds of recently deceased persons within"
            + " your household. "
            + "\nExcessive spending has occurred in your account."
            + "\nResearch into your father's origins have led us to believe that they are deceased"
            + " and has not left a will."
            + "The government will now own all property formerly belonging to Oscar in a month."},
        {"Private Jet to Terence \nEmail\n",
        "To: (terence@gmail.com)\n",
        "To Mr. Ithyl,\n",
        "The Learjet you associate has hired will be waiting for you at gate 2, Dock 5A.\n",
        ""},
        {"Edward, Phillip, and Terence \nGroup Chat Log\n",
        "\n",
        "Edward\nMan, I can't believe this!\n",
        "Phillip\nThose partyers have been causing a ruckus all around town!\n",
        "Terence\nDon't worry. You can count on me to get them out. Any enemy of that"
            + "\n group is a friend of mine.\n",
        "Edward\nYo we'll help you as well. What can we do?\n",
        "Terence\nI'll be back in a few hours. Just returning from the airport. I'll tell you later.\n",
        "Edward\nYou seem...different, somehow. More mature.\n",
        "We still buds, right?\n",
        "Terence\nOf course.\n",
        "I guess I decided along the way that\n",
        "Maybe its time to do more for the family, since my dad is still away.\n",
        "I've made a court appeal. You two can help me as witnesses."},
        {"Terence and Penny \nChat Log\n",
        "\n",
        "Terence\nWho is my father, really?\n",
        "Penny\nHoney, we've been over this. He was a great guy.\n",
        "Terence\nThat's all you've ever said.\n",
        "Penny\nLook\n",
        "Terence\nHe was a great guy so what? Where is he now if he's "
            + "so great?\n",
        "Penny\nIt's hard to explain, OK?\n",
        "He was... the man in my life, but now he's gone.\n",
        "I don't know if he will make it back.\n",
        "Terence\nWhy? What happened to him?\n",
        "Penny\nIt's hard. I don't want to talk about it.\n",
        "He was a well-known guy, your father was.\n",
        "Look let's just stop ok? I already get harassed by others "
            + "asking me these things.\n",
        "They just don't get it!\n",
        "Terence\nCome on don't be like that. Its not their fault.\n",
        "They're worried. Don't blame them for this.\n",
        "If anything, it's my fault for not stepping up to the plate. "
            + "I should have done this so long ago.\n",
        "Maybe it's time for me to move on.\n",
        "Penny\n:( Thanks. I feel like I'm the one asking you for help,"
            + " not the other way around.\n",
        "sigh if only Oscar hadn't gone to that...godforsaken place..."
            + " none of this would have happened...\n",
        "Terence\nDon't worry, Mom. I'm sorry I made you sad. Here, "
            + "take a rest.\n",
        "I think I know what I can do..."},
        {"Terence to Penny \nEmail\n",
        "From: (terence@gmail.com)\nTo: (Penny@gmail.com) \n",
        "\n",
        "How's everything going on back at home? Have the partygoers "
            + "gone yet? I will come home and make them leave tonight."
            + " They are not getting anything else from us.\n",
        "Dad would want me to take charge of the family, and I know "
            + "it. It was what he did during the war.\n",
        "Why didn't you tell me earlier?\n",
        "But don't worry, I'm positive he will come back. He will find a way."},
        {"Terence Rants \nEmail Set 1\n",
        "\nTo: (terence@gmail.com), (Oscar@gmail.com),\n\n",
        "Why can't I do this? Why? Screw Aaron and his band of douchebags.\n",
        "One night they said. And now I cant get them out!\n",
        "Dad, help. Please. Just come back. I know I don't know you,"
            + "but you are just the guy I need in my life right now."
            + "Seriously, please. I'm begging you.\n",
        "\n", " ", " ", "",
        "Why is my life so hard!?!?!!1!\n",
        "\n",
        "From: Oscar@gmail.com \nTo: (terence@gmail.com)\n",
        "\n",
        "Email Status: Undeliverable\n",
        "Error 550 Requested action not taken: mailbox unavailable"},
        {"Terence Rants \nEmail Set 2\n",
        "From (terence@gmail.com)\nTo: (terence@gmail.com), (Oscar@gmail.com)\n",
        "\n",
        "\n",
        "What would you do if you were in my shoes?\n",
        "Anyway, I know you won't receive this, but thanks dad.\n",
        "Aurora has helped me understand. I know what you would want me"
            + " to do now.\n",
        "And I myself know what I am doing now. I have this feeling "
            + "you'll be back soon, but...\n",
        "\n",
        "I have a purpose in life now. With Aurora's help I will find "
            + "you.\n",
        "It's time to take my own path.\n",
        "Thanks.\n",
        "\n",
        "From: Oscar@gmail.com\n",
        "To: (terence@gmail.com)\n",
        "Email Status: Undeliverable\n",
        "Error 550 Requested action not taken: mailbox unavailable"}};
	
	/**
	 * 
	 * @param convo
	 */
	private ChatLogData(int convo){
		CONVO = CONVOSDATA[convo - 1];
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getCONVO(){
		return CONVO;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName(){
		return CONVO[0];
	}
	
	/**
	 * 
	 */
	public void print(){
		for(String s:CONVO){
			System.out.println(s);
		}
	}
}
