package com.azumio.android.foodlenslibrary.utils;

import android.os.AsyncTask;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;



public class ArgusIconMap
{
	private static final String LOG_TAG = ArgusIconMap.class.getSimpleName();
	public static final String GIFT = "gift";
	public static final String GOOGLE_FIT = "googe_fit";
	public static final String WEB_INFO = "argus-web-info";
	public static final String ARGUS_COPY = "argus-copy";
	public static final String ARGUS_BODY_METRICS = "argus-body-metrics";
	public static final String ARGUS_WEIGHT = "argus-weight";
	public static final String ARGUS_CLOSE2 = "argus-close2";
	public static final String ARGUS_SELECTED = "argus-selected";
	private static final String ARGUS_DELETE2 = "argus-delete2";
	public static final String GPS_TILE_DURATION_FULL = "argus-gps-duration-full";

	private Semaphore mDeserializationLock = new Semaphore(1, true);

	public static final String GO_PREMIUM = "go_premium";
	public static final String NEWSFEED_ICON_NAME = "newsfeed_chat";
	public static final String FOLLOW_ICON_NAME = "FOLLOW_ICON_NAME";
	public static final String FOLLOWING_ICON_NAME = "FOLLOWING_ICON_NAME";
	public static final String FRIENDS_ICON_NAME = "FRIENDS_ICON_NAME";
	public static final String ADD_FRIEND_ICON_NAME = "ADD_FRIEND_ICON_NAME";
	public static final String ADD_ACTIVITY = "add-activity";
	public static final String CLOSE = "close";
	public static final String COMMENTS = "COMMENTS";
	public static final String HEART = "HEART";
	public static final String HEART_FILLED = "HEART_FILLED";
	public static final String LOCK = "lock";
	public static final String CREATE = "argus-fb-custom";
	public static final String WEIGHT = "weight";
	public static final String A1C = "GB-A1C";
	//TODO fill
	public static final String PLUS = "argus-add-challenge";

	public static final String JUST_WOKE_UP = "just_woke_up";
	public static final String EXERCISING = "Exercising";
	public static final String POST_WORKOUT = "post_workout";
	public static final String TIRED = "Tired";
	public static final String RESTING = "Resting";
	public static final String HOME = "Home";
	public static final String WORK = "Work";
	public static final String TWO_MIN_BEFORE = "two_min_before";
	public static final String TWO_MIN_AFTER = "two_min_after";
	public static final String OTHER = "other";
	public static final String USER_CUSTOM_TAG = "USER_CUSTOM_TAG";

	public static final String GOALS = "goals";

	public static final String CAFFEINE = "caffeine";
	public static final String ATE_LATE = "ate_late";
	public static final String WORKED_OUT = "worked_out";
	public static final String STRESSFUL_DAY = "stressful_day";
	public static final String NOT_MY_BED = "not_my_bed";
	public static final String ALCOHOL = "Alcohol";
	public static final String TOBACCO = "Tobacco";
	public static final String ELECTRONICS_BEFORE_BED = "electronics_before_bed";

	public static final String MOTION_PROCESSOR_ACTIVITY_SETTINGS = "FB-maschine";

	public static final String GPS_TILE_DURATION = "duration";
	public static final String GPS_TILE_DISTANCE = "distance2";
	public static final String GPS_TILE_AVG_PACE = "avg-pace";
	public static final String GPS_TILE_PACE = "pace";
	public static final String GPS_TILE_SPEED = "speed";
	public static final String GPS_TILE_AVG_SPEED = "avg-speed";
	public static final String GPS_TILE_HEART_RATE = "heartrate2";
	public static final String GPS_TILE_AVG_HEART_RATE = "avg-HR";
	public static final String GPS_TILE_CALORIES = "calories";
	public static final String GPS_TILE_ELEVATION = "elevation";
	public static final String GPS_TILE_ELEVATION_GAIN = "elev-gain";
	public static final String GPS_TILE_ELEVATION_LOSS = "elev-loss";
	public static final String GPS_TILE_MAX_SPEED = "max-speed";
	public static final String GPS_TILE_CLOCK = "clock";
	public static final String GPS_TILE_SPLIT_PACE = "split-pace";
	public static final String GPS_TILE_STEPS = "steps";
	public static final String GPS_TILE_CADENCE = "cadence";
	public static final String GPS_TILE_MORE = "arrow-forward";
	public static final String GPS_TILE_BACK = "arrow-back";
	public static final String GPS_TILE_PLACEHOLDER = "GPS_TILE_PLACEHOLDER";

