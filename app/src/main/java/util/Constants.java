 package util;


public class Constants {

	//For crash Log using the PostMortemReportExceptionHandler
	public static final boolean POSTMARTEMREPORTLOG = false;
	public static final String PROJECT_TITLE = "SpiceJet";
	public static final String PROJECT_NAME = "SpiceJet";
	public static final String PLATFORM = "Android";
	public static final String FOLDER_NAME = PROJECT_TITLE;
	
	//For Logs in App
	public static final boolean LOG = true;
	
	//Connection timeout for web requests
	public static final int HTTP_TIMEOUT = /*30000*/45000;
	public static final int SOCKET_TIMEOUT = /*30000*/45000;
	
	//Splash time
	public static final long SPLASHTIME = 3000;
	
	//SHARED_PREFERENCES_NAME to store values in SP
	public final static String SHARED_PREFERENCES_NAME = PROJECT_TITLE;
	
	public final static String COLON_SEPERATOR = ";;";
	
	
	public final static String DATE_FORMAT = "EEE dd MMM yyyy HH:mm:ss z";//Fri, 18 Nov 2011 06:58:17 GMT
	public final static String SEARCH_FLIGHTS_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	public final static String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
	public final static String ADD_PAYMENT_DATE_FORMAT = "dd-MMM-yyyy";
	
	public final static String FLIGHTS_DETAILS_DATE_FORMAT = "MM/dd/yyyy HH:mm";
	public final static String FlIGHT_STATUS_DATE_FORMAT = "EEE dd MMM, yyyy";
	public final static String FlIGHT_GALLERY_DATE_FORMAT = "EEE dd MMM";
	
	public final static String TEXT_FAULT="Fault";
	public final static String ERROR_IO="Network not available";
	public final static String ERROR_PARSER_EXCEPTION="Parse Exception";
	public final static String ERROR_UNABLE_TO_CONNECT_SERVER = "Unable to Connect to server";
	public final static String ERROR_EXCEPTION="Exception";
	public final static String ERROR = "Error";
	public final static String RESPONSE = "Response";
	
	public static final int NO_FLAG = 0;
	public static final int FLAG_ACTIVITY_CLEAR_TOP = 1;
	
	
	public static final String INTENT_DATA = "INTENT_DATA";

	public static final String DEV_API_HOST_NAME = "121.242.92.188";
	public static final String BASE_MOBILE_API_DEV_URL = "http://"+DEV_API_HOST_NAME+"/SJMobileAPI/";
	
	public static final String MOBILE_API_PRE_LIVE_URL = "http://"+DEV_API_HOST_NAME+"/SJMobileAPIPreLive/";

	public static final String STORE_API_HOST_NAME = "mobileapi.spicejet.com";
	public static final String STORE_URL = "https://" + STORE_API_HOST_NAME + "/";
	
	/* Need to update both variables for test and live servers */
	
	public static final String BASE_HOST_NAME = DEV_API_HOST_NAME;
	public static final String BASE_URL = BASE_MOBILE_API_DEV_URL;
	
	/* Need to update both variables for test and live servers */
	
	
	public static final String GET_SOURCE_CITIES_URL = BASE_URL + "Flight/GetSourceCities";
	public static final String GET_FLIGHT_SEARCH_RESULTS = BASE_URL + "Flight/FlightListSearch";
	public static final String GET_DESTINATION_CITIES = BASE_URL + "Flight/GetDestinationCities?cityCode=";
	public static final String GET_SEAT_SELECTION = BASE_URL + "Flight/SeatSelection";
	public static final String REGISTER_URL = BASE_URL + "User/Register";
	public static final String GETCOUNTRIES_URL = BASE_URL + "Flight/GetCountries";
	public static final String LOGIN_URL = BASE_URL + "User/Logon";
	public static final String LOGIN_URL_FOR_CHECK_IN = BASE_URL + "User/LogonForWebCheckin";
	public static final String FLIGHT_SCHEDULE = BASE_URL + "Flight/GetFlightSchedule";
	public static final String IS_PROMO_APPLY_FOR_RETURN_URL = BASE_URL + "Flight/IsPromoApplicableForReturnJourney";	
	public static final String CHECK_FOR_UPDATES_URL = BASE_URL + "User/GetVersionDetailsByName";
	
