package com.example.computer.ctw_4_24_16;

/**
 * Created by Computer on 4/17/2016.
 */
public class Utils_ctw_awaheed {

    //------------------------------- Remember ------------------------------
    //1. Create statics in a level class that any other file can access
    //      -Specifically for having drag and drop features work properly
    //2. Had to leave decision part so multi-threading could be avoided
    //3. Need static counter for # of words left
    //
    //4. Bug with when words go into status bar
    //
    //5. replace create button init with the one created for 4_18_16 project with offered clusters
    //
    //6. change to api 21 because of MutableInt

    public static final int TOTAL_WORKING_LEVELS = 6;

    public static final String GOOD_WORDS_BY_NEWLINE = "benign\n" +
        "philanthropic\n" +
        "altruistic\n" +
        "beneficent\n" +
        "humanitarian\n" +
        "kindhearted\n" +
        "magnanimous\n" +
        "considerate\n" +
        "kind\n" +
        "amiable\n" +
        "noble\n" +
        "benevolent\n" +
        "helpful\n" +
        "charitable\n" +
        "friendly";

    public static final String EVIL_WORDS_BY_NEWLINE = "malevolent\n" +
        "baleful\n" +
        "hostile\n" +
        "pernicious\n" +
        "vindictive\n" +
        "harmful\n" +
        "threatening\n" +
        "venomous\n" +
        "malicious\n" +
        "malignant\n" +
        "murderous\n" +
        "sinister\n" +
        "vengeful\n" +
        "vicious\n" +
        "wicked";

    public static final String DUMMY_BY_NEWLINE = "dummy1\n" +
            "dummy2\n" +
            "dummy3\n" +
            "dummy4\n" +
            "dummy5\n" +
            "dummy6\n" +
            "dummy7\n" +
            "dummy8\n" +
            "dummy9\n" +
            "dummy10\n" +
            "dummy11\n" +
            "dummy12\n" +
            "dummy13\n" +
            "dummy14\n" +
            "dummy15\n" +
            "dummy16\n" +
            "dummy17\n" +
            "dummy18\n" +
            "dummy19";

    public static final String id_and_word_good_evil = "0\tbenign\n" +
            "1\tphilanthropic\n" +
            "2\taltruistic\n" +
            "3\tbeneficent\n" +
            "4\thumanitarian\n" +
            "5\tkindhearted\n" +
            "6\tmagnanimous\n" +
            "7\tconsiderate\n" +
            "8\tkind\n" +
            "9\tamiable\n" +
            "10\tnoble\n" +
            "11\tbenevolent\n" +
            "12\thelpful\n" +
            "13\tcharitable\n" +
            "14\tfriendly\n" +
            "15\tmalevolent\n" +
            "16\tbaleful\n" +
            "17\thostile\n" +
            "18\tpernicious\n" +
            "19\tvindictive\n" +
            "20\tharmful\n" +
            "21\tthreatening\n" +
            "22\tvenomous\n" +
            "23\tmalicious\n" +
            "24\tmalignant\n" +
            "25\tmurderous\n" +
            "26\tsinister\n" +
            "27\tvengeful\n" +
            "28\tvicious\n" +
            "29\twicked";

    public static final String OLD_WORDS_BY_NEWLINE = "mature\n"+
            "aged\n"+
            "ancient\n"+
            "decrepit\n"+
            "elderly\n"+
            "senior\n"+
            "veteran\n"+
            "experienced\n"+
            "hoary\n"+
            "past\n"+
            "antiquated\n"+
            "archaic\n"+
            "obsolete\n"+
            "antediluvian";

    public static final String NEW_WORDS_BY_NEWLINE = "contemporary\n"+
            "young\n"+
            "youthful\n"+
            "green\n"+
            "modern\n"+
            "inexperienced\n"+
            "recent\n"+
            "current\n"+
            "fresh\n"+
            "up-to-date\n"+
            "novel\n"+
            "hip\n"+
            "innovative";