	public static final String RUNNING = "running";
	public static final String DELETE = "delete";
	public static final String HEX_FILLED = "hex";
	public static final String VERIFIED_ICON_NAME = "verified";
	public static final String LAYERS_ICON_NAME = "layers";
	public static final String EDIT = "edit";
	public static final String NEXT = "next";
	public static final String GRAINS = "grains";
	public static final String PROTEIN = "protein";
	public static final String PREVIOUS = "prev";
	public static final String HEALTH_AND_FITNESS = "HEALTH_AND_FITNESS";
	public static final String SHARE_FROM_WEBVIEW = "SHARE_FROM_WEBVIEW";
	public static final String IMPORT = "import";
	public static final String PREMIUM = "premium";

	public static final String FRUITS = "fruits";
	public static final String VEGGIES = "veggies";
	public static final String OILS = "oils";
	public static final String DAIRY = "dairy";
	public static final String SWEETS = "sweets";
	public static final String WINE = "wine";
	public static final String WATER = "water";
	public static final String CHOLESTROL = "cholestrol";
	public static final String SODIUM = "sodium";
	public static final String WORKOUT = "workout";
	public static final String HEART_RATE = "heart-rate";
	public static final String HEART_ICON = "premium-HRreport";
	public static final String HEART_ICON2 = "heartrate2";
	public static final String HEART_RATE_REPORT = "hearrate-report";

	public static final String GROUPS = "argus-groups-on";
	public static final String CHALLENGES = "argus-nav-challenges-off-2";

	public static final String KNOWLEDGE_BASE = "knowledge_base";
	public static final String TECHNICAL_SUGGESTION = "technical_suggestion";
	public static final String REPORT_BUG = "report_bug";
	public static final String CANCEL = "cancel";
	public static final String ST_NAV_CLOCK = "st_nav_clock";
	public static final String INSIGHT = "insight";
	public static final String SOUNDSCAPE = "soundscape";
	public static final String ARGUS_ICON_WP = "argus_wp_icon";
	public static final String SETTINGS_ICON = "settings_icon";
	public static final String SELECTED = "argus_selected";
	public static final String DOWNLOAD = "download";
	public static final String ARGUS_BOY = "argus_boy";
	public static final String SLEEP_INSIGHT = "sleep_insight";
	public static final String SLEEP_ICON = "sleep_icon";
	public static final String ARGUS_SELECTED2 = "argus-selected2";
	public static final String ARGUS_GIRL = "argus-fb-girl";
	public static final String MAGNIFY_GLASS = "magnifying-glass";
	public static final String COPY = "copy";
	public static final String SHARE_MORE = "argus-share-more";
	public static final String PHOTO_GALLERY = "argus-photo-gallery";
	public static final String MEAL_ICON = "mealicon";
	public static final String BODY_WEIGHT = "weight";
	public static final String ARGUS_BACK_ARROW = "argus-back-arrow";
	public static final String MEASURE_ICON = "measureicon";
	public static final String INSIGHTS_ICON = "insightsicon";
	public static final String PLANS_ICON = "plansicon";
	public static final String MEAL_PLANS = "mealplans";
	public static final String AZUMIO_ICON = "azumio_icon";
	public static final String ARGUS_LOGO = "argus_logo";
	public static final String GLUCOSE = "argus-GB-glucose2";
	public static final String MEDS = "argus-GB-medication";
	public static final String BLOOD_PRESSURE = "GB-bloodpressure";
	public static final String NOTIFICATIONS_ICON = "argus-nav-notifications-off-2";
	public static final String MAIN_NAV = "argus-main-nav";
	public static final String FOOD = "GB-food";
	public static final String ARGUS_CUSTOM = "argus-custom";
	public static final String ARGUS_PAYMENT_PROCESSING = "argus-payment-processing";
	public static final String ARGUS_RUNNING_HISTORY = "argus-running-history";
	public static final String CARBS = "argus-GB-carbs";
	public static final String HEART_RATE_PULSE = "argus-heart-rate";
	public static final String CALENDAR = "argus-calendar";
	public static final String CUSTOM_USER_TAG = "argus-L-tags";
	public static final String PROBES = "probes";
	public static final String OUT_OF_BED = "out_of_bed";
	public static final String BEFORE_BREAKFAST = "before_breakfast";
	public static final String AFTER_BREAKFAST = "after_breakfast";
	public static final String BEFORE_LUNCH = "before_lunch";
	public static final String AFTER_LUNCH = "after_lunch";
	public static final String BEFORE_DINNER = "before_dinner";
	public static final String BEFORE_SNACK = "before_snack";
	public static final String AFTER_SNACK = "after_snack";
	public static final String BEFORE_ACTIVITY = "before_activity";
	public static final String AFTER_ACTIVITY = "after_activity";
	public static final String AFTER_DINNER = "after_dinner";
	public static final String BEFORE_BED = "before_bed";
	public static final String AFTER_BED = "after_bed";
	public static final String DURING_ACTIVITY = "during_activity";
	public static final String ACTIVITIES = "activities";
	public static final String ARGUS_EXPAND = "argus_expand";