	public static final String FLIGHT_STATUS_URL = BASE_URL + "Flight/GetFlightStatus";
	public static final String GET_STATE_CITIES_URL = BASE_URL + "Flight/GetCitiesByState";
	
	public static final String GET_ANCILLARIES = BASE_URL + "Ancillaries/GetAncillaries";
	public static final String FORGOT_PASSWORD = BASE_URL + "User/SendForgotPasswordEmail";
	public static final String CHANGE_PASSWORD = BASE_URL + "User/ChangePassword";
	public static final String URL_PAYEMENT = BASE_URL + "Payment/paymentrequest";
	public static final String URL_PAYEMENT_YESBANK = BASE_URL + "Payment/PaymentRequest"; 
	public static final String URL_GET_SEAT_LAYOUT = BASE_URL+ "Booking/SeatSelection";
	public static final String URL_ASSIGN_SEAT = BASE_URL+ "Booking/AssignSeats";
	public static final String SEND_PASSENGERS_URL = BASE_URL + "Booking/UpdatePassenger";
	public static final String COMMIT_HOLD_BOOKING_URL = BASE_URL+ "Booking/CommitHoldBooking";
	public static final String SELL_SSR = BASE_URL + "Booking/SellSSR";
	public static final String ADD_PAYMENT_TO_BOOKING_URL = BASE_URL+ "Booking/AddPaymentToBooking";
	public static final String GET_BOOKING_INFO_URL = BASE_URL+ "Booking/GetBooking";
	public static final String CLEAR_SEATS_URL = BASE_URL + "Booking/ClearSeats";

	public static final String GET_DOCTYPE= BASE_URL+"Passenger/GetDocumentTypes";
	public static final String GET_PASSENGERS_DETAILS = BASE_URL + "Passenger/GetPassengerDetails";
	public static final String UPDATE_PASSENGER_DETAILS = BASE_URL + "Passenger/GetPassengerDetails";
	public static final String SELL_JOURNEY_URL = BASE_URL + "Booking/SellJourney";
	public static final String GET_PAYMENT_FEE_PRICE = BASE_URL + "Booking/GetPaymentFeePrice";
	
	public static final String SAVE_REG_ID_SERVER_URL = BASE_URL + "User/SavePushNotificationDetails";
	public static final String DELETE_REG_ID_SERVER_URL = BASE_URL + "User/DeletePushNotificationDetails";
	public static final String SESSION_LOGOUT = BASE_URL + "user/logout";
	public static final String SHARED_SIGNATURE_URL = BASE_URL + "user/GetSharedSignature";
	
	//ManageBooking API
	public static final String CANCELFLIGHT_URL = BASE_URL + "Booking/CheckForCancelFlight";
	public static final String ADD_CREDITSHELL = BASE_URL + "Booking/AddCreditShellPaymentToBooking";
	public static final String COMMIT_BOOKING = BASE_URL +"Booking/BookingCommit";
	public static final String UPDATE_CONTACT_DETAISL = BASE_URL + "Booking/UpdateContacts";
	public static final String RETRIEVE_BOOKING_URL = BASE_URL + "Booking/RetrieveBooking";
	public static final String GET_CHECK_IN_DETAILS_URL = BASE_URL + "Booking/GetDetailsForCheckIn";
	public static final String CHECK_IN_PASSENGERS_URL = BASE_URL + "Booking/CheckInPassenger";
	public static final String GENERATE_BOARDING_PASSES_URL = BASE_URL + "Booking/GetBarCodeBoardingPass";
	public static final String CHANGE_FLIGHT_URL = BASE_URL + "Booking/ChangeJourney";
	
