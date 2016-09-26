package com.bignerdranch.android.itrainer.database;

/**
 * Created by Marco on 9/11/2016.
 */
public class TrainerDbSchema {
    //Table containing customer information
    public final static class CustomerTable{
        public final static String NAME = "customers";

        public final static class Cols {
            public final static String ID = "id";
            public final static String UNIQUE_ID = "unique_id";
            public final static String F_NAME = "F_Name";
            public final static String L_NAME = "L_Name";
            public final static String DOB_YEAR = "DOB_Year";
            public final static String DOB_MONTH = "DOB_Month";
            public final static String DOB_DAY = "DOB_Day";
            public final static String PICTURE = "Picture";

        }
    }

    //Table containing session information
    public final static class SessionsTable{
        public final static String NAME = "sessions";

        public final static class Cols {
            public final static String ID = "id";
            public final static String UNIQUE_ID = "unique_id";
            public final static String TOTAL_SESSIONS = "total_sessions";
            public final static String SESSIONS_COMPLETED = "sessions_completed";
        }
    }

    //Table contaniing customer payment information
    public final static class PaymentInfoTable{
        public final static String NAME = "PaymentInfo";

        public final static class Cols {
            public final static String ID = "id";
            public final static String UNIQUE_ID = "unique_id";
            public final static String ADDRESS = "address";
            public final static String ADDED_SESSIONS = "added_sessions";
            public final static String PRICE = "price";
            public final static String PHONE = "phone";
            public final static String CC_INFO = "cc_info";
            public final static String EXP_DATE = "EXP_DATE";

        }
    }

    public final static class UsersTable{
        public final static String NAME = "users";

        public final static class Cols {
            public final static String ID = "id";
            public final static String F_NAME = "F_Name";
            public final static String L_NAME = "L_Name";
            public final static String USER_NAME = "user_name";
            public final static String PASSWORD = "password";

        }
    }

}