	public static final String LC_FRIENDS_ON = "argus-LC-friends-on";
	public static final String LC_FRIENDS_OFF = "argus-LC-friends-off";
	public static final String STORE_ON = "argus-LC-store-on";
	public static final String STORE_OFF = "argus-LC-store-off";
	public static final String STORE2_ON = "argus-LC-store2-on";
	public static final String STORE2_OFF = "argus-LC-store2-off";
	public static final String CONVERSION_ON = "argus-LC-conversion-on";
	public static final String CONVERSION_OFF = "argus-LC-conversion-off";
	public static final String STEPS_ON = "argus-LC-steps-on";
	public static final String STEPS_OFF = "argus-LC-steps-off";
	public static final String STEPS2_ON = "argus-LC-steps2-on";
	public static final String STEPS2_OFF = "argus-LC-steps2-off";
	public static final String LC_ICON_LOGO = "argus-LC-logo";
	public static final String ADD_FRIENDS = "argus-add-friends";
	public static final String LC_ICON_SAPLING = "argus-sapling";
	public static final String LC_SEND = "argus-LC-send";
	public static final String LC_ARGUS_SEED = "argus-seed";
	public static final String LC_ICON_SPROUT = "argus-sprout";
	public static final String LC_ICON_TREE = "argus-tree";
	public static final String LC_SETTINGS = "argus-nav-more-2";
	public static final String ARGUS_REPORT = "argus-report";
	public static final String ARGUS_MORE_VERT = "more-vert";
	public static final String ARGUS_NOTE = "note";
	public static final String ARGUS_LOSE_WEIGHT = "lose-weight";
	public static final String ARGUS_GAIN_MUSCLE = "gain-muscle";
	public static final String ARGUS_GET_ACTIVE = "get-active";
	public static final String GLUCOSE_CGM = "argus-continuous-bg";
	public static final String PROTEIN_SUPPLIMENT = "dinner";
	public static final String EXERCISE_GRAPH = "exercise-graphs";
	public static final String ARGUS_BARCODE = "argus_barcode";
	public static final String ARGUS_PLUS = "argus_plus";
	public static final String ARGUS_CAMERA = "argus_camera";
	public static final String ARGUS_WEIGHT2 = "argus_weight";
	public static final String BLOOD_SUGAR = "blood-sugar";
	public static final String ARGUS_KETONES = "argus-ketones";
	public static final String ARGUS_FB_TAB_MORE = "argus-fb-tab-more";

	public static final String INSIGHT_OFF = "insight_off";
	public static final String INSIGHT_ON = "insight_on";
	public static final String MEAL_PLAN_OFF = "meal_plan_off";
	public static final String MEAL_PLAN_ON = "meal_plan_on";
	public static final String PLANS_OFF = "plans_off";
	public static final String PLANS_ON = "plans_on";
	public static final String NAV_MORE_OFF = "nav_more_off";
	public static final String NAV_MORE_ON = "nav_more_on";

	public static final String ARGUS_EXERCISE_II_OFF = "argus-exercise-II-OFF";
	public static final String IHR_EXERCISE = "ihr_exercise";

	public static final String FB_favOFF = "FB-favOFF";
	public static final String ARGUS_FB_TAB_PLANS_ON = "argus-FB-tab-plans-ON";
	public static final String ARGUS_FB_TAB_PLANS_OFF = "argus-FB-tab-plans-OFF";

	public static final String SMART_FOOD_1 = "smart-food-1";
	public static final String SMART_FOOD_2 = "smart-food-2";
	public static final String SMART_FOOD_3 = "smart-food-3";
	public static final String CANCEL_ICON = "web-deselect-cover-photo";
	public static final String ARROW_LEFT = "arrow_left";
	private static final ArgusIconMap instance = new ArgusIconMap();


	public static ArgusIconMap getInstance()
	{
		return instance;
	}