	// Notification API
	public static final String GET_NOTIFICATIONS_URL = BASE_URL + "User/GetMessagesDetails";
	
	public static final String FIND_FLIGHTS_KEY = "FIND_FLIGHTS_BEAN";
	public static final String SELECTED_DATE_KEY = "SELECTED_DATE_IN_SEARCH_RESULTS";
	public static final int ONWARD_MESSAGE = 100;
	public static final int RETURN_MESSAGE = 101;
	public static final String SCHEDULEBEN = "ScheduleBean";
	public static final String SOURCECITY = "SOURCECITY";
	public static final String DESTCITY = "DESTCITY";
	public static final String DATEOFTRAVEL = "DATEOFTRAVEL";
	public static final String EXTRA_URL = "URL";
	public static final String EXTRA_SCREEN_TITLE = "EXTRA_SCREEN_TITLE";
	public static final String EXTRA_BILLING = "billing";
	public static final String EXTRA_PAXTYPE = "paxType";
	public static final String EXTRA_ISRETURN = "ISRETURN";
	public static final String EXTRA_CURRENT_SELECTED_FLIGHT_NUMBER = "EXTRA_CURRENT_SELECTED_FLIGHT_NUMBER";
	public static final String EXTRA_SEGMENT_INDEX = "EXTRA_SEGMENT_INDEX";
	public static final String EXTRA_POSITION = "EXTRA_POSITION";


	public static final String URL_FACEBOOK = "https://m.facebook.com/FlySpiceJet";
	public static final String URL_TWITTER = "https://mobile.twitter.com/flyspicejet";
	public static final String URL_SPECIAL_ASISTANCE = "file:///android_asset/SpiceAssistance.html";
	
//	public static final String URL_ABOUT_US = "file:///android_asset/CorporateOverview.html";
	public static final String URL_ABOUT_US = BASE_URL + "HTML/CorporateOverview.html";
	
//	public static final String URL_CONTACT_US = "file:///android_asset/ContactUsNew.html";
	public static final String URL_CONTACT_US = BASE_URL + "HTML/ContactUs.html";
	
//	public static final String URL_TERMS_AND_CONDITIONS = "file:///android_asset/TnC/terms.html";
	public static final String URL_TERMS_AND_CONDITIONS = BASE_URL + "HTML/terms.html";
	
//	public static final String URL_FARE_RULES = "file:///android_asset/TnC/fare-rules.html";
	public static final String URL_FARE_RULES = BASE_URL + "HTML/fare-rules.html";
	
	public static final String URL_DEALS_IMAGE = BASE_URL + "Deals/%s/deals.JPG";
	
	public static final String URL_CHECK_IN_TnC = BASE_URL + "HTML/MobileCheckin.html";
	
	public static final String OFFLINE_CITIES_FILE_PATH = "cities.txt";
	
	//Shared preferences values
	public static final String SP_SIGNATURE = "Signature";
	public static final String SP_AGENT_NAME= "AgentName";
	public static final String SP_USERNAME = "Username";
	public static final String SP_PASSWORD = "Password";
	public static final String SP_GUEST = "guest";
	public static final String SP_PERSON_DETAILS = "PersonDetails";
	public static final String FILE_PERSON_DETAILS = "PersonDetails";
	public static final String SP_CAHNGE_PSWD = "change";
	public static final String SP_LOCATION = "User_Location";
	public static final String SP_SHARED_SIGNATURE = "SHARED_SIGNATURE";
	public static final String SP_DEVICE_ID = "DEVICE_ID";
	
	public static final int AGE_LIMIT_INFANT = 2;
	public static final int AGE_LIMIT_CHILD = 12;
	public static boolean ISFrom_Passesnger_ACTIVITY;
	public static int FRAG_POSITION;
	public static final String PROMO_FARE_TYPE = "MobileAppPromo";
	
