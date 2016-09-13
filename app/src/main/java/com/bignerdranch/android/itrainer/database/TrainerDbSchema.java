package com.bignerdranch.android.itrainer.database;

/**
 * Created by Marco on 9/11/2016.
 */
public class TrainerDbSchema {
    //Table contaniing customer information
    public final static class CustomerTable{
        public final static String NAME = "customers";

        public final static class Cols {
            public final static String ID = "id";
            public final static String F_NAME = "F_Name";
            public final static String L_NAME = "L_Name";
            public final static String AGE = "age";
            public final static String DOB_YEAR = "DOB_Year";
            public final static String DOB_MONTH = "DOB_Month";
            public final static String DOB_DAY = "DOB_Day";
            public final static String START_WEIGHT = "Weight";
            public final static String DESIRED_WEIGHT = "Desired_Weight";

        }
    }

    //Table contaniing session information
    public final static class SessionsTable{
        public final static String NAME = "sessions";

        public final static class Cols {
            public final static String ID = "id";
            public final static String DATE = "date";
            public final static String TYPE = "type";
            public final static String CURRENT_WEIGHT = "current_weight";
            public final static String AMT_TO_TARGET = "amt_to_target";
        }
    }

    //Table contaniing customer payment information
    public final static class PaymentInfoTable{
        public final static String NAME = "PaymentInfo";

        public final static class Cols {
            public final static String ID = "id";
            public final static String ADDRESS_CITY = "address_city";
            public final static String ADDRESS_STREET = "address_street";
            public final static String ADDRESS_STATE = "address_state";
            public final static String ADDRESS_ZIP = "address_zip";
            public final static String CC_INFO = "cc_info";
            public final static String EXP_DATE = "EXP_DATE";

        }
    }
}