	private Map<String, String> icons = new TreeMap<>(String.CASE_INSENSITIVE_ORDER); //new HashMap<>();

	private ArgusIconMap()
	{
		//From iOS code

		icons.put(ARGUS_MORE_VERT, "\ue694");
		icons.put("hex", "\ue693");
		icons.put("delete2", "\ue690");
		icons.put(IMPORT, "\ue691");
		icons.put("sonicast", "\ue692");
		icons.put(MAGNIFY_GLASS, "\ue68e");
		icons.put("chart", "\ue68f");
		icons.put(DELETE, "\ue68d");
		icons.put("compare", "\ue68b");
		icons.put("filter", "\ue68c");
		icons.put("fine", "\ue688");
		icons.put("good", "\ue689");
		icons.put("sad", "\ue68a");
		icons.put(NEXT, "\ue686");
		icons.put(PREVIOUS, "\ue687");
		icons.put("audio-cue-off", "\ue680");
		icons.put("audio-cue-on", "\ue681");
		icons.put("gps", "\ue682");
		icons.put("pause", "\ue683");
		icons.put("pebble", "\ue684");
		icons.put("play", "\ue685");
		icons.put("lock", "\ue67f");
		icons.put("cadence", "\ue67e");
		icons.put("about", "\ue60a");
		icons.put("devices-apps", "\ue63c");
		icons.put("FB-bymuscle", "\ue63e");
		icons.put("FB-core", "\ue644");
		icons.put("FB-lowerbody", "\ue65c");
		icons.put("FB-smith", "\ue65d");
		icons.put(HEART_ICON, "\ue65e");
		icons.put("breakfast", "\ue67a");
		icons.put("dinner", "\ue67b");
		icons.put("lunch", "\ue67c");
		icons.put("snack", "\ue67d");
		icons.put("following-web", "\ue679");
		icons.put("avg-HR", "\ue66d");
		icons.put("avg-pace", "\ue66e");
		icons.put("avg-speed", "\ue66f");
		icons.put("distance2", "\ue670");
		icons.put("duration", "\ue671");
		icons.put("elev-gain", "\ue672");
		icons.put("elev-loss", "\ue673");
		icons.put("elevation", "\ue674");
		icons.put("max-speed", "\ue675");
		icons.put("pace", "\ue676");
		icons.put("speed", "\ue677");
		icons.put("split-pace", "\ue678");
		icons.put("more", "\ue66b");
		icons.put("share", "\ue66c");
		icons.put("channels", "\ue666");
		icons.put("distance", "\ue667");
		icons.put("location", "\ue668");
		icons.put("start-time", "\ue669");
		icons.put("tag-friends", "\ue66a");
		icons.put("FB-favOFF", "\ue660");
		icons.put("FB-favON", "\ue665");
		icons.put("FB-backpack", "\ue64d");
		icons.put("FB-bench", "\ue64e");
		icons.put("FB-bosuball", "\ue64f");
		icons.put("FB-cable", "\ue650");
		icons.put("FB-captainschair", "\ue651");
		icons.put("FB-chair", "\ue652");
		icons.put("FB-dipbar", "\ue653");
		icons.put("FB-ezcurlbar", "\ue654");
		icons.put("FB-hyperextension", "\ue655");
		icons.put("FB-plateloaded", "\ue656");
		icons.put("FB-platform", "\ue657");
		icons.put("FB-preacher", "\ue658");
		icons.put("FB-pullupbar", "\ue659");
		icons.put("FB-sandbag", "\ue65a");
		icons.put("FB-selectorized", "\ue65b");
		icons.put("FB-stairclimber", "\ue661");
		icons.put("FB-table", "\ue662");
		icons.put("FB-towel", "\ue663");
		icons.put("FB-waterbottle", "\ue664");
		icons.put("FB-arms", "\ue638");
		icons.put("FB-back", "\ue639");
		icons.put("FB-barbell", "\ue63a");
		icons.put("FB-bodyweight", "\ue63b");
		icons.put("FB-chest", "\ue63d");
		icons.put("FB-finish", "\ue63f");
		icons.put("FB-foamroller", "\ue640");
		icons.put("FB-hot", "\ue641");
		icons.put("FB-kettlebell", "\ue642");
		icons.put("FB-list", "\ue643");
		icons.put("FB-maschine", "\ue645");
		icons.put("FB-medicineball", "\ue646");
		icons.put("FB-plate", "\ue647");
		icons.put("FB-resistancebend", "\ue648");
		icons.put("FB-shoulders", "\ue649");
		icons.put("FB-stabilityball", "\ue64a");
		icons.put("FB-trending", "\ue64b");
		icons.put("L-tag", "\ue64c");
		icons.put(WORKOUT, "\ue635");
		icons.put("challenges", "\ue626");
		icons.put("premium-hex", "\ue636");
		icons.put(PREMIUM, "\ue637");
		icons.put("import-to-FB", "\ue633");
		icons.put("share-web", "\ue634");
		icons.put("HR-2min-after-workout", "\ue62a");
		icons.put("HR-2min-before-workout", "\ue62b");
		icons.put("HR-before-bed", "\ue62c");
		icons.put("HR-cutom-tag", "\ue62d");
		icons.put("HR-home", "\ue62e");
		icons.put("HR-just-woke-up", "\ue62f");
		icons.put("HR-post-workout", "\ue630");
		icons.put("HR-resting", "\ue631");
		icons.put("HR-work", "\ue632");
		icons.put("ST-bed", "\ue625");
		icons.put("ST-electronics", "\ue627");
		icons.put("ST-stress", "\ue628");
		icons.put("ST-tobacco", "\ue629");
		icons.put("camera", "\ue624");
		icons.put("L-addfriend", "\ue623");
		icons.put("L-follow", "\ue620");
		icons.put("L-following", "\ue621");
		icons.put("L-friend", "\ue622");
		icons.put("L-comments-active", "\ue61a");
		icons.put("L-comments", "\ue61b");
		icons.put("L-like-active", "\ue61c");
		icons.put("L-like", "\ue61d");
		icons.put("L-tags", "\ue61e");
		icons.put("L-writecomment", "\ue61f");
		icons.put(A1C, "\ue613");
		icons.put("GB-bloodpressure", "\ue614");
		icons.put("GB-food", "\ue615");
		icons.put("GB-glucose", "\ue616");
		icons.put("GB-meds", "\ue617");
		icons.put("GB-weight", "\ue618");
		icons.put("GB-workout", "\ue619");
		icons.put("fruits", "\ue611");
		icons.put("sweets", "\ue612");
		icons.put("body-fat", "\ue602");
		icons.put("dragon-boating", "\ue603");
		icons.put("housekeeping", "\ue604");
		icons.put("jumping-rope", "\ue605");
		icons.put("kiting", "\ue606");
		icons.put("lacrosse", "\ue607");
		icons.put("martial-arts", "\ue608");
		icons.put("pullups", "\ue609");
		icons.put("table-tennis", "\ue65f");
		icons.put("waterpolo", "\ue60b");
		icons.put("climbing", "\ue60c");
		icons.put("default", "\ue60d");
		icons.put("dive", "\ue60e");
		icons.put("hockey", "\ue60f");
		icons.put("horseback-riding", "\ue610");
		icons.put("thought", "\ue600");
		icons.put("newsfeed", "\ue601");
		icons.put("add-activity", "\ue038");
		icons.put("close", "\ue042");
		icons.put(ARGUS_CLOSE2, "\ue94e");
		icons.put("nav-hex", "\ue043");
		icons.put("trends", "\ue044");
		icons.put("goals", "\ue045");
		icons.put("friends", "\ue046");
		icons.put("food", "\ue047");
		icons.put("settings", "\ue048");
		icons.put("highlights", "\ue04a");
		icons.put("smile", "\ue04c");
		icons.put("exit-fullscreen", "\ue04d");
		icons.put(WATER, "\ue04e");
		icons.put("alco", "\ue04f");
		icons.put("wine", "\ue050");
		icons.put("beer", "\ue051");
		icons.put("soda", "\ue052");
		icons.put("tea", "\ue053");
		icons.put("coffee", "\ue054");
		icons.put(HEART_RATE, "\ue055");
		icons.put("treadmill", "\ue057");
		icons.put("body-temp", "\ue059");
		icons.put(BLOOD_SUGAR, "\ue05a");
		icons.put("aerobics", "\ue05b");
		icons.put("boxing", "\ue05c");
		icons.put("badminton", "\ue05d");
		icons.put("tennis", "\ue05e");
		icons.put("golf", "\ue05f");
		icons.put("baseball", "\ue060");
		icons.put("basketball", "\ue061");
		icons.put("soccer-football", "\ue062");
		icons.put("volleyball", "\ue063");
		icons.put("elliptical", "\ue064");
		icons.put("rowing", "\ue065");
		icons.put("motor", "\ue066");
		icons.put("skiing", "\ue067");
		icons.put("commute", "\ue068");
		icons.put("steps-hiking", "\ue069");
		icons.put("skate", "\ue06a");
		icons.put("surfing", "\ue06b");
		icons.put("paddle", "\ue06c");
		icons.put("yoga", "\ue06d");
		icons.put("pilates", "\ue06e");
		icons.put("zumba", "\ue06f");
		icons.put("seimming", "\ue070");
		icons.put("running", "\ue071");
		icons.put("cycling-mountainbiking", "\ue072");
		icons.put("dance", "\ue073");
		icons.put("weight", "\ue074");
		icons.put(GRAINS, "\ue075");
		icons.put(HEART_ICON2, "\ue65e");
		icons.put("oils", "\ue076");
		icons.put("dairy", "\ue077");
		icons.put(PROTEIN, "\ue078");
		icons.put("veggies", "\ue079");
		icons.put("calories", "\ue07a");
		icons.put("rain", "\ue07b");
		icons.put("storm", "\ue07c");
		icons.put("cloudy", "\ue07d");
		icons.put("snow", "\ue07e");
		icons.put("sun", "\ue07f");
		icons.put("main-nav", "\ue080");
		icons.put("fog", "\ue0045");
		icons.put("sleet", "\ue0046");
		icons.put("arrow-back", "\ue687");
		icons.put("arrow-forward", "\ue686");
		icons.put(GO_PREMIUM, "\ue69b");

		icons.put(EDIT, "\ue696");

		//Added
		icons.put(HEX_FILLED, "\ue693");
		icons.put(NEWSFEED_ICON_NAME, "\ue61f");
		icons.put(FOLLOW_ICON_NAME, "\ue620");
		icons.put(FOLLOWING_ICON_NAME, "\ue621");
		icons.put(FRIENDS_ICON_NAME, "\ue622");
		icons.put(ADD_FRIEND_ICON_NAME, "\ue623");
		icons.put(COMMENTS, "\ue61b");
		icons.put(HEART, "\ue61d");
		icons.put(HEALTH_AND_FITNESS, "\ue6ae");
		icons.put(SHARE_FROM_WEBVIEW, "\ue66c");

		//Tags global
		icons.put(USER_CUSTOM_TAG, "\ue64c");
		icons.put(OTHER, "\ue62d");

		//Heart rate tags
		icons.put(HEART_FILLED, "\ue61c");
		icons.put(JUST_WOKE_UP, "\ue62f");
		icons.put(BEFORE_BED, "\ue62c");
		icons.put(EXERCISING, "\ue619");
		icons.put(POST_WORKOUT, "\ue630");
		icons.put(TIRED, "\ue628");
		icons.put(RESTING, "\ue631");
		icons.put(HOME, "\ue62e");
		icons.put(WORK, "\ue632");
		icons.put(TWO_MIN_BEFORE, "\ue62b");
		icons.put(TWO_MIN_AFTER, "\ue62a");

		//Sleep tags
		icons.put(NOT_MY_BED, "\ue625");
		icons.put(TOBACCO, "\ue629");
		icons.put(ELECTRONICS_BEFORE_BED, "\ue627");
		icons.put(CAFFEINE, "\ue054");
		icons.put(ATE_LATE, "\ue047");
		icons.put(STRESSFUL_DAY, "\ue628");
		icons.put(WORKED_OUT, "\ue619");
		icons.put(ALCOHOL, "\ue050");

		//missing gps tiles
		icons.put(GPS_TILE_STEPS, "\ue069");
		icons.put(VERIFIED_ICON_NAME, "\ue698");
		icons.put(LAYERS_ICON_NAME, "\ue697");

		icons.put(CHOLESTROL, "\ue922");
		icons.put(SODIUM, "\ue923");
		icons.put(HEART_RATE_REPORT, "\ue925");

		icons.put(GROUPS, "\ue902");
		icons.put(CHALLENGES, "\ue6a3");
		icons.put(CREATE, "\ue800");
		icons.put(PLUS, "\ue93b");
		icons.put(KNOWLEDGE_BASE, "\ue6b4");
		icons.put(TECHNICAL_SUGGESTION, "\ue04a");
		icons.put(REPORT_BUG, "\ue60a");
		icons.put(CANCEL, "\ue042");
		icons.put(ST_NAV_CLOCK, "\ue6b2");
		icons.put(INSIGHT, "\ue69f");
		icons.put(SOUNDSCAPE, "\ue6b3");
		icons.put(ARGUS_ICON_WP, "\ue91d");
		icons.put(SETTINGS_ICON, "\ue69a");
		icons.put(SELECTED, "\ue93a");
		icons.put(DOWNLOAD, "\ue942");
		icons.put(ARGUS_BOY, "\ue943");
		icons.put(SLEEP_INSIGHT, "\ue90a");
		icons.put(SLEEP_ICON, "\ue945");


		icons.put(ARGUS_SELECTED, "\ue93a");
		icons.put(ARGUS_SELECTED2, "\ue947");
		icons.put(ARGUS_GIRL, "\ue944");
		icons.put(ARGUS_COPY, "\ue94f");
		icons.put(SHARE_MORE, "\ue935");
		icons.put(PHOTO_GALLERY, "\ue965");
		icons.put(MEAL_ICON, "\ue954");
		icons.put(BODY_WEIGHT, "\ue074");
		icons.put(ARGUS_BACK_ARROW, "\ue966");
		icons.put(GIFT, "\ue957");
		icons.put(MEASURE_ICON, "\ue055");
		icons.put(INSIGHTS_ICON, "\ue69f");
		icons.put(PLANS_ICON, "\ue91b");
		icons.put(MEAL_PLANS, "\ue954");
		icons.put(AZUMIO_ICON, "\ue931");
		icons.put(GOOGLE_FIT, "\ue69d");
		icons.put(ARGUS_LOGO, "\ue803");
		icons.put(GLUCOSE, "\ue96a");
		icons.put(MEDS, "\ue96b");
		icons.put(NOTIFICATIONS_ICON, "\ue6ab");
		icons.put(MAIN_NAV, "\ue080");
		icons.put(ARGUS_CUSTOM, "\ue95a");
		icons.put(ARGUS_PAYMENT_PROCESSING, "\ue961");
		icons.put(ARGUS_RUNNING_HISTORY, "\ue955");
		icons.put(CARBS, "\ue969");
		icons.put(HEART_RATE_PULSE, "\ue055");
		icons.put(CALENDAR, "\ue69c");
		icons.put(CUSTOM_USER_TAG, "\ue61e");
		icons.put(PROBES, "\ue95F");
		icons.put(OUT_OF_BED, "\ue914");
		icons.put(BEFORE_BREAKFAST, "\ue926");
		icons.put(AFTER_BREAKFAST, "\ue92c");
		icons.put(BEFORE_LUNCH, "\ue928");
		icons.put(AFTER_LUNCH, "\ue92a");
		icons.put(BEFORE_DINNER, "\ue927");
		icons.put(BEFORE_SNACK, "\ue67d");
		icons.put(AFTER_SNACK, "\ue67d");
		icons.put(BEFORE_ACTIVITY, "\ue929");
		icons.put(AFTER_ACTIVITY, "\ue92d");
		icons.put(AFTER_DINNER, "\ue92b");
		icons.put(BEFORE_BED, "\ue62c");
		icons.put(AFTER_BED, "\ue07f");
		icons.put(DURING_ACTIVITY, "\ue911");
		icons.put(ARGUS_BODY_METRICS, "\ue695");
		icons.put(ARGUS_WEIGHT, "\ue074");
		icons.put(ACTIVITIES, "\ue071");
		icons.put(ARGUS_EXPAND, "\ue974");
		icons.put(STORE_ON, "\ue973");
		icons.put(STORE_OFF, "\ue972");
		icons.put(CONVERSION_ON, "\ue96e");
		icons.put(CONVERSION_OFF, "\ue96d");
		icons.put(STEPS_ON, "\ue971");
		icons.put(STEPS_OFF, "\ue970");
		icons.put(LC_ICON_LOGO, "\ue96f");
		icons.put(ADD_FRIENDS, "\ue979");
		icons.put(LC_ICON_SAPLING, "\ue975");
		icons.put(LC_ICON_SPROUT, "\ue977");
		icons.put(LC_ICON_TREE, "\ue978");
		icons.put(LC_SEND, "\ue97a");
		icons.put(LC_ARGUS_SEED, "\ue985");
		icons.put(LC_FRIENDS_OFF, "\ue986");
		icons.put(LC_FRIENDS_ON, "\ue987");

		icons.put(STEPS2_OFF, "\ue988");
		icons.put(STEPS2_ON, "\ue989");
		icons.put(STORE2_OFF, "\ue98a");
		icons.put(STORE2_ON, "\ue98b");
		icons.put(LC_SETTINGS, "\ue6aa");
		icons.put(ARGUS_REPORT, "\ue94a");
		icons.put(WEB_INFO, "\ue938");
		icons.put("LC-profile1", "\ue97b");
		icons.put("LC-profile10", "\ue984");
		icons.put("LC-profile2", "\ue97c");
		icons.put("LC-profile3", "\ue97d");
		icons.put("LC-profile4", "\ue97e");
		icons.put("LC-profile5", "\ue97f");
		icons.put("LC-profile6", "\ue980");
		icons.put("LC-profile7", "\ue981");
		icons.put("LC-profile8", "\ue982");
		icons.put("LC-profile9", "\ue983");
		icons.put(ARGUS_DELETE2, "\ue690");
		icons.put(ARGUS_NOTE, "\ue91e");
		icons.put(ARGUS_LOSE_WEIGHT, "\ue9cd");
		icons.put(ARGUS_GAIN_MUSCLE, "\ue9ca");
		icons.put(ARGUS_GET_ACTIVE, "\ue9cb");
		icons.put(GLUCOSE_CGM, "\ue9d7");
		icons.put(EXERCISE_GRAPH, "\ue90a");
		icons.put(ARGUS_BARCODE, "\ue699");
		icons.put(ARGUS_PLUS, "\ue6ad");
		icons.put(ARGUS_CAMERA, "\ue624");
		icons.put(ARGUS_WEIGHT2, "\ue9c4");
		icons.put(ARGUS_KETONES, "\ue9da");
		icons.put(ARGUS_FB_TAB_MORE, "\ue9bb");

		icons.put(INSIGHT_OFF, "\ue801");
		icons.put(INSIGHT_ON, "\ue802");
		icons.put(MEAL_PLAN_OFF, "\ue9b9");
		icons.put(MEAL_PLAN_ON, "\ue9ba");
		icons.put(PLANS_OFF, "\ue9bc");
		icons.put(PLANS_ON, "\ue9bd");
		icons.put(NAV_MORE_OFF, "\ue6a1");
		icons.put(NAV_MORE_ON, "\ue6aa");
		icons.put(ARGUS_EXERCISE_II_OFF, "\ue911");
		icons.put(IHR_EXERCISE, "\ue912");
		icons.put(FB_favOFF, "\ue660");
		icons.put(ARGUS_FB_TAB_PLANS_ON, "\ue9bd");
		icons.put(ARGUS_FB_TAB_PLANS_OFF, "\ue9bc");

		icons.put(SMART_FOOD_1, "\ue950");
		icons.put(SMART_FOOD_2, "\ue950");
		icons.put(SMART_FOOD_3, "\ue950");
		icons.put(CANCEL_ICON, "\ue92f");
		icons.put(ARROW_LEFT, "\uf125");

		mDeserializationLock.acquireUninterruptibly();

		AsyncTask.THREAD_POOL_EXECUTOR.execute(() ->
		{
			parse();
			mDeserializationLock.release();
		});
	}

