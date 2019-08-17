package com.frogobox.speechbooster.helper

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.helper
 *
 */
class ConstHelper {

    object ApiUrl {

    }

    object Pref {
        const val PREF_NAME = "SpeechBooster"
        const val PREF_USER_DATA = "PREF_USER_DATA"
        const val PREF_USER_PASS = "PREF_USER_PASS"
    }

    object Extra {
        const val EXTRA_PROBLEM = "EXTRA_PROBLEM"
    }

    object Tag {
        const val TAG_DEFAULT_FORM = 7000
        const val TAG_DEFAULT_FORM_HEADER = 7001
        const val TAG_DEFAULT_FORM_IMAGE_VIEW = 7002
        const val TAG_DEFAULT_FORM_SINGLE_LINE = 7003
        const val TAG_DEFAULT_FORM_MULTI_LINE = 7004
        const val TAG_DEFAULT_FORM_EMAIL = 7005
        const val TAG_DEFAULT_FORM_PASSWORD = 7006
        const val TAG_DEFAULT_FORM_NUMBER = 7007
        const val TAG_DEFAULT_FORM_PHONE = 7008
        const val TAG_DEFAULT_FORM_TEXT_VIEW = 7009
        const val TAG_DEFAULT_FORM_LABEL = 7010
        const val TAG_DEFAULT_FORM_DATE = 7011
        const val TAG_DEFAULT_FORM_TIME = 7012
        const val TAG_DEFAULT_FORM_DATE_TIME = 7013
        const val TAG_DEFAULT_FORM_DROPDOWN = 7014
        const val TAG_DEFAULT_FORM_MULTI_CHECKBOX = 7015
        const val TAG_DEFAULT_FORM_AUTO_COMPLETE = 7016
        const val TAG_DEFAULT_FORM_AUTO_COMPLETE_TOKEN = 7017
        const val TAG_DEFAULT_FORM_SWITCH = 7018
        const val TAG_DEFAULT_FORM_SLIDER = 7019
        const val TAG_DEFAULT_FORM_CHECKBOX = 7020
        const val TAG_DEFAULT_FORM_PROGRESS = 7021
        const val TAG_DEFAULT_FORM_SEGMENTED = 7022
        const val TAG_DEFAULT_FORM_BUTTON = 7023
        const val TAG_DEFAULT_FORM_RADIO = 7024
    }

    object Const {
        // -------------------------------------------------------------------------------------------------------------
        const val JOGET_VAR_ORG = "org"
        const val JOGET_VAR_JOGET = ".joget"
        const val JOGET_VAR_APPS = ".apps"
        const val JOGET_VAR_FORM = ".form"
        const val JOGET_VAR_MODEL = ".model"
        const val JOGET_VAR_LIB = ".lib"
        // -------------------------------------------------------------------------------------------------------------
        const val JOGET_PACKAGE_MODEL = JOGET_VAR_ORG + JOGET_VAR_JOGET + JOGET_VAR_APPS + JOGET_VAR_FORM + JOGET_VAR_MODEL
        const val JOGET_PACKAGE_LIB = JOGET_VAR_ORG + JOGET_VAR_JOGET + JOGET_VAR_APPS + JOGET_VAR_FORM + JOGET_VAR_LIB
        // -------------------------------------------------------------------------------------------------------------
        // Base Class Name Model
        const val ITEM_FORM = ".Form"
        const val ITEM_COLUMN = ".Column"
        const val ITEM_SECTION = ".Section"
        // -------------------------------------------------------------------------------------------------------------
        // Base Class Name Lib
        const val ITEM_WORK_FLOW_FORM_BINDER = ".WorkflowFormBinder"
        const val ITEM_TEXT_FIELD = ".TextField"
        const val ITEM_TEXT_AREA = ".TextArea"
        const val ITEM_SELECT_BOX = ".SelectBox"
        const val ITEM_CHECK_BOX = ".CheckBox"
        const val ITEM_RADIO = ".Radio"
        const val ITEM_DATE_PICKER = ".DatePicker"
        const val ITEM_PASSWORD_FIELD = ".PasswordField"
        const val ITEM_FILE_UPLOAD = ".FileUpload"
        // -------------------------------------------------------------------------------------------------------------
        const val CLASSNAME_MODEL_FORM = JOGET_PACKAGE_MODEL + ITEM_FORM
        const val CLASSNAME_MODEL_COLUMN = JOGET_PACKAGE_MODEL + ITEM_COLUMN
        const val CLASSNAME_MODEL_SECTION = JOGET_PACKAGE_MODEL + ITEM_SECTION
        // -------------------------------------------------------------------------------------------------------------
        const val CLASSNAME_LIB_WORK_FLOW_FORM_BINDER = JOGET_PACKAGE_LIB + ITEM_WORK_FLOW_FORM_BINDER
        const val CLASSNAME_LIB_TEXT_FIELD = JOGET_PACKAGE_LIB + ITEM_TEXT_FIELD
        const val CLASSNAME_LIB_TEXT_AREA = JOGET_PACKAGE_LIB + ITEM_TEXT_AREA
        const val CLASSNAME_LIB_SELECT_BOX = JOGET_PACKAGE_LIB + ITEM_SELECT_BOX
        const val CLASSNAME_LIB_CHECK_BOX = JOGET_PACKAGE_LIB + ITEM_CHECK_BOX
        const val CLASSNAME_LIB_RADIO = JOGET_PACKAGE_LIB + ITEM_RADIO
        const val CLASSNAME_LIB_DATE_PICKER = JOGET_PACKAGE_LIB + ITEM_DATE_PICKER
        const val CLASSNAME_LIB_PASSWORD_FIELD = JOGET_PACKAGE_LIB + ITEM_PASSWORD_FIELD
        const val CLASSNAME_LIB_ITEM_FILE_UPLOAD = JOGET_PACKAGE_LIB + ITEM_FILE_UPLOAD

    }

}