	//Payment method codes
	public static final String BILL_DESK_METHOD_CODE = "BQ";
	public static final String YES_BANK_METHOD_CODE = "YB";
	
	public static final String BILL_DESK_GATEWAY_ID = "11";
	public static final String YES_BANK_GATEWAY_ID = "9";
	
	public static String DOMAIN_CODE_KEY = "Domaincode";
	public static String AGENT_NAME_KEY = "AgentName";
	public static String PASSWORD_KEY = "Password";
	public static String NEW_PASSWORD_KEY = "NewPassword";
	public static String PNR_KEY = "pnr";
	public static String PNR_BEAN_KEY = "PNR_BEAN";
	public static String ITINERARY_DATE_FORMAT= "yyyy-MM-dd'T'HH:mm:ss";
	public static String ITINERARY_BOOKING_DATE = "EEE, dd-MMM-yyyy";
	public static String BOOKING_DATE_FORMATTER = "EEE, dd-MMM";
	public static String BOOKING_DATE_FORMATTER_NEW = "EEE, dd MMM";
	public final static String ISSUE_DATE_FORMAT = "dd-MMM-yyyy";
	public final static String JOURNEY_DATE_FORMAT = "MM/dd/yyyy HH:mm";
	public final static String BOOKING_DATE_FORMAT = "MM/dd/yyyy";
	public final static String SUMMARY_DATE_FORMAT = "EEE, MMM dd, yyyy";
	
	public final static String TIME_FORMAT = "HH:mm";
	public static String FULL_DATE_FORMAT= "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static String TRANS_REF_KEY = "tans ref no";
	public static String ACC_NO_KEY = "acc no";
	public static String SEARCH_MAX_DATE = "maxdate";
	public final static String AMPM_FORMAT = "aa";
	
	/** Intent Request Codes **/
	public static final int SOURCE_CITIES_REQUEST_CODE = 1001;
	public static final int DESTINATION_CITIES_REQUEST_CODE = 1002;
	public static final int NATIONALITY_CODE = 1003;
	public static final int COUNRTY_CODE = 1004;
	public static final int STATE_CODE = 1005;
	public static final int CITY_CODE = 1006;
	public static final int DEPARTURE_CALENDAR_INTENT_REQUEST_CODE = 1006;
	public static final int ARRIVAL_CALENDAR_INTENT_REQUEST_CODE = 1007;
	
	public static final String SEAT_PASSENGER_COUNT_KEY = "SEAT_SELECTION_PASSENGER_COUNT_KEY";
	public static final String SEAT_SELECTION_PASSENGER_INDEX_KEY = "SEAT_SELECTION_PASSENGER_INDEX_KEY";
	public static final String SELECTED_CITY_KEY = "City_Key";
	public static final String SELECTED_CITY_POSITION = "City_Position";
	public static final String REMOVE_ITEM_KEY = "Remove City";
	public static final String PAYMENT_CODE = "payment code";
	public static final String PAYMENT_URL = "payment url";
	public static final String BOOKING_RESPONSE_KEY = "Booking Response";
	public static final String MANAGE_BOOKING_KEY = "Manage Booking";
	public static final String COMMIT_BOOKING_KEY = "Commit Booking";
	public static final String FROM_CHANGE_FLIGHT_KEY = "From Change Flight";
	public static final String KEY_CITIES = "cities";
	public static final String IS_FROM_PUSH_NOTIFICATION_KEY = "From Push Notification";
	
	public static final String IS_FROM_BOOKING = "Booking Flow";
	public static final String IS_FROM_FIND_FLIGHT = "from find flight";
	public static final String IS_FROM_CONTACT = "from contact";
	public static final String IS_FROM_CONTACT_DISPLAY_CITIES = "from contacts display cities";
	public static final String SELECTED_COUNTRY = "country";
	public static final String SELECTED_COUNTRY_POS = "SELECTED_COUNTRY_POS";
	public static final String SELECTED_CITY_POS = "SELECTED_CITY_POS";
	public static final String SELECTED_COUNTRY_KEY = "SELECTED_COUNTRY_KEY";
	public static final String SELECTED_PASSENGERS = "PassengersList";