	private void parse()
	{
		/* todo jason
		assertFalse("This method should NOT be called on main thread!", Thread.currentThread() == Looper.getMainLooper().getThread());
		ObjectMapper objectMapper = ObjectMapperProvider.getSharedSmileInstance();
		InputStream input = null;
		InputStream rawInput = null;

		try
		{
			rawInput = ApplicationContextProvider.getApplicationContext().getResources().openRawResource(R.raw.selection);
			input = new BufferedInputStream(rawInput);

			JsonNode root = objectMapper.readTree(input);

			for (JsonNode node : root)
			{
				int codeValue = node.get("code").asInt();
				String unicodeString = Character.toString((char) codeValue);

				String key = node.get("name").asText().toLowerCase();

				icons.put(key, unicodeString);
			}
			input.close();
			rawInput.close();
		}
		catch (Throwable e)
		{
			Log.e(LOG_TAG, "ERROR! ", e);
		}
		finally
		{
			StreamUtils.quietCloseStream(input);
			StreamUtils.quietCloseStream(rawInput);
		}

		 */
	}

	@NonNull
	public String get(@Nullable String what)
	{
		if (TextUtils.isEmpty(what))
		{
			return "";
		}

		what = what.toLowerCase();

		if (icons.containsKey(what))
		{
			return icons.get(what);
		}
		return "";
	}

	public char getAZBIcon(String iconCode)
	{
		Integer code = Integer.parseInt(iconCode.substring(2), 16); // the integer 65 in base 10
		char ch = Character.toChars(code)[0];
		return ch;
	}
}