    public static final String id_and_word_old_new = "30\tmature\n"+
            "31\taged\n"+
            "32\tancient\n"+
            "33\tdecrepit\n"+
            "34\telderly\n"+
            "35\tsenior\n"+
            "36\tveteran\n"+
            "37\texperienced\n"+
            "38\thoary\n"+
            "39\tpast\n"+
            "40\tantiquated\n"+
            "41\tarchaic\n"+
            "42\tobsolete\n"+
            "43\tantediluvian\n"+
            "44\tcontemporary\n"+
            "45\tyoung\n"+
            "46\tyouthful\n"+
            "47\tgreen\n"+
            "48\tmodern\n"+
            "49\trecent\n"+
            "50\tcurrent\n"+
            "51\tfresh\n"+
            "52\tup-to-date\n"+
            "53\tinexperienced\n"+
            "54\tnovel\n"+
            "55\thip\n"+
            "56\tinnovative";

    public static final String HIGH_MIGHTY_WORDS_BY_NEWLINE = "arrogant\n" +
            "braggart\n" +
            "complacent\n" +
            "contemptuous\n" +
            "disdainful\n" +
            "egotistical\n" +
            "haughty\n" +
            "insolent\n" +
            "narcissist\n" +
            "ostentatious\n" +
            "presumptuous\n" +
            "pretentious\n" +
            "supercilious";

    public static final String HUMBLE_SERVING_WORDS_BY_NEWLINE = "compliant\n" +
            "fawning\n" +
            "obsequious\n" +
            "servile\n" +
            "slavish\n" +
            "submissive\n" +
            "subordinate\n" +
            "subservient\n" +
            "sycophant\n" +
            "toady";

    public static final String id_and_word_high_humble = "57\tarrogant\n" +
            "58\tbraggart\n" +
            "59\tcomplacent\n" +
            "60\tcontemptuous\n" +
            "61\tdisdainful\n" +
            "62\tegotistical\n" +
            "63\thaughty\n" +
            "64\tinsolent\n" +
            "65\tnarcissist\n" +
            "66\tostentatious\n" +
            "67\tpresumptuous\n" +
            "68\tpretentious\n" +
            "69\tsupercilious\n" +
            "70\tcompliant\n" +
            "71\tfawning\n" +
            "72\tobsequious\n" +
            "73\tservile\n" +
            "74\tslavish\n" +
            "75\tsubmissive\n" +
            "76\tsubordinate\n" +
            "77\tsubservient\n" +
            "78\tsycophant\n" +
            "79\ttoady";


    public static final String FIGHTING_WORDS_BY_NEWLINE = "animosity\n" +
            "antagonism\n" +
            "bellicose\n" +
            "belligerent\n" +
            "cantankerous\n" +
            "captious\n" +
            "contentious\n" +
            "disputatious\n" +
            "polemical\n" +
            "predator\n" +
            "pugnacious\n" +
            "aggressive\n";

    public static final String HARMONIUS_WORDS_BY_NEWLINE = "allay\n" +
            "alleviate\n" +
            "ameliorate\n" +
            "appease\n" +
            "assuage\n" +
            "conciliate\n" +
            "mediate\n" +
            "mitigate\n" +
            "mollify\n" +
            "pacify\n" +
            "placate\n" +
            "quell";

    public static final String id_and_word_fighting_harmonius = "80\tanimosity\n" +
            "81\tantagonism\n" +
            "82\tbellicose\n" +
            "83\tbelligerent\n" +
            "84\tcantankerous\n" +
            "85\tcaptious\n" +
            "86\tcontentious\n" +
            "87\tdisputatious\n" +
            "88\tpolemical\n" +
            "89\tpredator\n" +
            "90\tpugnacious\n" +
            "91\taggressive\n" +
            "92\tallay\n" +
            "93\talleviate\n" +
            "94\tameliorate\n" +
            "95\tappease\n" +
            "96\tassuage\n" +
            "97\tconciliate\n" +
            "98\tmediate\n" +
            "99\tmitigate\n" +
            "100\tmollify\n" +
            "101\tpacify\n" +
            "102\tplacate\n" +
            "103\tquell";