	public static String DATE_TYPE_HEADER_STRING = "DATE_TYPE_STRING";
	public static String SELECTED_DATE_IN_MILLIS = "SELECTED_DATE_IN_MILLIS";
	public static String CALENDAR_START_DATE = "CALENDAR_START_DATE";
	public static String CALENDAR_END_DATE = "CALENDAR_END_DATE";
	public static String KEY_EDIT_PASS_BEAN = "Passbean";
	
	public static final String IS_FROM_MANAGE_BOOKING = "FROM_MANAGE_BOOKING";
	public static final String IS_SPICE_MAX_KEY = "IS_SPICE_MAX";
	public static final String IS_REGISTERATION = "registration";
	public static final String ADD_PASSENGER = "add passenger";
	public static final String IS_MENU_LOGIN_KEY = "IS_MENU_LOGIN";
	public static final String IS_FROM_PASSENGER_DETAILS = "FROM_PASSENGER_DETAILS";
	
	public static final long AUTO_POPUP_DISMISS_TIME = 2000;
	public static final String NEW_PASSENGER = "new_passenger";
	
	public static final String FROM_MOBILE_CHECK_IN = "MOBILE_CHECK_IN";
	public static final String PRODUCT_FROM_KEY = "PRODUCT_FROM_KEY";
	public static final String PRODUCT_SPICEMAX = "PRODUCT_SPICEMAX";
	public static final String PRODUCT_HOTMEAL = "PRODUCT_HOTMEAL";
	public static final String PRODUCT_SPICE_ASSURANCE = "PRODUCT_SPICE_ASSURANCE";
	public static final String PRODUCT_EXCESS_BAGGAGE = "PRODUCT_EXCESS_BAGGAGE";
	
	public static final String EXTRA_FROM_CHANGEFLIGHT = "EXTRA_FROM_CHANGEFLIGHT";
	public static final String SEGMENT_POSITION = "SEGMENT POSITION";
	public static final String CONTACT_PERSON_KEY = "Contact Person";
	public static final String TIME_FORMAT_AM_PM = "hh:mm a";
	public static final String KEY_POSITION = "position";
	public static final String KEY_INTERNATIONAL = "position";
	public static final String KEY_BEAN = "bean";
	public static final String KEY_DEPART_CHECK = "departCheckBox";
	public static final String KEY_ARRIVAL_CHECK = "arrivalCheckBox";
	public static final String NOTIFICATION_MESSAGE_KEY = "Notificaiton_Message";
	
	public static final boolean KEY_IS_FROM_HOME_LOGIN = false;
	public static final String BOARDING_PASSES_KEY = "BOARDING_PASSES_KEY";
	
	public static String IF_ANCILLARIES_RECEIVED = "com.vl.spicejet.ANCILLARIES_RECEIVED";
	public static String MANAGE_ANCILLARIES_RECEIVED = "com.vl.spicejet.MANAGE_ANCILLARIES_RECEIVED";
	
	public static final String SENDER_ID = "723051308076";//610972464261-----723051308076
	public static final String GCM_REGISTRATION_ID = "GCM register";
	public static final String GCM_LOGIN_USERNAME = "GCM_UserName";
	
	public static final String MEALS_CODE_NONE = "NONE";
	public static final String BAGGAGE_CODE_NONE = "EB 0";
	public static final String EXTRA_SELECTED_ANCILLARIES_BEAN = "com.vl.spicejet.EXTRA_SELECTED_ANCILLARIES_BEAN";
	public static final String EXTRA_ACTUAL_ANCILLARIES_BEAN_TO_CANCEL = "com.vl.spicejet.EXTRA_ACTUAL_ANCILLARIES_BEAN_TO_CANCEL";
}