    public static final String TALK_A_LOT_WORDS_BY_NEWLINE = "circumlocution\n" +
            "colloquial\n" +
            "diffuse\n" +
            "digress\n" +
            "eloquence\n" +
            "garrulous\n" +
            "grandiloquent\n" +
            "loquacious\n" +
            "prattle\n" +
            "rant\n" +
            "rhetorical\n" +
            "verbose\n" +
            "voluble";

    public static final String TALK_A_LITTLE_WORDS_BY_NEWLINE = "brevity\n" +
            "concise\n" +
            "laconic\n" +
            "pithy\n" +
            "quiescent\n" +
            "reticent\n" +
            "succinct\n" +
            "taciturn\n" +
            "terse";

    public static final String id_and_word_talk_a_lot_little = "104\tcircumlocution\n" +
            "105\tcolloquial\n" +
            "106\tdiffuse\n" +
            "107\tdigress\n" +
            "108\teloquence\n" +
            "109\tgarrulous\n" +
            "110\tgrandiloquent\n" +
            "111\tloquacious\n" +
            "112\tprattle\n" +
            "113\trant\n" +
            "114\trhetorical\n" +
            "115\tverbose\n" +
            "116\tvoluble\n" +
            "117\tbrevity\n" +
            "118\tconcise\n" +
            "119\tlaconic\n" +
            "120\tpithy\n" +
            "121\tquiescent\n" +
            "122\treticent\n" +
            "123\tsuccinct\n" +
            "124\ttaciturn\n" +
            "125\tterse";
    public static final String BEGINNING_WORDS_BY_NEWLINE = "antecedent\n" +
            "cardinal\n" +
            "front\n" +
            "fundamental\n" +
            "elementary\n" +
            "first\n" +
            "primary\n" +
            "primitive";

    public static final String MIDDLE_WORDS_BY_NEWLINE = "intermediate\n" +
            "intervening\n" +
            "median\n" +
            "halfway\n" +
            "midpoint\n" +
            "between\n" +
            "midway";

    public static final String END_WORDS_BY_NEWLINE = "limit\n" +
            "terminal\n" +
            "ultimate\n" +
            "tail\n" +
            "terminus\n" +
            "end point\n" +
            "utmost";

    public static final String id_and_word_beginning_middle_end = "126\tantecedent\n" +
            "127\tcardinal\n" +
            "128\tfront\n" +
            "129\tfundamental\n" +
            "130\telementary\n" +
            "131\tfirst\n" +
            "132\tprimary\n" +
            "133\tprimitive\n" +
            "134\tintermediate\n" +
            "135\tintervening\n" +
            "136\tmedian\n" +
            "137\thalfway\n" +
            "138\tmidpoint\n" +
            "139\tbetween\n" +
            "140\tmidway\n" +
            "141\tlimit\n" +
            "142\tterminal\n" +
            "143\tultimate\n" +
            "144\ttail\n" +
            "145\tterminus\n" +
            "146\tend point\n" +
            "147\tutmost";


    public static final String MARTIAL_ARTS_WORDS_BY_NEWLINE = "judo\n" +
            "karate\n" +
            "wrestling\n" +
            "aikido\n" +
            "jujitsu\n" +
            "kendo\n" +
            "kick boxing\n" +
            "kung fu\n" +
            "sumo wrestling\n" +
            "tai chi\n" +
            "tae kwon do\n" +
            "wushu";

    public static final String id_and_word_martial_arts = "148\tjudo\n" +
            "149\tkarate\n" +
            "150\twrestling\n" +
            "151\taikido\n" +
            "152\tjujitsu\n" +
            "153\tkendo\n" +
            "154\tkick boxing\n" +
            "155\tkung fu\n" +
            "156\tsumo wrestling\n" +
            "157\ttai chi\n" +
            "158\ttae kwon do\n" +
            "159\twushu";


}
